/**
 * 
 */
package ist.meic.pa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ist169350
 * 
 */
public abstract class HistoryElement {

	private String methodName;
	private List<String> methodParameters;
	private String fileName;

	/**
	 * 
	 */
	public HistoryElement() {
		this("", new ArrayList<String>(), "");
	}

	public HistoryElement(String methodName, List<String> methodParameters,
			String fileName) {
		super();
		this.methodName = methodName;
		this.methodParameters = methodParameters;
		this.fileName = fileName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<String> getMethodParameters() {
		return methodParameters;
	}

	public void setMethodParameters(List<String> methodParameters) {
		this.methodParameters = methodParameters;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
