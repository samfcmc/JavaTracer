package ist.meic.pa;

import java.io.PrintStream;
import javassist.*;

public class TraceVM {

	private static PrintStream printStream = System.err;

	public static void main(String[] args) throws Throwable {
		if (args.length < 1) {
			printStream.println("Invalid number of arguments.");
			printStream.println("Usage: TraceVM <filename>");
		} else {
			Translator translator = new TraceTranslator();
			ClassPool pool = ClassPool.getDefault();
			Loader classLoader = new Loader();
			classLoader.addTranslator(pool, translator);
			classLoader.run(args); //throws throwable...
		}
	}
}
