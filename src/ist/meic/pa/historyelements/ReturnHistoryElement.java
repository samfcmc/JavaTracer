package ist.meic.pa.historyelements;

public class ReturnHistoryElement extends TraceHistoryElement {

	public ReturnHistoryElement(String methodName, String fileName,
			int lineNumber) {
		super(methodName, fileName, lineNumber, Direction.OUT);
	}
}
