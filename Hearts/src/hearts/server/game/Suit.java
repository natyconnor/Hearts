package hearts.server.game;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public enum Suit {
	CLUBS ("clubs"),
	DIAMONDS ("diamonds"),
	SPADES ("spades"),
	HEARTS ("hearts");
	
	String name;
	Suit(String name)
	{
		this.name = name;
	}
	
	public boolean equals(String s)
	{
		return name.equals(s);
	}
	
	public static final List<Suit> SUITS = Collections.unmodifiableList(Arrays.asList(values()));
}
