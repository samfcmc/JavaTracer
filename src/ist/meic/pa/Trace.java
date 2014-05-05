package ist.meic.pa;

import java.io.PrintStream;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 
 *
 */
public class Trace {

	private static Map<Object, History> history = new IdentityHashMap<Object, History>();
	private static PrintStream printStream = System.err;
	private static final String NO_HISTORY = "Tracing for %s is nonexistent!";
	private static final String OBJECT_HISTORY = "Tracing for %s";

	public static void print(Object object) {
		History objectHistory = getHistory(object);

		if (objectHistory == null) {
			String message = String.format(NO_HISTORY, object.toString());
			printStream.println(message);
		} else {
			String message = String.format(OBJECT_HISTORY, object.toString());
			printStream.println(message);
			objectHistory.print(printStream);
		}
	}

	public static History getHistory(Object object) {
		return history.get(object);
	}

	public static void createHistory(Object object) {
		if(!history.containsKey(object)) {
			history.put(object, new History());
		}
	}

	public static void addCallElementToHistory(Object[] objects, String methodName, String fileName, int lineNumber) {
		HistoryElement element = new CallHistoryElement(methodName,fileName,lineNumber);
		for(Object object : objects) {
			createHistory(object);
			getHistory(object).addHistoryElement(element);
		}
	}
	
	public static void addReturnElementToHistory(Object object, String methodName, String fileName, int lineNumber) {
		createHistory(object);
		HistoryElement element = new ReturnHistoryElement(methodName,fileName,lineNumber);
		getHistory(object).addHistoryElement(element);
	}

}
