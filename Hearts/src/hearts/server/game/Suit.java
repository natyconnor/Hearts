package hearts.server.game;

public enum Suit {
	CLUBS ("clubs"),
	DIAMONDS ("diamonds"),
	HEARTS ("hearts"),
	SPADES ("spades");
	
	String name;
	Suit(String name)
	{
		this.name = name;
	}
	
	public boolean equals(String s)
	{
		return name.equals(s);
	}
}
