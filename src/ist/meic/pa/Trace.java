package ist.meic.pa;

import ist.meic.pa.historyelements.CastHistoryElement;
import ist.meic.pa.historyelements.Direction;
import ist.meic.pa.historyelements.FieldAccessHistoryElement;
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

	public static void addUsedAsArgumentElementToHistory(Object[] objects,
			String methodName, String fileName, int lineNumber) {
		HistoryElement historyElement = new UsedAsArgumentHistoryElement(
				methodName, fileName, lineNumber);
		for (Object object : objects) {
			createHistory(object);
			getHistory(object).addHistoryElement(historyElement);
		}
	}

	public static void addCastElementToHistory(Object object,
			Class<?> castType, String fileName, int lineNumber) {
		HistoryElement element = new CastHistoryElement(castType.getName(),
				fileName, lineNumber);
		createHistory(object);
		getHistory(object).addHistoryElement(element);
	}

	private static void addFieldAccessElementToHistory(Object object,
			String fieldName, String fileName, int lineNumber,
			Direction direction) {
		HistoryElement element = new FieldAccessHistoryElement(fieldName,
				fileName, lineNumber, direction);
		createHistory(object);
		getHistory(object).addHistoryElement(element);
	}

	public static void addFieldSetElementToHistory(Object object,
			String fieldName, String fileName, int lineNumber) {
		addFieldAccessElementToHistory(object, fieldName, fileName, lineNumber,
				Direction.IN);
	}

	public static void addFieldGetElementToHistory(Object object,
			String fieldName, String fileName, int lineNumber) {
		addFieldAccessElementToHistory(object, fieldName, fileName, lineNumber,
				Direction.OUT);
	}
}
