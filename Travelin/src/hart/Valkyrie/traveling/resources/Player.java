package hart.Valkyrie.traveling.resources;

import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.objects.NamedArrayList;

public class Player
{
	private char playerChar;
	private int x;
	private int y;
	private NamedArrayList inv;

	public Player(char playerChar, int x, int y)
	{
		super();
		this.playerChar = playerChar;
		this.x = x;
		this.y = y;
	}

	public Player(char playerChar, int x, int y, NamedArrayList inv)
	{
		super();
		this.playerChar = playerChar;
		this.x = x;
		this.y = y;
		this.inv = inv;
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
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public NamedArrayList getInv()
	{
		return inv;
	}

	public void addInv(Item item)
	{
		try
		{
			inv.add(item.getName(), item);
		} catch (DuplicateNameException e)
		{
			e.printStackTrace();
		}
	}

}
