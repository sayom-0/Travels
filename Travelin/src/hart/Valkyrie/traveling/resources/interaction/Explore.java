package hart.Valkyrie.traveling.resources.interaction;

import hart.Valkyrie.util.BWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Explore extends BWindow
{
	private char pClass;
	
	public Explore(char pClass)
	{
		this.pClass = pClass;
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Explore Planet");
		stage.show();
	}
	
}
