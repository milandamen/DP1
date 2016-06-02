import controller.Mediator;
import model.Circuit;

import view.MainFrame;

public class Main {

	public static void main(String[] args) throws Exception {
		Mediator mediator = Mediator.getInstance();
		
		//mediator.buildCirctuit("circuit1.txt");
		//mediator.simulate();
		MainFrame mainView = new MainFrame();
		
	}
}
