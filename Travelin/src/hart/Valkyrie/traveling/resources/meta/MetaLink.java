package hart.Valkyrie.traveling.resources.meta;

public class MetaLink<T>
{
	private T local_t;
	private int index;
	
	public MetaLink(T type,int index)
	{
		super();
		local_t = type;
		this.index = index;
	}
	
	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public T getLocal_t()
	{
		return local_t;
	}
	
	
	
}
