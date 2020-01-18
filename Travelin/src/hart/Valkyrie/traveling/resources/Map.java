package hart.Valkyrie.traveling.resources;

import java.util.ArrayList;

import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.NamedArrayList;
import hart.Valkyrie.traveling.exceptions.InvalidMetaLinkException;
import hart.Valkyrie.traveling.resources.meta.MetaLink;

public class Map
{
	public String[][] rawmap;
	public MetaLink[][] metaMap;
	private char defaultChar;
	private char chestChar;
	private char playerChar;
	private ArrayList<Planet> planetArray;
	private char wallChar;
	private char lastChar;
	private String status;
	private int c_x;
	private int c_y;
	public Player ply;

	public Map(char defaultChar, char chestChar, char playerChar, char planetChar, char wallChar, int row, int col)
			throws DuplicateNameException
	{
		planetArray = new ArrayList<Planet>();
		this.defaultChar = defaultChar;
		this.chestChar = chestChar;
		this.playerChar = playerChar;
		this.wallChar = wallChar;
		rawmap = new String[row][col];
		metaMap = new MetaLink[row][col];
		makePlanet("Kharak", '@', randomX(), randomY());
		status = "";
		ply = new Player(getPlayerChar(), (int) (rawmap.length * 0.5) + 1, (int) (rawmap[0].length * 0.5) + 1,
				"Valkyrie");
	}

	public NamedArrayList<Item> getPlayerInv()
	{
		return ply.getInv();
	}

	public Planet getPL(int i) throws NonExistantDataException
	{
		return planetArray.get(i);
	}

	public void generate() throws NonExistantDataException
	{
		int row = 0;
		int col = 0;

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
		rawmap[getPL(0).getX()][getPL(0).getY()] = String.valueOf(getPL(0).getPlanetChar());
		rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());

	}

	public void updPlyCords() throws NonExistantDataException
	{
		status = "";
		lastChar = (rawmap[ply.getX()][ply.getY()]).charAt(0);
		System.out.println("updPly : ");
		System.out.println("Wiping X/Y _OLD");
		rawmap[ply.getX_old()][ply.getY_old()] = String.valueOf(lastChar);
		System.out.println("Drawing ply X/Y Char");
		if (rawmap[ply.getX()][ply.getY()].equals(String.valueOf(getDefaultChar())) == false)
		{
			c_x = ply.getX();
			c_y = ply.getY();
			ply.revertCords();

			if (rawmap[ply.getX()][ply.getY()].equals(String.valueOf(getChestChar())))
				status = "Chest";

			if (rawmap[ply.getX()][ply.getY()].equals(String.valueOf(getPL(0).getPlanetChar())))
				status = "Planet";

		} else
		{
			rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());
		}
		System.out.println("STATUS : " + status);
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

	public char getWallChar()
	{
		return wallChar;
	}

	public void setWallChar(char wallChar)
	{
		this.wallChar = wallChar;
	}

	public String getStatus()
	{
		return status;
	}

	public void makePlanet(String name, char planetChar, int x, int y) throws DuplicateNameException
	{
		metaMap[x][y] = new MetaLink("Planet", planetArray.size());
		planetArray.add(new Planet(name, planetChar, x, y));
	}

	public int randomX()
	{
		return (int) (rawmap.length * Math.random());
	}

	public int randomY()
	{
		return (int) (rawmap[0].length * Math.random());
	}

	public Object handleLink(MetaLink x) throws InvalidMetaLinkException
	{
		if (x.getType().equals("Planet"))
		{
			return planetArray.get(x.getIndex());
		} else
		{
			throw new InvalidMetaLinkException(
					"The MetaLinks registered Generic Type does not have a entry in the handleLink Method");
		}
	}

	public int getC_x()
	{
		return c_x;
	}

	public void setC_x(int c_x)
	{
		this.c_x = c_x;
	}

	public int getC_y()
	{
		return c_y;
	}

	public void setC_y(int c_y)
	{
		this.c_y = c_y;
	}

}
