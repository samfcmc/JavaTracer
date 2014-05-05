package ist.meic.pa.historyelements;

public class UsedAsArgumentHistoryElement extends HistoryElement {

	private final String argumentType;

	public UsedAsArgumentHistoryElement(String argumentType) {
		super();
		this.argumentType = argumentType;
	}

	public UsedAsArgumentHistoryElement(String methodName, String fileName,
			int lineNumber, String argumentType) {
		super(methodName, fileName, lineNumber, Direction.IN);
		this.argumentType = argumentType;
	}

	public String getArgumentType() {
		return argumentType;
	}
}
