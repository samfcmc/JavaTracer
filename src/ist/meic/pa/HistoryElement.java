/**
 * 
 */
package ist.meic.pa;

/**
 * @author ist169350
 * 
 */
public abstract class HistoryElement {

	private String methodName;
	private String fileName;
	private int lineNumber;

	/**
	 * 
	 */
	public HistoryElement() {
		this("", "", 0);
	}

	public HistoryElement(String methodName,
			String fileName, int lineNumber) {
		this.methodName = methodName;
		this.fileName = fileName;
		this.lineNumber = lineNumber;
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

}
