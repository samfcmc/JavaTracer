package ist.meic.pa;

public class ReturnHistoryElement extends HistoryElement {

	public String toString() {
		return "  <- " + this.getMethodName() + " on " + this.getFileName() + ":" + this.getLineNumber();
	}
}
