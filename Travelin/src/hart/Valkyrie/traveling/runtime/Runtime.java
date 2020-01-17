package hart.Valkyrie.traveling.runtime;

import java.util.ArrayList;

import hart.Valkyrie.SCFX.ScreenControllerFX;
import hart.Valkyrie.traveling.resources.Map;
import hart.Valkyrie.util.Utils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * W = y--;
 * S = y++;
 * A = x--;
 * D = x++;
 */

public class Runtime extends Application
{

	@Override
	public void start(Stage stage) throws Exception
	{
		ScreenControllerFX SCFX = new ScreenControllerFX(1920, 1080);
		Map map = new Map('.', '=', '@', '8', '*', 50, 150);
		map.generate("field");
		BorderPane HUD = new BorderPane();
		VBox maptext = new VBox();
		VBox inv = new VBox();
		HUD.setCenter(maptext);
		maptext.setSpacing(1);
		ArrayList<Text> maptextarray = new ArrayList<Text>();
		int counter = 0;

		while (counter < map.rawmap.length)
		{
			maptextarray.add(new Text((String) Utils.getArrayRow(counter, map.rawmap)));
			counter++;
		}
		counter = 0;

		while (counter < map.rawmap.length)
		{
			maptext.getChildren().add(maptextarray.get(counter));
			counter++;
		}

		Scene scene = new Scene(HUD, SCFX.getRes("width"), SCFX.getRes("height"));
		stage.setScene(scene);
		stage.setTitle("Traveling");
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
