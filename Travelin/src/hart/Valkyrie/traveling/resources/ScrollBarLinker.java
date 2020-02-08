package hart.Valkyrie.traveling.resources;

import hart.Valkyrie.objects.eventbuttonmanager.MethodParser;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;

public class ScrollBarLinker extends MethodParser<ScrollBar, MouseEvent>
{
	
	public ScrollBarLinker()
	{
		
	}

	@Override
	public ScrollBar link(ScrollBar n, EventHandler<MouseEvent> e)
	{
		n.setOnMouseMoved(e);
		return n;
	}

	@Override
	public void deConstruct()
	{
		
	}

}
