package ist.meic.pa;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class History {

	private final List<HistoryElement> historyElements;

	public History() {
		this(new ArrayList<HistoryElement>());
	}

	public History(List<HistoryElement> historyElements) {
		super();
		this.historyElements = historyElements;
	}

	public void addHistoryElement(HistoryElement historyElement) {
		this.historyElements.add(historyElement);
	}

	public void print(PrintStream printStream) {
		for (HistoryElement historyElement : this.historyElements) {
			printStream.println(historyElement.toString());
		}
	}

}
