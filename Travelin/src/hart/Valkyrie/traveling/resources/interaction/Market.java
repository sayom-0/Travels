package hart.Valkyrie.traveling.resources.interaction;

import java.util.ArrayList;
import hart.Valkyrie.traveling.resources.Sellable;
import hart.Valkyrie.util.BWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Market extends BWindow
{
	ArrayList<Sellable> sellables = new ArrayList<Sellable>();
	
	public Market(String v)
	{
		switch (v)
		{
		case "Full":
			sellables.add(new Sellable("Water", 5, 1));
			break;
			
		case "War":
			
			break;
			
		case "Sup":
			
			break;
		}
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Traveling");
		stage.show();
	}
}
