package ist.meic.pa.historyelements;

public abstract class TraceHistoryElement extends HistoryElement {

	private String methodName;

	public TraceHistoryElement(String methodName, String fileName, int lineNumber,
			Direction direction) {
		super(fileName, lineNumber, direction);
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	@Override
	public String toString() {
		return "  " + getDirection().getString() + " " + this.methodName
				+ " on " + getFileName() + ":" + getLineNumber();
	}
}
