package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.ConstructorCall;
import javassist.expr.ExprEditor;

public class TraceTranslator implements Translator {

	@Override
	public void onLoad(ClassPool pool, String className) throws NotFoundException,
			CannotCompileException {
		CtClass ctClass = pool.get(className);
		
		traceMethods(ctClass);
		traceConstructors(ctClass);
	}

	@Override
	public void start(ClassPool pool) throws NotFoundException,
			CannotCompileException {

	}
	
	private static void traceMethods(CtClass ctClass) throws CannotCompileException, NotFoundException {
		//TODO: modify methods
		for(CtMethod ctMethod : ctClass.getDeclaredMethods()) {
			traceMethodParameters(ctClass, ctMethod);
			traceMethodReturns(ctClass, ctMethod);
		}
	}
	
	private static void traceMethodParameters(CtClass ctClass, CtMethod ctMethod) throws CannotCompileException, NotFoundException {
		//TODO: modify method starts
		String methodName = ctMethod.getName();
		int methodLineNumber = ctMethod.getMethodInfo().getLineNumber(0);
			
		//need to fix this part
		String beforeMethod = "";
				//"	ist.meic.pa.HistoryElement element = new ist.meic.pa.HistoryElement(\"" + tracingParams + "\");" +
				//"	ist.meic.pa.Trace.getHistory($1).addHistoryElement(element);" + 
		ctMethod.insertBefore(beforeMethod);
	}
	
	private static void traceMethodReturns(CtClass ctClass, CtMethod ctMethod) {
		//TODO: modify method returns
	}
	
	private static void traceConstructors(CtClass ctClass) throws CannotCompileException {
		for(CtConstructor ctConstructor : ctClass.getDeclaredConstructors()) {
			traceConstructor(ctClass, ctConstructor);
		}
	}
	
	private static void traceConstructor(CtClass ctClass, CtConstructor ctConstructor) throws CannotCompileException {
		final String beforeCall = 
				"{" +
				"	ist.meic.pa.History hist = new ist.meic.pa.History();" + 
				"	ist.meic.pa.Trace.createHistory(($w)$0, hist);" +
				"   ist.meic.pa.HistoryElement element = new ist.meic.pa.ConstructorHistoryElement();" +
				"   ist.meic.pa.Trace.getHistory(($w)$0).addHistoryElement(element);" +
				"   $_ = $proceed($$);" +
				"}";
		ctConstructor.instrument(new ExprEditor() {
			public void edit(ConstructorCall call) throws CannotCompileException {
				call.replace(beforeCall);
			}
		});
	}
	
	
}
