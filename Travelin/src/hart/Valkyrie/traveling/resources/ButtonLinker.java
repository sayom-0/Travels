package hart.Valkyrie.traveling.resources;

import hart.Valkyrie.objects.eventbuttonmanager.MethodParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonLinker extends MethodParser<Button, ActionEvent>
{
	public ButtonLinker()
	{

	}

	@Override
	public Button link(Button n, EventHandler<ActionEvent> e)
	{
		n.setOnAction(e);
		return n;
	}

}
