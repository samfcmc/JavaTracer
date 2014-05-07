package ist.meic.pa;

import java.io.PrintStream;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.Loader;
import javassist.NotFoundException;
import javassist.Translator;

public class Tracer {

	private static PrintStream printStream = System.err;

	public static void run(String mainArgs[], Translator translato) {
		if (mainArgs.length < 1) {
			printStream.println("Invalid number of arguments.");
			printStream.println("Usage: TraceVM <filename>");
		} else {
			Translator translator = new TraceTranslator();
			ClassPool pool = ClassPool.getDefault();
			Loader classLoader = new Loader();
			classLoader.delegateLoadingOf("ist.meic.pa.Trace");
			classLoader.delegateLoadingOf("ist.meic.pa.Tracer");
			try {
				classLoader.addTranslator(pool, translator);
			} catch (NotFoundException e) {
				printStream.println("ERROR: Class " + e.getClass().getName()
						+ " not found");
			} catch (CannotCompileException e) {
				printStream.println("ERROR: Cannot compile injected code");
			}
			String[] restArgs = new String[mainArgs.length - 1];
			System.arraycopy(mainArgs, 1, restArgs, 0, restArgs.length);
			try {
				classLoader.run(mainArgs[0], restArgs);
			} catch (Throwable e) {
				// I know that this error message says nothing but we are
				// catching a throwable
				printStream
						.println("ERROR: An error ocurred while running the class loader");
			}
		}
	}
}
