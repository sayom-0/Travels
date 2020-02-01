package hart.Valkyrie.traveling.runtime;

import java.util.ArrayList;
import java.util.Random;
import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import hart.Valkyrie.objects.Reversable;
import hart.Valkyrie.objects.NamedLists.NamedArrayList;
import hart.Valkyrie.traveling.exceptions.InvalidMetaLinkException;
import hart.Valkyrie.traveling.resources.Item;
import hart.Valkyrie.traveling.resources.Player;
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
	private Reversable<Integer> kp;
	private int sp;
	private int sectorRisk;
	private TextGenerator names;
	public Player ply;
	private Random r;
	private Planet targetedPlanet;
	private boolean firstRun;

	public Map(char defaultChar, char chestChar, char playerChar, char planetChar, char wallChar, int row, int col)
			throws DuplicateNameException, IllegalDimensionsException, NonExistantDataException
	{
		firstRun = true;

		kp = new Reversable<Integer>(0);

		r = new Random();
		sectorRisk = r.nextInt(10);
		planetArray = new ArrayList<Planet>();
		this.defaultChar = defaultChar;
		this.chestChar = chestChar;
		this.playerChar = playerChar;
		this.wallChar = wallChar;
		rawmap = new String[row][col];
		metaMap = new MetaLink[row][col];
		names = new TextGenerator(new String[]
		{ "Great", "The", "Blue", "Expansive", "Voyage", "Space", "Outpost", "Green" });
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
		sp = kp.getValue() - kp.getLast();

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
		if (!firstRun)
		{
			for (int i = 0; i != sp; i++)
				rawmap[getPL(kp.getLast() + i).getX()][getPL(kp.getLast() + i).getY()] = String
						.valueOf(getPL(kp.getLast() + i).getPlanetChar());
		}
		ply.setX((int) (rawmap.length * 0.5));
		ply.setY((int) (rawmap[0].length * 0.5));
		ply.setX_old(ply.getX());
		ply.setY_old(ply.getY());
		rawmap[ply.getX()][ply.getY()] = String.valueOf(ply.getPlayerChar());
		firstRun = false;
	}

	public void updPlyCords(Boolean lp) throws NonExistantDataException, DuplicateNameException,
			IllegalDimensionsException, InvalidMetaLinkException
	{
		try
		{
			lastChar = (rawmap[ply.getX()][ply.getY()]).charAt(0);
		} catch (ArrayIndexOutOfBoundsException ex)
		{
			newSector();
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
			{
				status = "Planet";
				targetedPlanet = (Planet) handleLink(metaMap[c_x][c_y]);
			}

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

	public void newSector() throws DuplicateNameException, IllegalDimensionsException, NonExistantDataException
	{
		sectorRisk = r.nextInt(10);
		int x = r.nextInt(5) + 1;

		for (int pm = 0; pm != x; pm++)
			makePlanet(names.name(3), '@', randomX(), randomY(), true, true, sectorRisk, 'M', "Full");
		kp.setValue((x - 1) + kp.getValue());
		this.generate();
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

	public int getSectorRisk()
	{
		return sectorRisk;
	}

	public void setSectorRisk(int sectorRisk)
	{
		this.sectorRisk = sectorRisk;
	}

	public Planet getTargetedPlanet()
	{
		return targetedPlanet;
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

}
