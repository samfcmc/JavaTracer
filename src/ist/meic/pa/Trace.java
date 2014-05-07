package ist.meic.pa;

import java.io.PrintStream;

/**
 * The Class Trace. This class provides an interface to the programmer to trace
 * whatever objects in some different ways
 */
public class Trace {

	/** The default print stream. */
	private static PrintStream defaultPrintStream = System.err;

	/** The Constant NO_HISTORY. */
	private static final String NO_HISTORY = "Tracing for %s is nonexistent!";

	/** The Constant OBJECT_HISTORY. */
	private static final String OBJECT_HISTORY = "Tracing for %s";

	/**
	 * Prints the tracing for Object <code>object</code> in the default print
	 * stream which is the <code>System.err</code>.
	 * 
	 * @param object
	 *            the object
	 */
	public static void print(Object object) {
		print(object, defaultPrintStream);
	}

	/**
	 * Prints the tracing for Object <code>object</code> in the given print
	 * stream <code>printStream</code>.
	 * 
	 * @param object
	 *            the object
	 * @param printStream
	 *            the print stream
	 */
	public static void print(Object object, PrintStream printStream) {
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

	/**
	 * Prints the tracing for Object <code>object</code> in all print streams in
	 * <code>printStreams</code>.
	 * 
	 * @param object
	 *            the object
	 * @param printStreams
	 *            the print streams
	 */
	public static void print(Object object, Iterable<PrintStream> printStreams) {
		for (PrintStream printStream : printStreams) {
			print(object, printStream);
		}
	}

}
