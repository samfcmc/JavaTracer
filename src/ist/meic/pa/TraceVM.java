package ist.meic.pa;

import java.io.PrintStream;
import java.lang.reflect.*;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class TraceVM {

	private static PrintStream printStream = System.err;

	public static void main(String[] args) throws NotFoundException,
			CannotCompileException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (args.length < 1) {
			printStream.println("Invalid number of arguments.");
			printStream.println("Usage: TraceVM <filename>");
		} else {
			ClassPool pool = ClassPool.getDefault();
			CtClass ctClass = pool.get(args[0]);
			Class<?> rtClass = ctClass.toClass();
			Method main = rtClass.getMethod("main", args.getClass());
			main.invoke(null, new Object[] {});
		}
	}

}
