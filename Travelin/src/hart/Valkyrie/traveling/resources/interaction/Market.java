package hart.Valkyrie.traveling.resources.interaction;

import java.util.ArrayList;
import hart.Valkyrie.SCFX.ScreenControllerFX;
import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.EventButtonManager;
import hart.Valkyrie.traveling.resources.Sellable;
import hart.Valkyrie.util.BWindow;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Market extends BWindow
{
	ArrayList<Sellable> sellables = new ArrayList<Sellable>();
	private VBox header;

	public Market(String v) throws IllegalDimensionsException, DuplicateNameException, NonExistantDataException
	{
		switch (v)
		{
		case "Full":
			sellables.add(new Sellable("Ammo", 5, 1));
			sellables.add(new Sellable("Fuel", 5, 1));
			break;

		case "War":
			sellables.add(new Sellable("Ammo", 5, 1));
			sellables.add(new Sellable("Fuel", 5, 1));
			break;

		case "Sup":
			sellables.add(new Sellable("Water", 5, 1));
			sellables.add(new Sellable("Food", 5, 1));
			break;
		}

		SCFX = new ScreenControllerFX(500, 500);
		ebm = new EventButtonManager();
		SCFX.makeFont("Title", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		SCFX.makeFont("SubTitle", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		SCFX.makeText("Title", new Text("Market"), "Title");
		SCFX.makeText("mType", new Text("Market Type : " + v), "SubTitle");
		header = new VBox();
		header.setSpacing(10);
		header.setAlignment(Pos.CENTER);
		header.getChildren().add(SCFX.getText("Title"));
		header.getChildren().add(SCFX.getText("mType"));
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		BorderPane pHUD = new BorderPane();
		pHUD.setTop(header);
		Scene scene = new Scene(pHUD, SCFX.getRes("width"), SCFX.getRes("height"));
		stage.setScene(scene);

		stage.setTitle("Market");
		stage.show();
	}
}
