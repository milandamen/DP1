package controller.logging;

import view.LogPanel;

public class GUILogger implements ILogger {
	private LogPanel logPanel;

	public GUILogger(LogPanel logPanel) {
		this.logPanel = logPanel;
	}

	@Override
	public void log(String string) {
		logPanel.log(string + "\n");
	}

}
