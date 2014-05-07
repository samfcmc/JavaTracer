package ist.meic.pa.historyelements;

public class CastHistoryElement extends HistoryElement {
	
	private String castType;
	
	public CastHistoryElement(String castType, String fileName,
			int lineNumber) {
		super(fileName, lineNumber, Direction.CAST);
		this.setCastType(castType);
	}

	public String getCastType() {
		return castType;
	}

	public void setCastType(String castType) {
		this.castType = castType;
	}
	
	@Override
	public String toString() {
		return "  " + getDirection().getString() + " cast to " + this.castType
				+ " on " + getFileName() + ":" + getLineNumber();
	}
}
