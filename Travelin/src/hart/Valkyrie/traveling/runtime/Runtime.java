package hart.Valkyrie.traveling.runtime;

import java.util.ArrayList;
import hart.Valkyrie.SCFX.ScreenControllerFX;
import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.EventButtonManager;
import hart.Valkyrie.traveling.exceptions.InvalidMetaLinkException;
import hart.Valkyrie.traveling.resources.Map;
import hart.Valkyrie.util.Utils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import hart.Valkyrie.traveling.resources.planet.Planet;

public class Runtime extends Application
{
	final static double v = 0.8;
	HBox head;
	ScreenControllerFX SCFX;
	EventButtonManager ebm;
	Map map;
	ArrayList<Text> maptextarray;
	BorderPane HUD;
	VBox maptext;
	VBox inv;
	HBox mBG;
	ArrayList<Text> alog;
	VBox lG;
	boolean local_Planet;
	HBox infoHUD;
	HBox shipHUD;
	HBox targetHUD;
	int counter = 0;
	int lcx;
	int lcy;
	VBox lGt;

	@Override
	public void start(Stage stage) throws DuplicateNameException, IllegalDimensionsException, NonExistantDataException
	{
		head = new HBox();
		lGt = new VBox();
		maptextarray = new ArrayList<Text>();
		HUD = new BorderPane();
		maptext = new VBox();
		lG = new VBox();
		shipHUD = new HBox();
		local_Planet = false;
		infoHUD = new HBox();
		targetHUD = new HBox();
		alog = new ArrayList<Text>();
		mBG = new HBox();
		inv = new VBox();
		map = new Map('#', '=', 'O', '8', '*', 70, 30);
		SCFX = new ScreenControllerFX(800, 600);
		ebm = new EventButtonManager();
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

		ebm.makeButton("iLand", new Button("Land"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				try
				{
					Planet x = (Planet) map.handleLink(map.metaMap[map.getC_x()][map.getC_y()]);
					addLog(new Text("Landed on : " + x.getName()));
					local_Planet = true;
					map.setStatus("Landed");
					lcx = map.getC_x();
					lcy = map.getC_y();
					x.window();
					reDraw();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		ebm.makeButton("iLaunch", new Button("Launch"), new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					Planet x = (Planet) map.handleLink(map.metaMap[lcx][lcy]);
					addLog(new Text("Launched from : " + x.getName()));
					local_Planet = false;
					map.setStatus("");
					reDraw();
				} catch (NonExistantDataException | InvalidMetaLinkException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		SCFX.makeFont("Title", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		SCFX.makeFont("SubTitle", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

		SCFX.makeText("InvTitle", new Text("Inventory"), "Title");
		SCFX.makeText("LogTitle", new Text("Events"), "Title");
		SCFX.makeText("Info", new Text("Information"), "Title");
		SCFX.makeText("GTitle", new Text("Traveling Version " + v), "Title");
		head.getChildren().add(SCFX.getText("GTitle"));
		head.setAlignment(Pos.CENTER);
		head.setSpacing(0);
		HUD.setTop(head);
		inv.getChildren().add(SCFX.getText("InvTitle"));
		inv.getChildren().add(new Text(map.getPlayerInv().toString()));
		HUD.setCenter(maptext);
		maptext.setAlignment(Pos.CENTER);
		HUD.setLeft(inv);
		maptext.setSpacing(1);
		map.generate();
		mBG.setSpacing(20);
		mBG.setPadding(new Insets(15, 15, 15, 15));
		mBG.getChildren().addAll(ebm.getButton("mUP"), ebm.getButton("mDown"), ebm.getButton("mLeft"),
				ebm.getButton("mRight"));
		mBG.setAlignment(Pos.CENTER);
		lG.setAlignment(Pos.TOP_LEFT);
		HUD.setBottom(mBG);
		lGt.getChildren().add(SCFX.getText("LogTitle"));
		lGt.getChildren().add(lG);
		HUD.setRight(lGt);
		lG.getChildren().add(new Text("gamestart"));

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
		while (counter != map.rawmap[counter].length)
		{
			maptextarray.add(new Text((String) Utils.getArrayRow(counter, map.rawmap)));
			counter++;
		}
		counter = 0;

		while (counter != map.rawmap[counter].length)
		{
			maptext.getChildren().add(maptextarray.get(counter));
			counter++;
		}
		counter = 0;
	}

	public void reDraw() throws NonExistantDataException
	{
		map.updPlyCords(local_Planet);
		HUDCTL();
		while (counter != map.rawmap[counter].length)
		{
			maptextarray.remove(0);
			counter++;
		}
		counter = 0;

		while (counter != map.rawmap[counter].length)
		{
			maptext.getChildren().remove(0);
			counter++;
		}
		counter = 0;
		draw();
	}

	public void HUDCTL() throws NonExistantDataException
	{
		switch (map.getStatus())
		{
		case "Planet":
			if (!mBG.getChildren().contains(ebm.getButton("iLand")))
			{
				mBG.getChildren().add(ebm.getButton("iLand"));
			}
			break;

		case "Landed":

			if (mBG.getChildren().contains(ebm.getButton("iLand")))
			{
				mBG.getChildren().remove(ebm.getButton("iLand"));
			}
			if (!mBG.getChildren().contains(ebm.getButton("iLaunch")))
			{
				mBG.getChildren().add(ebm.getButton("iLaunch"));
			}

			if (mBG.getChildren().contains(ebm.getButton("mUP")) && mBG.getChildren().contains(ebm.getButton("mRight"))
					&& mBG.getChildren().contains(ebm.getButton("mLeft"))
					&& mBG.getChildren().contains(ebm.getButton("mDown")))
			{
				mBG.getChildren().removeAll(ebm.getButton("mUP"), ebm.getButton("mRight"), ebm.getButton("mLeft"),
						ebm.getButton("mDown"));
			}

			break;

		default:
			if (mBG.getChildren().contains(ebm.getButton("iLand")))
			{
				mBG.getChildren().remove(ebm.getButton("iLand"));
			}

			if (mBG.getChildren().contains(ebm.getButton("iLaunch")))
			{
				mBG.getChildren().remove(ebm.getButton("iLaunch"));
			}

			if (!mBG.getChildren().contains(ebm.getButton("mUP"))
					&& !mBG.getChildren().contains(ebm.getButton("mRight"))
					&& !mBG.getChildren().contains(ebm.getButton("mLeft"))
					&& !mBG.getChildren().contains(ebm.getButton("mDown")))
			{
				mBG.getChildren().addAll(ebm.getButton("mUP"), ebm.getButton("mDown"), ebm.getButton("mLeft"),
						ebm.getButton("mRight"));
			}

			break;
		}
	}

	public void addLog(Text e)
	{
		int x = alog.size() - 1;
		lG.getChildren().clear();
		lG.getChildren().add(e);

		while (alog.size() != x)
		{
			if (0 > x)
			{
				break;
			}
			lG.getChildren().add(alog.get(x));
			x--;
		}
		alog.add(e);
	}

}
