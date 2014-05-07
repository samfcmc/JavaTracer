/**
 * 
 */
package ist.meic.pa.historyelements;

/**
 * @author ist169350
 * 
 */
public abstract class HistoryElement {

	//private String methodName;
	private String fileName;
	private int lineNumber;
	private final Direction direction;

	public HistoryElement() {
		this("", 0, Direction.NONE);
	}

	public HistoryElement(String fileName, int lineNumber,
			Direction direction) {
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.direction = direction;
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

	public Direction getDirection() {
		return direction;
	}

}
