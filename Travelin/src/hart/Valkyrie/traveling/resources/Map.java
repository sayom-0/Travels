package hart.Valkyrie.traveling.resources;

import hart.Valkyrie.objects.NamedArrayList;

public class Map
{
	public String[][] rawmap;
	private char defaultChar;
	private char chestChar;
	private char playerChar;
	private char planetChar;
	private char wallChar;
	private char lastChar;
	public Player ply;

	public Map(char defaultChar, char chestChar, char playerChar, char planetChar, char wallChar)
	{
		super();
		this.defaultChar = defaultChar;
		this.chestChar = chestChar;
		this.playerChar = playerChar;
		this.planetChar = planetChar;
		this.wallChar = wallChar;
		rawmap = new String[15][15];
		ply = new Player(getPlayerChar(), (int) (rawmap.length * 0.5) + 1, (int) (rawmap[0].length * 0.5) + 1, "Valkyrie");
	}

	public Map(char defaultChar, char chestChar, char playerChar, char planetChar, char wallChar, int row, int col)
	{
		super();
		this.defaultChar = defaultChar;
		this.chestChar = chestChar;
		this.playerChar = playerChar;
		this.planetChar = planetChar;
		this.wallChar = wallChar;
		rawmap = new String[row][col];
		ply = new Player(getPlayerChar(), (int) (rawmap.length * 0.5) + 1, (int) (rawmap[0].length * 0.5) + 1, "Valkyrie");
	}

	public NamedArrayList<Item> getPlayerInv()
	{
		return ply.getInv();
	}

	public void generate(String type)
	{
		int row = 0;
		int col = 0;

		switch (type)
		{
		case "island":

			break;

		case "field":

			while (row != (rawmap.length))
			{

				while (col != (rawmap[row].length))
				{
					rawmap[row][col] = String.valueOf(getDefaultChar());
					col++;
				}
				col = 0;
				row++;
			}

			rawmap[(int) (row * 0.5)][(int) (rawmap[0].length * 0.5)] = String.valueOf(getPlanetChar());
			rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());

			break;

		case "underground":

			break;
		}
	}
	
	public void updPlyCords()
	{
		lastChar = (rawmap[ply.getX()][ply.getY()]).charAt(0);
		System.out.println("updPly : ");
		System.out.println("Wiping X/Y _OLD");
		rawmap[ply.getX_old()][ply.getY_old()] = String.valueOf(lastChar);
		System.out.println("Drawing ply X/Y Char");
		rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());
	}

	public char getDefaultChar()
	{
		return defaultChar;
	}

	public void setDefaultChar(char defaultChar)
	{
		this.defaultChar = defaultChar;
	}

	public char getChestChar()
	{
		return chestChar;
	}

	public void setChestChar(char chestChar)
	{
		this.chestChar = chestChar;
	}

	public char getPlayerChar()
	{
		return playerChar;
	}

	public void setPlayerChar(char playerChar)
	{
		this.playerChar = playerChar;
	}

	public char getPlanetChar()
	{
		return planetChar;
	}

	public void setPlanetChar(char tardisChar)
	{
		this.planetChar = tardisChar;
	}

	public char getWallChar()
	{
		return wallChar;
	}

	public void setWallChar(char wallChar)
	{
		this.wallChar = wallChar;
	}

}
