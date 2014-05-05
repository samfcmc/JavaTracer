package ist.meic.pa;

public class JavassistHelper {
	public static String addConstructorCallHistory(String object, String fileName, int lineNumber) {
		return "ist.meic.pa.Trace.createHistory(" + object + ");"; 
	}
}
