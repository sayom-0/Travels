package hart.Valkyrie.traveling.resources;

import java.util.ArrayList;

import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.NamedArrayList;
import hart.Valkyrie.traveling.exceptions.InvalidMetaLinkException;
import hart.Valkyrie.traveling.resources.meta.MetaLink;
import hart.Valkyrie.traveling.resources.planet.Planet;
import hart.Valkyrie.util.TextGenerator;

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
	private int k;
	private TextGenerator names;
	public Player ply;

	public Map(char defaultChar, char chestChar, char playerChar, char planetChar, char wallChar, int row, int col)
			throws DuplicateNameException, IllegalDimensionsException, NonExistantDataException
	{
		k = 0;
		planetArray = new ArrayList<Planet>();
		this.defaultChar = defaultChar;
		this.chestChar = chestChar;
		this.playerChar = playerChar;
		this.wallChar = wallChar;
		rawmap = new String[row][col];
		metaMap = new MetaLink[row][col];
		names = new TextGenerator(new String[] { "Great", "The", "Blue", "Expansive", "Voyage", "Space", "Outpost", "Green" });
		makePlanet(names.name(3), '@', randomX(), randomY(), true, true, 0, 'M', "Full");
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
		rawmap[getPL(k).getX()][getPL(k).getY()] = String.valueOf(getPL(0).getPlanetChar());
		ply.setX((int) (rawmap.length * 0.5));
		ply.setY((int) (rawmap[0].length * 0.5));
		ply.setX_old(ply.getX());
		ply.setY_old(ply.getY());
		rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());

	}

	public void updPlyCords(Boolean lp) throws NonExistantDataException, DuplicateNameException, IllegalDimensionsException
	{
		try
		{
			lastChar = (rawmap[ply.getX()][ply.getY()]).charAt(0);
		} catch (ArrayIndexOutOfBoundsException ex)
		{
			k++;
			makePlanet(names.name(3), '@', randomX(), randomY(), true, true, 0, 'M', "Full");
			this.generate();
		}
		rawmap[ply.getX_old()][ply.getY_old()] = String.valueOf(lastChar);
		if (!rawmap[ply.getX()][ply.getY()].equals(String.valueOf(getDefaultChar())))
		{
			c_x = ply.getX();
			c_y = ply.getY();
			ply.revertCords();

			if (rawmap[c_x][c_y].equals(String.valueOf(getChestChar())))
				status = "Chest";

			if (rawmap[c_x][c_y].equals(String.valueOf(getPL(0).getPlanetChar())))
				status = "Planet";

			rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());

		} else
		{
			status = "";
			rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());
		}
		if (lp == true)
		{
			status = "Landed";
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

	public void makePlanet(String name, char planetChar, int x, int y, boolean explore, boolean market, int risk,
			char pClass, String mt) throws DuplicateNameException, IllegalDimensionsException, NonExistantDataException
	{
		metaMap[x][y] = new MetaLink("Planet", planetArray.size());
		planetArray.add(new Planet(name, planetChar, x, y, explore, market, risk, pClass, mt));
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

	public void setStatus(String status)
	{
		this.status = status;
	}

}
