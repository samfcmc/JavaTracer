package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;

public class TraceTranslator implements Translator {

	@Override
	public void onLoad(ClassPool pool, String className)
			throws NotFoundException, CannotCompileException {
		CtClass ctClass = pool.get(className);

		traceMethodCalls(ctClass);
		traceConstructorCalls(ctClass);
	}

	@Override
	public void start(ClassPool pool) throws NotFoundException,
			CannotCompileException {
	}

	private static void traceMethodCalls(CtClass ctClass)
			throws CannotCompileException, NotFoundException {
		ctClass.instrument(new ExprEditor() {
			public void edit(MethodCall call) throws CannotCompileException {
				try {
					String template = 
							"{" +
							" 	ist.meic.pa.Trace.addUsedAsArgumentElementToHistory($args, \"%s\", \"%s\", %d);" +
							"	$_ = $proceed($$);" +
							" 	ist.meic.pa.Trace.addReturnElementToHistory($_,\"%s\", \"%s\", %d); " +
							"}";

					call.replace(String.format(template, call.getMethod()
							.getLongName(), call.getFileName(), call
							.getLineNumber(), call.getMethod().getLongName(),
							call.getFileName(), call.getLineNumber()));
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void traceConstructorCalls(CtClass ctClass)
			throws CannotCompileException, NotFoundException {
		ctClass.instrument(new ExprEditor() {
			public void edit(NewExpr newExpr)
					throws CannotCompileException {
				try {
					final String template = 
							"{" +
							"	$_ = $proceed($$);" +
							"	ist.meic.pa.Trace.addReturnElementToHistory($_, \"%s\", \"%s\", %d);" +
							"}";
					newExpr.replace(String.format(template, newExpr
							.getConstructor().getLongName(), newExpr
							.getFileName(), newExpr.getLineNumber()));
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
