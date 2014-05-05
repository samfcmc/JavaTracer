package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.NewExpr;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class TraceTranslator implements Translator {

	@Override
	public void onLoad(ClassPool pool, String className) throws NotFoundException,
			CannotCompileException {
		CtClass ctClass = pool.get(className);
		
		traceMethodCalls(ctClass);
		traceConstructorCalls(ctClass);
	}

	@Override
	public void start(ClassPool pool) throws NotFoundException,
			CannotCompileException {
	}
	
	private static void traceMethodCalls(CtClass ctClass) throws CannotCompileException, NotFoundException {
		ctClass.instrument(new ExprEditor() {
			public void edit(MethodCall call) throws CannotCompileException {
				try {
					String template = 
							"{" +
							//	insert stuff here to get the parameters
							//"	ist.meic.pa.Trace.addCallElementToHistory(params, \"" + call.getMethod().getLongName() + "\", \"" + call.getFileName() + "\", " + call.getLineNumber() + ");" +
							//"	}" +
							" 	$_ = $proceed($$); " +
							"	ist.meic.pa.Trace.addReturnElementToHistory($_,\"" + call.getMethod().getLongName() + "\", \"" + call.getFileName() + "\", " + call.getLineNumber() + ");" +
							"}";
					call.replace(template);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void traceConstructorCalls(CtClass ctClass) throws CannotCompileException, NotFoundException {
		ctClass.instrument(new ExprEditor() {
			public void edit(NewExpr newExpr) throws CannotCompileException {
				try {
					final String template = 
							"{" +
							"	$_ = $proceed($$); " +
							"	ist.meic.pa.Trace.addReturnElementToHistory($_,\"" + newExpr.getConstructor().getLongName() + "\", \"" + newExpr.getFileName() + "\", " + newExpr.getLineNumber() + ");" +
							"}";
					newExpr.replace(template);
					//System.err.println("Calling constructor for " + newExpr.getConstructor().getLongName() + " in " + newExpr.getFileName() + ":" + newExpr.getLineNumber());
				} catch (NotFoundException e) {
					// black hole
				}
			}
		});
	}	
	
}
