package ist.meic.pa;

public class ReturnHistoryElement extends HistoryElement {

	public ReturnHistoryElement(String methodName, String fileName, int lineNumber) {
		super(methodName,fileName,lineNumber);
	}
	
	public String toString() {
		return "  <- " + this.getMethodName() + " on " + this.getFileName() + ":" + this.getLineNumber();
	}
}
