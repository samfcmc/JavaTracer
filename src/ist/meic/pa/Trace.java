package ist.meic.pa;

import ist.meic.pa.historyelements.HistoryElement;
import ist.meic.pa.historyelements.ReturnHistoryElement;
import ist.meic.pa.historyelements.UsedAsArgumentHistoryElement;

import java.io.PrintStream;
import java.util.IdentityHashMap;
import java.util.Map;

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
		if (!history.containsKey(object)) {
			history.put(object, new History());
		}
	}

	public static void addReturnElementToHistory(Object object,
			String methodName, String fileName, int lineNumber) {
		createHistory(object);
		HistoryElement element = new ReturnHistoryElement(methodName, fileName,
				lineNumber);
		getHistory(object).addHistoryElement(element);
	}
	
//	public static void addReturnElementToHistory(int i,
//			String methodName, String fileName, int lineNumber) {
//		addReturnElementToHistory(new Integer(i), methodName, fileName, lineNumber);
//	}	
//
//	public static void addReturnElementToHistory(boolean b,
//			String methodName, String fileName, int lineNumber) {
//		addReturnElementToHistory(new Boolean(b), methodName, fileName, lineNumber);
//	}	
//	
//	public static void addReturnElementToHistory(char b,
//			String methodName, String fileName, int lineNumber) {
//		addReturnElementToHistory(new Character(b), methodName, fileName, lineNumber);
//	}	
//	
//	public static void addReturnElementToHistory(byte b,
//			String methodName, String fileName, int lineNumber) {
//		addReturnElementToHistory(new Byte(b), methodName, fileName, lineNumber);
//	}	
//	
//	public static void addReturnElementToHistory(short b,
//			String methodName, String fileName, int lineNumber) {
//		addReturnElementToHistory(new Short(b), methodName, fileName, lineNumber);
//	}	
//	
//	public static void addReturnElementToHistory(long b,
//			String methodName, String fileName, int lineNumber) {
//		addReturnElementToHistory(new Long(b), methodName, fileName, lineNumber);
//	}	
//	
//	public static void addReturnElementToHistory(float b,
//			String methodName, String fileName, int lineNumber) {
//		addReturnElementToHistory(new Float(b), methodName, fileName, lineNumber);
//	}	
//	
//	public static void addReturnElementToHistory(double b,
//			String methodName, String fileName, int lineNumber) {
//		addReturnElementToHistory(new Double(b), methodName, fileName, lineNumber);
//	}	

	public static void addUsedAsArgumentElementToHistory(Object[] objects,
			String methodName, String fileName, int lineNumber) {
		HistoryElement historyElement = new UsedAsArgumentHistoryElement(
				methodName, fileName, lineNumber);
		for (Object object : objects) {
			createHistory(object);
			getHistory(object).addHistoryElement(historyElement);
		}
	}

}
