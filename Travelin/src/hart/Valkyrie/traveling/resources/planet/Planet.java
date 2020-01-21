package hart.Valkyrie.traveling.resources.planet;

import hart.Valkyrie.SCFX.ScreenControllerFX;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.objects.EventButtonManager;
import hart.Valkyrie.objects.NamedArrayList;
import hart.Valkyrie.traveling.resources.Item;
import hart.Valkyrie.traveling.resources.interaction.Explore;
import hart.Valkyrie.traveling.resources.interaction.Market;
import hart.Valkyrie.util.BWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Planet extends BWindow
{
	private String name;
	private char planetChar;
	private NamedArrayList<Item> inv;
	private int x;
	private int y;
	private boolean explore;
	private boolean market;
	private char pClass;
	private int risk;
	private Market mk;
	private Explore ex;
	private ScreenControllerFX SCFX;
	private EventButtonManager ebm;
	private String marketType;

	public Planet(String name, char planetChar, int x, int y, boolean explore, boolean market, int risk, char pClass,
			String marketType) throws IllegalDimensionsException
	{
		super();
		this.name = name;
		this.planetChar = planetChar;
		this.x = x;
		this.y = y;
		this.explore = explore;
		this.market = market;
		this.risk = risk;
		this.pClass = pClass;
		this.marketType = marketType;
		SCFX = new ScreenControllerFX(400, 800);
		ebm = new EventButtonManager();
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

	public NamedArrayList<Item> getInv()
	{
		return inv;
	}

	public void setInv(NamedArrayList<Item> inv)
	{
		this.inv = inv;
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
		SCFX.makeFont("Title", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		SCFX.makeFont("SubTitle", Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

		SCFX.makeText("Title", new Text(name), "Title");
		pHUD.setTop(SCFX.getText("Title"));
		if (market)
		{
			mk = new Market(marketType);
			ebm.makeButton("oMarket", new Button("Enter the Market"), new EventHandler<ActionEvent>()
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

			});
		}

		if (explore)
		{
			ex = new Explore(pClass);
			ebm.makeButton("oExplore", new Button("Explore the Planet"), new EventHandler<ActionEvent>()
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

			});
		}

		Scene scene = new Scene(pHUD, SCFX.getRes("width"), SCFX.getRes("height"));
		stage.setScene(scene);
		stage.setTitle("Traveling");
		stage.show();
	}

}
