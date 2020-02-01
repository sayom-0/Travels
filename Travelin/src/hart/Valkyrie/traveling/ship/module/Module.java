package hart.Valkyrie.traveling.ship.module;

public abstract class Module
{
	protected int eft;
	protected int health;
	protected int cost;
	private int sell;
	private int rebuy;

	public Module(int eft, int health, int cost)
	{
		super();
		this.eft = eft;
		this.health = health;
		this.cost = cost;
	}

	public int getEft()
	{
		return eft;
	}

	public void setEft(int eft)
	{
		this.eft = eft;
	}

	public int getCost()
	{
		return cost;
	}

	public void setCost(int cost)
	{
		this.cost = cost;
	}

	public int getSell()
	{
		return sell;
	}

	public int getRebuy()
	{
		return rebuy;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

}
