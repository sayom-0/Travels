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
	private int sr;
	private int ShieldHealth;
	private int ArmorHealth;
	private boolean npc;
	private boolean hostile;

	private void init()
	{
		health = armor.getEft() + shields.getEft();
		fe();
	}

	public Ship(String title, Armor armor, Engine engine, Shields shields, WeaponSet weaponset, Tank tank)
	{
		super();
		this.npc = false;
		this.title = title;
		this.armor = armor;
		this.engine = engine;
		this.shields = shields;
		this.weaponset = weaponset;
		this.tank = tank;
		init();
	}
	
	public Ship(boolean hostile, int sr)
	{
		this.npc = true;
		this.hostile = hostile;
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
	
	public boolean isDestroyed()
	{
		return ArmorHealth > 0 ? true : false;
	}
	
	public int dealDmg()
	{
		return 0;
	}
	
	public void reciveDmg(int dmg)
	{
		if(isShielded())
			ShieldHealth -= dmg;
		else
			ArmorHealth -= dmg;
	}
	
	public boolean isShielded()
	{
		return ShieldHealth > 0 ? true : false;
	}

	public int getShieldHealth()
	{
		return ShieldHealth;
	}

	public void setShieldHealth(int shieldHealth)
	{
		ShieldHealth = shieldHealth;
	}

	public int getArmorHealth()
	{
		return ArmorHealth;
	}

	public void setArmorHealth(int armorHealth)
	{
		ArmorHealth = armorHealth;
	}

	public boolean isNpc()
	{
		return npc;
	}

	public void setNpc(boolean npc)
	{
		this.npc = npc;
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
