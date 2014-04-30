package ist.meic.pa;

import java.util.ArrayList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.Translator;

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
		ctMethod.setName(methodName + "$original");
		ctMethod = CtNewMethod.copy(ctMethod, methodName, ctClass, null);
		
		List<String> methodParameters = new ArrayList<String>();
		for(CtClass c : ctMethod.getParameterTypes()) {
			methodParameters.add(c.getName());
		}
		//need to fix this part
		String methodBody = "{ return ($r)" + methodName + "$original($$); }";
		/*
		String methodBody = 
				"{" + "System.out.println(\"failed\");" +
				"	ist.meic.pa.Trace.createHistory($1, new History());" +
				"	HistoryElement element = new HistoryElement(\"" + methodName + "\"," +
				"												new java.util.ArrayList<String>(), " +
				"												\"" + ctClass.getName() + ".java\", " +
																methodLineNumber + ");" +
				"	ist.meic.pa.Trace.getHistoryFor($1).addHistoryElement(element);" + 
				"	return ($r)" + methodName + "$original($$);";
		*/
		ctMethod.setBody(methodBody);
		ctClass.addMethod(ctMethod);
	}
	
	private static void traceMethodReturns(CtClass ctClass, CtMethod ctMethod) {
		//TODO: modify method returns
	}
	
	private static void traceConstructors(CtClass ctClass) {
		//TODO: modify constructor calls
	}
}
