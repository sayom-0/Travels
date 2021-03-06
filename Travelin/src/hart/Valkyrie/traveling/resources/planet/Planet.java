package hart.Valkyrie.traveling.resources.planet;

import java.io.IOException;

import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.ScreenControllerFX;
import hart.Valkyrie.objects.eventNodeManager.EventNodeManager;
import hart.Valkyrie.traveling.resources.ButtonLinker;
import hart.Valkyrie.traveling.resources.Player;
import hart.Valkyrie.traveling.resources.interaction.Explore;
import hart.Valkyrie.traveling.resources.interaction.Market;
import hart.Valkyrie.util.BWindow;
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

public class Planet extends BWindow
{
	private String name;
	private char planetChar;
	private int x;
	private int y;
	private boolean explore;
	private boolean market;
	private char pClass;
	private int risk;
	private Market mk;
	private Explore ex;
	private String marketType;
	private HBox header;
	private VBox center;
	private HBox bt;
	private EventNodeManager<Button, ActionEvent, ButtonLinker> ebm;

	public Planet(String name, char planetChar, int x, int y, boolean explore, boolean market, int risk, char pClass,
			String marketType, Player ply)
			throws IllegalDimensionsException, DuplicateNameException, NonExistantDataException, IOException
	{
		super();
		initd();
		this.name = name;
		this.planetChar = planetChar;
		this.x = x;
		this.y = y;
		this.explore = explore;
		this.market = market;
		this.risk = risk;
		this.pClass = pClass;
		this.marketType = marketType;
		SCFX = new ScreenControllerFX(400, 600);
		ebm = new EventNodeManager<>(new ButtonLinker());
		SCFX.setFont("Title", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		SCFX.setFont("SubTitle", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		SCFX.setText("Title", new Text(name), "Title");
		SCFX.setText("mType", new Text("Market Type : " + marketType), "SubTitle");

		center = new VBox();
		center.setSpacing(10);
		center.getChildren().add(SCFX.getText("mType"));

		header = new HBox();
		header.setAlignment(Pos.CENTER);
		header.getChildren().add(SCFX.getText("Title"));

		bt = new HBox();
		bt.setSpacing(20);
		bt.setPadding(new Insets(15, 15, 15, 15));
		bt.setAlignment(Pos.CENTER);

		if (market)
		{
			mk = new Market(marketType, ply);
			bt.getChildren().add(ebm.setNode("oMarket", new Button("Enter the Market"), new EventHandler<ActionEvent>()
			{

				@Override
				public void handle(ActionEvent event)
				{
					try
					{
						mk.window();
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}));
			indexSubBW(mk);
		}

		if (explore)
		{
			ex = new Explore(pClass);
			bt.getChildren()
					.add(ebm.setNode("oExplore", new Button("Explore the Planet"), new EventHandler<ActionEvent>()
					{

						@Override
						public void handle(ActionEvent event)
						{
							try
							{
								ex.window();
							} catch (Exception e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}));
			indexSubBW(ex);
		}

	}

	public char getPlanetChar()
	{
		return planetChar;
	}

	public void setPlanetChar(char planetChar)
	{
		this.planetChar = planetChar;
	}

	public String getName()
	{
		return name;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public boolean isExplore()
	{
		return explore;
	}

	public boolean isMarket()
	{
		return market;
	}

	public int getRisk()
	{
		return risk;
	}

	public char getpClass()
	{
		return pClass;
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		BorderPane pHUD = new BorderPane();
		pHUD.setTop(header);
		pHUD.setCenter(center);
		pHUD.setBottom(bt);
		Scene scene = new Scene(pHUD, SCFX.getRes("width"), SCFX.getRes("height"));
		stage.setScene(scene);
		stage.setTitle(name);
		stage.show();
	}

}
