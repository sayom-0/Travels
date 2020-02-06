package hart.Valkyrie.traveling.runtime;

import java.util.ArrayList;
import java.util.LinkedList;
import hart.Valkyrie.SCFX.ScreenControllerFX;
import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.EventNodeManager;
import hart.Valkyrie.traveling.exceptions.InvalidMetaLinkException;
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
	final static String v = "Alpha 10.7";
	VBox head;
	ScreenControllerFX SCFX;
	EventNodeManager<Button> ebm;
	Map map;
	LinkedList<Text> maptextarray;
	BorderPane HUD;
	VBox maptext;
	VBox inv;
	HBox mBG;
	ArrayList<Text> alog;
	VBox lG;
	boolean local_Planet;
	HBox targetHUD;
	int counter = 0;
	int lcx;
	int lcy;
	VBox lGt;
	BorderPane subHead;
	VBox subHeadLeft;
	VBox subHeadRight;
	String pName;

	@Override
	public void start(Stage stage) throws DuplicateNameException, IllegalDimensionsException, NonExistantDataException
	{
		pName = "N/A";
		subHead = new BorderPane();
		subHeadLeft = new VBox();
		subHeadRight = new VBox();
		head = new VBox();
		lGt = new VBox();
		maptextarray = new LinkedList<Text>();
		HUD = new BorderPane();
		maptext = new VBox();
		lG = new VBox();
		local_Planet = false;
		targetHUD = new HBox();
		alog = new ArrayList<Text>();
		mBG = new HBox();
		inv = new VBox();
		map = new Map('#', '=', 'O', '8', '*', 70, 20);
		SCFX = new ScreenControllerFX(1000, 600);
		ebm = new EventNodeManager<Button>(".setOnAction");
		ebm.makeButton("mUP", new Button("Up"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				map.ply.setY(map.ply.getY() - 1);
				map.ply.setX(map.ply.getX());
				try
				{
					pStat();
					reDraw();
				} catch (NonExistantDataException | DuplicateNameException | IllegalDimensionsException
						| InvalidMetaLinkException e1)
				{
					e1.printStackTrace();
				}
			}
		});

		ebm.makeButton("mDown", new Button("Down"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				map.ply.setY(map.ply.getY() + 1);
				map.ply.setX(map.ply.getX());
				try
				{
					pStat();
					reDraw();
				} catch (NonExistantDataException | DuplicateNameException | IllegalDimensionsException
						| InvalidMetaLinkException e1)
				{
					e1.printStackTrace();
				}
			}
		});

		ebm.makeButton("mLeft", new Button("Left"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				map.ply.setX(map.ply.getX() - 1);
				map.ply.setY(map.ply.getY());
				try
				{
					pStat();
					reDraw();
				} catch (NonExistantDataException | DuplicateNameException | IllegalDimensionsException
						| InvalidMetaLinkException e1)
				{
					e1.printStackTrace();
				}
			}
		});

		ebm.makeButton("mRight", new Button("Right"), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				map.ply.setX(map.ply.getX() + 1);
				map.ply.setY(map.ply.getY());
				try
				{
					pStat();
					reDraw();
				} catch (NonExistantDataException | DuplicateNameException | IllegalDimensionsException
						| InvalidMetaLinkException e1)
				{
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
					pStat(x);
					local_Planet = true;
					map.setStatus("Landed");
					lcx = map.getC_x();
					lcy = map.getC_y();
					x.window();
					reDraw();
				} catch (Exception e1)
				{
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
					pStat();
					local_Planet = false;
					map.setStatus("");
					reDraw();
				} catch (NonExistantDataException | InvalidMetaLinkException | DuplicateNameException
						| IllegalDimensionsException e)
				{
					e.printStackTrace();
				}
			}

		});
		SCFX.makeFont("Title", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		SCFX.makeFont("SubTitle", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		SCFX.makeFont("Map Text", Font.font("verdana", FontWeight.THIN, FontPosture.REGULAR, 10));
		
		SCFX.makeText("InvTitle", new Text("Inventory"), "Title");
		SCFX.makeText("LogTitle", new Text("Events"), "Title");
		SCFX.makeText("Info", new Text("Information"), "Title");
		SCFX.makeText("GTitle", new Text("Traveling Version " + v), "Title");
		SCFX.makeText("Ship", new Text("Ship"), "Title");
		SCFX.makeText("Planet", new Text("Planet"), "Title");
		SCFX.makeText("sName", new Text("Ship Name : " + map.ply.ship.getTitle()), "SubTitle");
		SCFX.makeText("pName", new Text("Planet Name : N/A"), "SubTitle");
		SCFX.makeText("pClass", new Text("Planet Class : N/A"), "SubTitle");
		SCFX.makeText("pRisk", new Text("Risk : N/A"), "SubTitle");
		SCFX.makeText("pMarket", new Text("Market : N/A"), "SubTitle");
		SCFX.makeText("pExplore", new Text("Explorable : N/A"), "SubTitle");
		SCFX.makeText("sDang", new Text("Dangometer : " + map.getSectorRisk()), "SubTitle");
		SCFX.makeText("sHealth", new Text("Health : " + map.ply.ship.getHealth()), "SubTitle");
		SCFX.makeText("sRange", new Text("Range : " + map.ply.ship.getRange()), "SubTitle");
		
		subHead.setLeft(subHeadLeft);
		subHead.setRight(subHeadRight);

		subHeadLeft.setAlignment(Pos.CENTER_LEFT);
		subHeadLeft.setSpacing(5);

		subHeadRight.setAlignment(Pos.CENTER_RIGHT);
		subHeadRight.setSpacing(5);

		subHeadLeft.getChildren().add(SCFX.getText("Ship"));
		subHeadLeft.getChildren().add(SCFX.getText("sName"));
		subHeadLeft.getChildren().add(SCFX.getText("sDang"));
		subHeadLeft.getChildren().add(SCFX.getText("sHealth"));
		subHeadLeft.getChildren().add(SCFX.getText("sRange"));

		subHeadRight.getChildren().add(SCFX.getText("Planet"));
		subHeadRight.getChildren().add(SCFX.getText("pName"));
		subHeadRight.getChildren().add(SCFX.getText("pClass"));
		subHeadRight.getChildren().add(SCFX.getText("pRisk"));
		subHeadRight.getChildren().add(SCFX.getText("pMarket"));
		subHeadRight.getChildren().add(SCFX.getText("pExplore"));

		head.getChildren().add(SCFX.getText("GTitle"));
		head.getChildren().add(subHead);
		head.setAlignment(Pos.CENTER);
		head.setSpacing(30);
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
		stage.setTitle("Traveling " + v);
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public void pStat(Planet x) throws NonExistantDataException
	{
		SCFX.getText("pName").setText("Name : " + x.getName());
		SCFX.getText("pClass").setText("Class : " + x.getpClass());
		SCFX.getText("pRisk").setText("Risk : " + x.getRisk());
		SCFX.getText("pMarket").setText("Market : " + x.isMarket());
		SCFX.getText("pExplore").setText("Explorable : " + x.isExplore());
		SCFX.getText("sDang").setText("Dangometer : " + map.getSectorRisk());
		SCFX.getText("sName").setText("Ship Name : " + map.ply.ship.getTitle());
		SCFX.getText("sHealth").setText("Health : " + map.ply.ship.getHealth());
		SCFX.getText("sRange").setText("Range : " + map.ply.ship.getRange());

	}

	public void pStat() throws NonExistantDataException
	{
		pName = "N/A";
		SCFX.getText("pName").setText("Name : " + pName);
		SCFX.getText("pClass").setText("Class : " + pName);
		SCFX.getText("pRisk").setText("Risk : N/A");
		SCFX.getText("pMarket").setText("Market : N/A");
		SCFX.getText("pExplore").setText("Explorable : N/A");
		SCFX.getText("sDang").setText("Dangometer : " + map.getSectorRisk());
		SCFX.getText("sName").setText("Ship Name : " + map.ply.ship.getTitle());
		SCFX.getText("sHealth").setText("Health : " + map.ply.ship.getHealth());
		SCFX.getText("sRange").setText("Range : " + map.ply.ship.getRange());
		if (map.getStatus().equals("Planet"))
			pStat(map.getTargetedPlanet());
	}

	public void draw() throws NonExistantDataException
	{
		while (counter != map.rawmap[counter].length)
		{
			maptextarray.add(SCFX.buildText(new Text((String) Utils.getArrayRow(counter, map.rawmap)), "Map Text"));
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

	public void reDraw() throws NonExistantDataException, DuplicateNameException, IllegalDimensionsException,
			InvalidMetaLinkException
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
