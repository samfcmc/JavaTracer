package ist.meic.pa.historyelements;

public class FieldAccessHistoryElement extends HistoryElement {

	private String fieldName;

	public FieldAccessHistoryElement(String fieldName, String fileName, int lineNumber, Direction direction) {
		super(fileName,lineNumber,direction);
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public String toString() {
		String setOrGet = getDirection().getString().equals("->") ? " written" : " read";
		return "  " + getDirection().getString() + " field " + fieldName + setOrGet
				+ " on " + getFileName() + ":" + getLineNumber();
	}
}
