package controller.logging;

public class ConsoleLogger implements ILogger {

	@Override
	public void log(String string) {
		System.out.println(string);
	}

}
