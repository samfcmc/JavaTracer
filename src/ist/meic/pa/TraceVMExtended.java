package ist.meic.pa;


public class TraceVMExtended {

	public static void main(String[] args) throws Throwable {
		Tracer.run(args, new TraceTranslatorExtended());
	}

}
