package hart.Valkyrie.traveling.resources;

import hart.Valkyrie.objects.NamedArrayList;

public class Planet
{
	private String name;
	private char planetChar;
	private NamedArrayList<Item> inv;
	private int x;
	private int y;
	
	public Planet(String name, char planetChar, int x, int y)
	{
		this.name = name;
		this.planetChar = planetChar;
		this.x = x;
		this.y = y;
	}

	public Planet()
	{
		// TODO Auto-generated constructor stub
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
	
	
	
}
