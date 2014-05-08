package ist.meic.pa.test;

import ist.meic.pa.Trace;

public class OurTest {

	public static void main(String[] args) {
		Foo foo = new Bar("Hello", 69);
		Bar bar = (Bar) foo;
		Trace.print(foo);
		Trace.print(bar);
	}
}

class Foo {
	String field;

	Foo(String field) {
		this.field = field;
	}
}

class Bar extends Foo {
	int field2;

	Bar(String field, int field2) {
		super(field);
		this.field2 = field2;
	}
}