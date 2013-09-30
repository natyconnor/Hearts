package hearts.server.game;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> myDeck;
	
	public Deck()
	{
		myDeck = new ArrayList<Card> ();
		for (Suit s : Suit.values())
		{
			for (Value v : Value.values())
			{
				myDeck.add(new Card(s, v));
			}
		}
	}
	
	public void shuffle()
	{
		Card[] temp = new Card[myDeck.size()];
		Random r = new Random();
		
		for(int i = 0; i < myDeck.size(); i++)
		{
			int index = r.nextInt(myDeck.size());
			if(temp[index] != null)
			{
				i--;
			}
			else
			{
				temp[index] = myDeck.get(i);
			}
		}
		
		for(int i = 0; i < myDeck.size(); i++){
			myDeck.set(i, temp[i]);
		}
	}
	
	public ArrayList<Card> getDeck()
	{
		return myDeck;
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for (Card c : myDeck)
		{
			s.append(c.toString());
			s.append("\n");
		}
		return s.toString();
	}
}
