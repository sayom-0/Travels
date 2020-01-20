package hart.Valkyrie.traveling.resources;

import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.objects.NamedArrayList;

public class Player
{
	private char playerChar;
	private int x;
	private int y;
	private NamedArrayList<Item> inv;
	private int x_old;
	private int y_old;
	private String shipName;
	private int shipHealth;
	private int shipDmg;
	private int shipSpeed;

	public Player(char playerChar, int x, int y, String name) throws DuplicateNameException
	{
		super();
		this.playerChar = playerChar;
		this.x = x;
		this.y = y;
		inv = new NamedArrayList<Item>();
		this.shipName = name;
		x_old = x;
		y_old = y;
		inv.add("Coins", new Item("Coins",5));
	}

	public Player(char playerChar, int x, int y, String name,NamedArrayList<Item> inv)
	{
		super();
		this.playerChar = playerChar;
		this.x = x;
		this.y = y;
		this.inv = inv;
		inv = new NamedArrayList<Item>();
		this.shipName = name;
		x_old = x;
		y_old = y;
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
		System.out.println("X :"+getX());
		System.out.println("X_old :"+getX_old());
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		y_old = getY();
		this.y = y;
		System.out.println("Y :"+getY());
		System.out.println("Y_old :"+getY_old());
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
	
	

	public String getShipName()
	{
		return shipName;
	}

	public void setShipName(String shipName)
	{
		this.shipName = shipName;
	}

	public int getShipHealth()
	{
		return shipHealth;
	}

	public void setShipHealth(int shipHealth)
	{
		this.shipHealth = shipHealth;
	}

	public int getShipDmg()
	{
		return shipDmg;
	}

	public void setShipDmg(int shipDmg)
	{
		this.shipDmg = shipDmg;
	}

	public int getShipSpeed()
	{
		return shipSpeed;
	}

	public void setShipSpeed(int shipSpeed)
	{
		this.shipSpeed = shipSpeed;
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
