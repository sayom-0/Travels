package hart.Valkyrie.traveling.resources.interaction;

import hart.Valkyrie.util.BWindow;
import javafx.stage.Stage;

public class Explore extends BWindow
{
	private char pClass;
	
	public Explore(char pClass)
	{
		initd();
		this.pClass = pClass;
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Explore Planet");
		stage.show();
	}
	
}
