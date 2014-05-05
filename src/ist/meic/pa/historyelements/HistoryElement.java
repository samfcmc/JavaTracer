/**
 * 
 */
package ist.meic.pa.historyelements;

/**
 * @author ist169350
 * 
 */
public abstract class HistoryElement {

	private String methodName;
	private String fileName;
	private int lineNumber;
	private final Direction direction;

	/**
	 * 
	 */
	public HistoryElement() {
		this("", "", 0, Direction.NONE);
	}

	public HistoryElement(String methodName, String fileName, int lineNumber,
			Direction direction) {
		this.methodName = methodName;
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.direction = direction;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public String toString() {
		return "  " + this.direction.getString() + " " + this.methodName
				+ " on " + this.fileName + ":" + this.lineNumber;
	}

}
