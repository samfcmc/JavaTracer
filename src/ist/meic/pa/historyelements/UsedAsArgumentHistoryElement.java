package ist.meic.pa.historyelements;

public class UsedAsArgumentHistoryElement extends TraceHistoryElement {

	public UsedAsArgumentHistoryElement(String methodName, String fileName,
			int lineNumber) {
		super(methodName, fileName, lineNumber, Direction.IN);
	}
}
