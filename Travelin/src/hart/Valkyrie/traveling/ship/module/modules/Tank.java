package hart.Valkyrie.traveling.ship.module.modules;

import hart.Valkyrie.traveling.ship.module.Module;

public class Tank extends Module
{
	private int fuel;

	public Tank(int eft, int health, int cost, int fuel)
	{
		super(eft, health, cost);
	}

	public int getFuel()
	{
		return fuel;
	}

	public void setFuel(int fuel)
	{
		this.fuel = fuel;
	}

}
