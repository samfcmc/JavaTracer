package ist.meic.pa;


public class TraceVM {

	public static void main(String[] args) {
		Tracer.run(args, new TraceTranslator());
	}
}
