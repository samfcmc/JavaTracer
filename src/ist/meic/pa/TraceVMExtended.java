package ist.meic.pa;

import java.io.PrintStream;

import javassist.ClassPool;
import javassist.Loader;
import javassist.Translator;

public class TraceVMExtended {
	
	private static PrintStream printStream = System.err;
	
	public static void main(String[] args) throws Throwable {
		if (args.length < 1) {
			printStream.println("Invalid number of arguments.");
			printStream.println("Usage: TraceVM <filename>");
		} else {
			Translator translator = new TraceTranslatorExtended();
			ClassPool pool = ClassPool.getDefault();
			Loader classLoader = new Loader();
			classLoader.delegateLoadingOf("ist.meic.pa.Trace");
			classLoader.addTranslator(pool, translator);
			String[] restArgs = new String[args.length - 1];
			System.arraycopy(args, 1, restArgs, 0, restArgs.length);
			classLoader.run(args[0], restArgs); //throws throwable...
		}
	}
	
}
