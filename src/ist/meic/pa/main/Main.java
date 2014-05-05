package ist.meic.pa.main;

import ist.meic.pa.Trace;

public class Main {
	
	public static String foo(String s) {
		return s;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = new String("Hello");
		Trace.print(s);
	}

}
