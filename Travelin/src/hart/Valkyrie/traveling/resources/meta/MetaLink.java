package hart.Valkyrie.traveling.resources.meta;

public class MetaLink
{
	private int index;
	private String type;
	public MetaLink(String type,int index)
	{
		super();
		this.index = index;
		this.type = type;
	}
	
	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	
	
	
}
