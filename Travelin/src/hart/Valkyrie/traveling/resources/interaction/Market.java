package hart.Valkyrie.traveling.resources.interaction;

import java.util.ArrayList;
import hart.Valkyrie.SCFX.ScreenControllerFX;
import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.EventButtonManager;
import hart.Valkyrie.traveling.resources.Sellable;
import hart.Valkyrie.util.BWindow;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	private VBox centre;
	private BorderPane pHUD;

	public Market(String v) throws IllegalDimensionsException, DuplicateNameException, NonExistantDataException
	{
		switch (v)
		{
		case "Full":
			sellables.add(new Sellable("Water", 5, 1));
			sellables.add(new Sellable("Food", 5, 1));
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
		SCFX.makeFont("Normal", Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
		SCFX.makeText("Title", new Text("Market"), "Title");
		SCFX.makeText("mType", new Text("Market Type : " + v), "SubTitle");

		header = new VBox();
		header.setSpacing(10);
		header.setAlignment(Pos.CENTER);
		header.getChildren().add(SCFX.getText("Title"));
		header.getChildren().add(SCFX.getText("mType"));

		centre = new VBox();
		centre.setAlignment(Pos.CENTER);
		centre.setSpacing(5);

		pHUD = new BorderPane();
		pHUD.setTop(header);
		pHUD.setCenter(centre);
		int times = 0;
		for (int x = 0; x != sellables.size(); x++)
		{
			String name = "GN" + x;

			String nameb = "GB" + x;

			SCFX.makeText(name, new Text(sellables.get(x).getName()), "Normal");
			
			ScrollBar sc = new ScrollBar();
			sc.setMin(0);
			sc.setMax(sellables.get(x).getQty());
			sc.setValue(0);
			sc.setOrientation(Orientation.HORIZONTAL);
			sc.setUnitIncrement(1);
			sc.setBlockIncrement(1);
			
			ebm.makeButton(nameb, new Button("Buy"));
			
			cAddRow(SCFX.getText(name), ebm.getButton(nameb), sc);
			
			times++;
		}
		System.out.println(times);
	}

	@Override
	public void start(Stage stage)
	{
		stage.setScene(new Scene(pHUD, SCFX.getRes("width"), SCFX.getRes("height")));
		stage.setTitle("Market");
		stage.show();
	}

	public void cAddRow(Text name, Button b, ScrollBar sb)
	{
		BorderPane row = new BorderPane();
		VBox left = new VBox();
		VBox right = new VBox();

		left.setAlignment(Pos.CENTER_LEFT);
		left.setSpacing(5);
		left.getChildren().add(name);

		right.setAlignment(Pos.CENTER_RIGHT);
		right.setSpacing(5);
		right.getChildren().add(sb);
		right.getChildren().add(b);

		row.setLeft(left);
		row.setRight(right);

		centre.getChildren().add(row);
	}
}
