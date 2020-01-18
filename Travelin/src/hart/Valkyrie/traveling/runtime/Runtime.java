package hart.Valkyrie.traveling.runtime;

import java.util.ArrayList;

import hart.Valkyrie.SCFX.ScreenControllerFX;
import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.EventButtonManager;
import hart.Valkyrie.traveling.resources.Map;
import hart.Valkyrie.util.Utils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
	Map map;
	ArrayList<Text> maptextarray = new ArrayList<Text>();
	BorderPane HUD = new BorderPane();
	VBox maptext = new VBox();
	VBox inv = new VBox();
	HBox mBG = new HBox();
	int counter = 0;

	@Override
	public void start(Stage stage) throws DuplicateNameException, IllegalDimensionsException, NonExistantDataException
	{
		map = new Map('#', '=', 'O', '8', '*', 50, 20);
		ScreenControllerFX SCFX = new ScreenControllerFX(800, 600);
		EventButtonManager ebm = new EventButtonManager();
		ebm.makeButton("mUP", new Button("Up"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				System.out.println("Move : Up :");
				map.ply.setY(map.ply.getY() - 1);
				map.ply.setX(map.ply.getX());
				try
				{
					reDraw();
				} catch (NonExistantDataException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		ebm.makeButton("mDown", new Button("Down"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				System.out.println("Move : Down :");
				map.ply.setY(map.ply.getY() + 1);
				map.ply.setX(map.ply.getX());
				try
				{
					reDraw();
				} catch (NonExistantDataException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		ebm.makeButton("mLeft", new Button("Left"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				System.out.println("Move : Left :");
				map.ply.setX(map.ply.getX() - 1);
				map.ply.setY(map.ply.getY());
				try
				{
					reDraw();
				} catch (NonExistantDataException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		ebm.makeButton("mRight", new Button("Right"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				System.out.println("Move : Right :");
				map.ply.setX(map.ply.getX() + 1);
				map.ply.setY(map.ply.getY());
				try
				{
					reDraw();
				} catch (NonExistantDataException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		SCFX.makeText("InvTitle", new Text("Inventory"),
				Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		inv.getChildren().add(SCFX.getText("InvTitle"));
		inv.getChildren().add(new Text(map.getPlayerInv().toString()));
		HUD.setCenter(maptext);
		HUD.setLeft(inv);
		maptext.setSpacing(1);
		map.generate();
		mBG.setSpacing(20);
		mBG.getChildren().addAll(ebm.getButton("mUP"),ebm.getButton("mDown"),ebm.getButton("mLeft"),ebm.getButton("mRight"));
		
		HUD.setBottom(mBG);

		draw();

		Scene scene = new Scene(HUD, SCFX.getRes("width"), SCFX.getRes("height"));
		stage.setScene(scene);
		stage.setTitle("Traveling");
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public void draw()
	{
		System.out.println("DRAW : ");
		System.out.println("Loading map.rawmap into maptextarray...");
		while (counter != map.rawmap[counter].length)
		{
			maptextarray.add(new Text((String) Utils.getArrayRow(counter, map.rawmap)));
			counter++;
		}
		counter = 0;

		System.out.println("Loading maptextarray into maptext Vbox");
		while (counter != map.rawmap[counter].length)
		{
			maptext.getChildren().add(maptextarray.get(counter));
			counter++;
		}
		counter = 0;
	}
	
	public void reDraw() throws NonExistantDataException
	{
		System.out.println("REDRAW : ");
		map.updPlyCords();
		System.out.println("Wiping maptextarray");
		while (counter != map.rawmap[counter].length)
		{
			maptextarray.remove(0);
			counter++;
		}
		counter = 0;

		System.out.println("Wiping maptext VBox");
		while (counter != map.rawmap[counter].length)
		{
			maptext.getChildren().remove(0);
			counter++;
		}
		counter = 0;
		draw();
	}

}
