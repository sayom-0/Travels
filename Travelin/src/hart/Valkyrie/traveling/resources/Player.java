package hart.Valkyrie.traveling.resources;

import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.objects.NamedLists.NamedArrayList;
import hart.Valkyrie.traveling.ship.Ship;

public class Player
{
	private char playerChar;
	private int x;
	private int y;
	private NamedArrayList<Item> inv;
	private int x_old;
	private int y_old;
	public Ship ship;

	public Player(char playerChar, int x, int y, String name) throws DuplicateNameException
	{
		super();
		this.playerChar = playerChar;
		this.x = x;
		this.y = y;
		inv = new NamedArrayList<Item>();
		x_old = x;
		y_old = y;
		inv.add("Coins", new Item("Coins", 5));
		ship = new Ship(name, null, null, null, null, null, 0);
	}

	public Player(char playerChar, int x, int y, String name, NamedArrayList<Item> inv)
	{
		super();
		this.playerChar = playerChar;
		this.x = x;
		this.y = y;
		this.inv = inv;
		inv = new NamedArrayList<Item>();
		x_old = x;
		y_old = y;
		ship = new Ship(name, null, null, null, null, null, 0);
	}

	public void revertCords()
	{
		x = x_old;
		y = y_old;
	}

	public char getPlayerChar()
	{
		return playerChar;
	}

	public void setPlayerChar(char playerChar)
	{
		this.playerChar = playerChar;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		x_old = getX();
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		y_old = getY();
		this.y = y;
	}

	public int getX_old()
	{
		return x_old;
	}

	public void setX_old(int x_old)
	{
		this.x_old = x_old;
	}

	public int getY_old()
	{
		return y_old;
	}

	public void setY_old(int y_old)
	{
		this.y_old = y_old;
	}

	public NamedArrayList<Item> getInv()
	{
		return inv;
	}

	public void addInv(Item item)
	{
		try
		{
			inv.add(item.getName(), item);
		} catch (DuplicateNameException e)
		{
			e.printStackTrace();
		}
	}

}
