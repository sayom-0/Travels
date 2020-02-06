package hart.Valkyrie.traveling.resources;

public class Sellable
{
	private String name;
	private int qty;
	private int cost;
	public static final int coincost = 1;

	public Sellable(String name, int qty, int cost)
	{
		super();
		this.name = name;
		this.qty = qty;
		this.cost = cost;
	}

	public String getName()
	{
		return name;
	}

	public int getQty()
	{
		return qty;
	}

	public int getCost()
	{
		return cost;
	}

	@Override
	public String toString()
	{
		return name + " | Qty: " + qty + " | Cost: " + cost + "";
	}

}
