package hart.Valkyrie.traveling.ship;

import hart.Valkyrie.traveling.ship.module.modules.*;

public class Ship
{
	private Armor armor;
	private Engine engine;
	private Shields shields;
	private WeaponSet weaponset;
	private Tank tank;
	private String title;
	private int health;
	private int fe;
	private Integer sr;

	private void init()
	{
		health = armor.getEft() + shields.getEft();
		fe();
		System.out.println("Per Tile : " + fe);
	}

	public Ship(String title, Armor armor, Engine engine, Shields shields, WeaponSet weaponset, Tank tank, Integer sr)
	{
		super();
		this.title = title;
		this.armor = armor;
		this.engine = engine;
		this.shields = shields;
		this.weaponset = weaponset;
		this.tank = tank;
		this.sr = sr;
		init();
	}

	public Armor getArmor()
	{
		return armor;
	}

	public void setArmor(Armor armor)
	{
		this.armor = armor;
	}

	public Engine getEngine()
	{
		return engine;
	}

	public void setEngine(Engine engine)
	{
		this.engine = engine;
	}

	public Shields getShields()
	{
		return shields;
	}

	public void setShields(Shields shields)
	{
		this.shields = shields;
	}

	public WeaponSet getWeaponset()
	{
		return weaponset;
	}

	public void setWeaponset(WeaponSet weaponset)
	{
		this.weaponset = weaponset;
	}

	public int getRange()
	{
		fe();
		return (tank.getFuel() / fe);
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public Tank getTank()
	{
		return tank;
	}

	public void setTank(Tank tank)
	{
		this.tank = tank;
	}

	public boolean canFly()
	{
		return tank.getFuel() > 0 && engine.getHealth() > 0 ? true : false;
	}

	public void moved()
	{

		tank.setFuel(tank.getFuel() - fe);
	}

	public void fe()
	{
		fe = (int) (2 - (engine.getEft() * .1));
		if (0 >= fe)
			fe = 1;
	}

}
