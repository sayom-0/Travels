package hart.Valkyrie.traveling.resources.interaction;

import java.util.ArrayList;
import hart.Valkyrie.traveling.resources.Sellable;

public class Market
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
}
