package ist.meic.pa.main;

import ist.meic.pa.Trace;

public class Test {

	public Object foo() {
		return new String("Foo");
	}

	public Object bar() {
		return foo();
	}

	public Object baz() {
		return bar();
	}

	public void test() {
		Trace.print(foo());
		Trace.print(bar());
		Trace.print(baz());
	}

	public static String test(String test) {
		return test;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new Test()).test();
		String test = new String("Testing");
		test(test);
		Trace.print(test);
	}

}
