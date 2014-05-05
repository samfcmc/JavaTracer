package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.ConstructorCall;
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
		final String template = "{}"; 
		
		ctClass.instrument(new ExprEditor() {
			public void edit(MethodCall call) throws CannotCompileException {
				try {
					System.err.println("Calling method " + call.getMethod().getLongName() + " in " + call.getFileName() + ":" + call.getLineNumber());
					call.replace(template);
				} catch (NotFoundException e) {
					//black hole
				}
			}
		});
	}
	
	private static void traceConstructorCalls(CtClass ctClass) throws CannotCompileException {
		final String template = "{}";
		
		ctClass.instrument(new ExprEditor() {
			public void edit(ConstructorCall call) throws CannotCompileException {
				try {
					System.err.println("Calling constructor " + call.getConstructor().getLongName() + " in " + call.getFileName() + ":" + call.getLineNumber());
					call.replace(template);
				} catch (NotFoundException e) {
					//black hole
				}
			}
		});
	}
	
	
}
