package ist.meic.pa;

import java.io.PrintStream;

public class Trace {

	private static PrintStream printStream = System.err;
	private static final String NO_HISTORY = "Tracing for %s is nonexistent!";
	private static final String OBJECT_HISTORY = "Tracing for %s";

	public static void print(Object object) {
		History objectHistory = Tracer.getHistory(object);

		if (objectHistory == null) {
			String message = String.format(NO_HISTORY, object.toString());
			printStream.println(message);
		} else {
			String message = String.format(OBJECT_HISTORY, object.toString());
			printStream.println(message);
			objectHistory.print(printStream);
		}
	}

}
