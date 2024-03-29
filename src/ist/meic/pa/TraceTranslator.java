package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;

public class TraceTranslator implements Translator {

	@Override
	public void onLoad(ClassPool pool, String className)
			throws NotFoundException, CannotCompileException {
		CtClass ctClass = pool.get(className);

		ctClass.instrument(new TraceExprEditor()); 
	}

	@Override
	public void start(ClassPool pool) throws NotFoundException,
			CannotCompileException {
	}
}
