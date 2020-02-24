package npc;

import hart.Valkyrie.traveling.ship.Ship;

public class NPC
{
	private Ship ship;
	private boolean hostile;
	
	public NPC(boolean hostile, int sr)
	{
		this.hostile = hostile;
		this.ship = new Ship(hostile, sr);
	}

	public Ship getShip()
	{
		return ship;
	}

	public void setShip(Ship ship)
	{
		this.ship = ship;
	}

	public boolean isHostile()
	{
		return hostile;
	}

	public void setHostile(boolean hostile)
	{
		this.hostile = hostile;
	}

}
