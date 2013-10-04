package hearts.client.player;

import hearts.server.game.*;
import hearts.server.main.Server;

import java.util.ArrayList;

abstract public class Player {
	ArrayList<Card> hand;
	ArrayList<Card> clubs;
	ArrayList<Card> diamonds;
	ArrayList<Card> spades;
	ArrayList<Card> hearts;
	ArrayList<ArrayList<Card>> handBySuit;
	int score;
	String myName;
	Server myServer;
	
	public Player()
	{
		this("");
	}
	
	public Player(String name)
	{
		myName = name;
		hand     = new ArrayList<Card>();
		clubs    = new ArrayList<Card>();
		diamonds = new ArrayList<Card>();
		hearts   = new ArrayList<Card>();
		
		handBySuit = new ArrayList<ArrayList<Card>>();
		score = 0;
	}
	
	public void setServer(Server s) 
	{ 
		myServer = s; 
	}
	
	public void setHand(ArrayList<Card> cards)
	{
		hand = cards;
	}
	
	public void giveCard(Card c)
	{
		hand.add(c);
	}
	
	public void sortHand()
	{
		for(Card c : hand)
		{
			if(c.getSuit() == Suit.CLUBS)
				clubs.add(c);
			if(c.getSuit() == Suit.DIAMONDS)
				diamonds.add(c);
			if(c.getSuit() == Suit.SPADES)
				spades.add(c);
			if(c.getSuit() == Suit.HEARTS)
				hearts.add(c);
		}
		
		//Sort each suit
		bubbleSortCards(clubs);
		bubbleSortCards(diamonds);
		bubbleSortCards(spades);
		bubbleSortCards(hearts);
		
		handBySuit.add(clubs);
		handBySuit.add(diamonds);
		handBySuit.add(spades);
		handBySuit.add(hearts);
	}
	
	private void bubbleSortCards(ArrayList<Card> cards)
	{
		for(int i = 0; i < cards.size(); i++)
		{
			for(int j = 0; j < cards.size()-i; j++)
			{
				if(cards.get(j).getValue().getNum() >= clubs.get(j+1).getValue().getNum())
				{
					Card temp = cards.get(j);
					cards.set(j, cards.get(j+1));
					cards.set(j+1, temp);
				}
			}
		}
	}
	
	public boolean equals(Player p)
	{
		return myName.equals(p.myName);
	}
	
	public abstract void run();
	
	public abstract void playCardPrompt();
	
	public ArrayList<ArrayList<Card>> getHandBySuit()
	{
		return handBySuit;
	}
	
	public void playCard(Card c)
	{
		myServer.getGame().playCard(this, c);
		//remove card from hand
	}

}
