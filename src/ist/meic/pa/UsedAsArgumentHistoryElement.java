package ist.meic.pa;

public class UsedAsArgumentHistoryElement extends HistoryElement {

	private final String argumentType;

	public UsedAsArgumentHistoryElement(String argumentType) {
		super();
		this.argumentType = argumentType;
	}

	public UsedAsArgumentHistoryElement(String methodName, String fileName,
			int lineNumber, String argumentType) {
		super(methodName, fileName, lineNumber);
		this.argumentType = argumentType;
	}

	public String getArgumentType() {
		return argumentType;
	}

	@Override
	public String toString() {
		return "  -> " + getMethodName() + " on " + getFileName() + ":"
				+ getLineNumber();
	}
}
