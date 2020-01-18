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
	private MetaLink[][] metaMap;
	private char defaultChar;
	private char chestChar;
	private char playerChar;
	private ArrayList<Planet> planetArray;
	private char wallChar;
	private char lastChar;
	private String status;
	public Player ply;
	private String planetChars;

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
		planetChars = "";
		makePlanet("Kharak", '@', randomX(), randomY());

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
			ply.revertCords();

			if (rawmap[ply.getX()][ply.getY()].equals(String.valueOf(getChestChar())))
				status = "Chest";

			if (rawmap[ply.getX()][ply.getY()].equals(String.valueOf(getPL(0).getPlanetChar())))
				status = "Planet";

		} else
		{
			rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());
		}
		System.out.println("STATUS : "+status);
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
		planetChars += planetChar;
		metaMap[x][y] = new MetaLink<Planet>(new Planet(), planetArray.size());
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

	public <T> Object handleLink(MetaLink<T> x) throws InvalidMetaLinkException
	{
		if (x.getLocal_t().equals(new Planet()))
		{
			return planetArray.get(x.getIndex());
		}

		throw new InvalidMetaLinkException(
				"The MetaLinks registered Generic Type does not have a entry in the handleLink Method");
	}

}
