package ist.meic.pa.historyelements;

public class FieldAccessHistoryElement extends HistoryElement {

	private String fieldName;

	public FieldAccessHistoryElement(String fieldName, String fileName,
			int lineNumber, Direction direction) {
		super(fileName, lineNumber, direction);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String toString() {
		String setOrGet = getDirection().getString().equals("->") ? " WRITE "
				: " READ ";

		return "  " + getDirection().getString() + setOrGet + "field \'"
				+ fieldName + "\' on " + getFileName() + ":" + getLineNumber();
	}
}
