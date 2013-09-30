package hearts.server.game;

public class Card {
	private Suit mySuit;
	private Value myValue;
	
	public Card(Suit suit, Value value)
	{
		mySuit = suit;
		myValue = value;
	}
	
	
	/**
	 * Accessors
	 */
	public Suit getSuit()   { return mySuit;           }
	public Value getValue()	{ return myValue;          }
	public int getNum()     { return myValue.getNum(); }
	
	public String toString()
	{
		return myValue + " of " + mySuit;
	}
}
