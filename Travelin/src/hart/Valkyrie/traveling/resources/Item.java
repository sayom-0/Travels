package hart.Valkyrie.traveling.resources;

public class Item
{
	private String name;
	private int value;

	public Item(String name, int value)
	{
		super();
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return value + " " + name + "(S)";
	}
}
