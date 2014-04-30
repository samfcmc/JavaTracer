package ist.meic.pa;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 */
public class Trace {

	private static Map<Integer, History> history = new HashMap<Integer, History>();
	private static PrintStream printStream = System.err;
	private static final String NO_HISTORY = "Tracing for {0} is nonexistent!";

	public static void print(Object object) {
		History objectHistory = getHistory(object);

		if (objectHistory == null) {
			String message = String.format(NO_HISTORY, object.toString());
			printStream.println(message);
		} else {
			objectHistory.print(printStream);
		}
	}

	private static History getHistory(Object object) {
		return history.get(object.hashCode());
	}

}
