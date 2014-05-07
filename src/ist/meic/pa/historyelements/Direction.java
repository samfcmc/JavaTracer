package ist.meic.pa.historyelements;

public enum Direction {

	IN("->"), OUT("<-"), NONE(""), CAST("<>");

	private String string;

	private Direction(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}
}
