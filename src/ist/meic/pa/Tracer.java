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

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.Loader;
import javassist.NotFoundException;
import javassist.Translator;

public class Tracer {

	private static Map<Object, History> history = new IdentityHashMap<Object, History>();

	private static PrintStream printStream = System.err;

	public static void run(String mainArgs[], Translator translator) {
		if (mainArgs.length < 1) {
			printStream.println("Invalid number of arguments.");
			printStream.println("Usage: TraceVM <filename>");
		} else {
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
				printStream.println("ERROR: " + e.getMessage());
			}
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

	public static void addFieldAccessElementToHistory(Object object,
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
