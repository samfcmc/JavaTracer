package ist.meic.pa;

public class CallHistoryElement extends HistoryElement {

	public CallHistoryElement(String methodName, String fileName, int lineNumber) {
		super(methodName,fileName,lineNumber);
	}
	
	public String toString() {
		return "  -> " + this.getMethodName() + " on " + this.getFileName() + ":" + this.getLineNumber();
	}	
}
