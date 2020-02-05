package hart.Valkyrie.traveling.resources.interaction;

import java.util.ArrayList;
import hart.Valkyrie.SCFX.ScreenControllerFX;
import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.EventButtonManager;
import hart.Valkyrie.traveling.resources.Sellable;
import hart.Valkyrie.util.BWindow;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
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

		for (int x = 0; x != sellables.size(); x++)
		{
			String name = "GN" + x;
			String nameb = "GB" + x;
			String namesc = "GS" + x;
			String namesh = "GSH" + x;
			String nameuh = "GUH" + x;
			String namec = "GC" + x;

			ScrollBar sc = new ScrollBar();
			sc.setMin(0);
			sc.setMax(sellables.get(x).getQty());
			sc.setValue(0);
			sc.setOrientation(Orientation.HORIZONTAL);
			sc.setUnitIncrement(1);
			sc.setBlockIncrement(1);
			sc.setOnMouseMoved(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent event)
				{
					try
					{
						String s = namesh.substring(3, 4);
						int y = Integer.parseInt(s);
						SCFX.getText(namesc).setText("Buying : " + gv());
						SCFX.getText(namesh).setText("Shop : " + (sellables.get(y).getQty() - gv()));
						// SCFX.getText(nameuh).setText("Hold : " +);
						// SCFX.getText(namec).setText("Credits : " +);
					} catch (NonExistantDataException e)
					{
						e.printStackTrace();
					}
				}

				public int gv()
				{
					return (int) sc.getValue();
				}

			});

			SCFX.makeText(name, new Text(sellables.get(x).getName()), "Normal");
			SCFX.makeText(namesc, new Text("Buying : " + sc.getValue()), "Normal");
			SCFX.makeText(namesh, new Text("Shop : " + sellables.get(x).getQty()), "Normal");

			ebm.makeButton(nameb, new Button("Buy"));

			cAddRow(SCFX.getText(name), ebm.getButton(nameb), sc, new Text("Hold : "), new Text("Shop : "),
					new Text("Credits : "), SCFX.getText(namesc));
		}

		scene = new Scene(pHUD, SCFX.getRes("width"), SCFX.getRes("height"));
	}

	@Override
	public void start(Stage stage)
	{
		stage.setScene(scene);
		stage.setTitle("Market");
		stage.show();
	}

	public void cAddRow(Text name, Button b, ScrollBar sb, Text hold, Text shop, Text cred, Text buying)
	{
		BorderPane row = new BorderPane();
		VBox left = new VBox();
		VBox right = new VBox();
		HBox rightSC = new HBox();

		left.setAlignment(Pos.CENTER_RIGHT);
		left.setSpacing(5);
		left.getChildren().add(name);

		rightSC.setAlignment(Pos.CENTER);
		rightSC.setSpacing(5);
		rightSC.getChildren().add(hold);
		rightSC.getChildren().add(sb);
		rightSC.getChildren().add(shop);

		right.setAlignment(Pos.CENTER_LEFT);
		right.setSpacing(5);
		right.getChildren().add(cred);
		right.getChildren().add(rightSC);
		right.getChildren().add(buying);
		right.getChildren().add(b);

		row.setLeft(left);
		row.setRight(right);

		centre.getChildren().add(row);
	}
}
