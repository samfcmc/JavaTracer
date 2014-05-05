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
			@Override
			public void edit(MethodCall call) throws CannotCompileException {
				try {
					String template = "{ "
							+ "$_ = $proceed($$); "
							+ " ist.meic.pa.Trace.addReturnElementToHistory($_,\"%s\", \"%s\", %d);"
							+ " ist.meic.pa.Trace.addUsedAsArgumentElementToHistory($args, \"%s\", \"%s\", %d);"
							+ "} ";

					/*
					 * String template = "{" + // insert stuff here to get the
					 * parameters //
					 * "	ist.meic.pa.Trace.addCallElementToHistory(params, \""
					 * // + call.getMethod().getLongName() + "\", \"" + //
					 * call.getFileName() + "\", " + // call.getLineNumber() +
					 * ");" + // "	}" + " 	$_ = $proceed($$); " +
					 * "	ist.meic.pa.Trace.addReturnElementToHistory($_,\"" +
					 * call.getMethod().getLongName() + "\", \"" +
					 * call.getFileName() + "\", " + call.getLineNumber() + ");"
					 * +
					 * " ist.meic.pa.Trace.addUsedAsArgumentElementToHistory($args, "
					 * + "}";
					 */

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
			@Override
			public void edit(NewExpr newExpr) throws CannotCompileException {
				try {
					final String template = "{"
							+ "	$_ = $proceed($$); "
							+ "	ist.meic.pa.Trace.addReturnElementToHistory($_,\""
							+ newExpr.getConstructor().getLongName() + "\", \""
							+ newExpr.getFileName() + "\", "
							+ newExpr.getLineNumber() + ");" + "}";
					newExpr.replace(template);
					// System.err.println("Calling constructor for " +
					// newExpr.getConstructor().getLongName() + " in " +
					// newExpr.getFileName() + ":" + newExpr.getLineNumber());
				} catch (NotFoundException e) {
					// black hole
				}
			}
		});
	}

}
