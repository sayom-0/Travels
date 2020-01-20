package hart.Valkyrie.traveling.resources.planet;

import hart.Valkyrie.objects.NamedArrayList;
import hart.Valkyrie.traveling.resources.Item;

public class Planet
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

	public Planet(String name, char planetChar, int x, int y, boolean explore, boolean market, int risk, char pClass)
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
	
	
	
}
