package hart.Valkyrie.traveling.resources;

import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.objects.NamedLists.NamedArrayList;
import hart.Valkyrie.traveling.ship.Ship;
import hart.Valkyrie.traveling.ship.module.modules.Armor;
import hart.Valkyrie.traveling.ship.module.modules.Engine;
import hart.Valkyrie.traveling.ship.module.modules.Shields;
import hart.Valkyrie.traveling.ship.module.modules.Tank;
import hart.Valkyrie.traveling.ship.module.modules.WeaponSet;

public class Player
{
	private char playerChar;
	private int x;
	private int y;
	private NamedArrayList<Sellable> inv;
	private int x_old;
	private int y_old;
	public Ship ship;

	public Player(char playerChar, int x, int y, String name, Integer sr) throws DuplicateNameException
	{
		super();
		this.playerChar = playerChar;
		this.x = x;
		this.y = y;
		inv = new NamedArrayList<>();
		x_old = x;
		y_old = y;
		inv.add("Credits", new Sellable("Credits", 50, Sellable.coincost));
		inv.add("Water", new Sellable("Water", 5, 3));
		inv.add("Ammo", new Sellable("Ammo", 5, 8));
		inv.add("Food", new Sellable("Food", 5, 4));
		inv.add("Fuel", new Sellable("Fuel", 5, 2));
		ship = new Ship(name, new Armor(15, 20, 30), new Engine(15, 20, 30), new Shields(15, 20, 30),
				new WeaponSet(15, 20, 30), new Tank(15, 20, 30, 0), sr);
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

	public NamedArrayList<Sellable> getInv()
	{
		return inv;
	}

}
