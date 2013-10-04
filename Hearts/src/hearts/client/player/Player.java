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
		spades   = new ArrayList<Card>();
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
			for(int j = 1; j < cards.size(); j++)
			{
				if(cards.get(j).getValue().getNum() < cards.get(j-1).getValue().getNum())
				{
					Card temp = cards.get(j);
					cards.set(j, cards.get(j-1));
					cards.set(j-1, temp);
				}
			}
		}
	}
	
	public boolean have2Clubs()
	{
		if(clubs.size() > 0)
		{
			if(clubs.get(0).getValue() == Value.TWO)
			{
				return true;
			} else {
				return false;
			}
		} else {
			return false;
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
		//remove card from hand and 
		hand.remove(c);
		if(c.getSuit() == Suit.CLUBS)
			clubs.remove(c);
		else if(c.getSuit() == Suit.DIAMONDS)
			diamonds.remove(c);
		else if(c.getSuit() == Suit.SPADES)
			spades.remove(c);
		else if(c.getSuit() == Suit.HEARTS)
			hearts.remove(c);
		else
			System.out.print("Removing card doesn't have correct Suit!!");
	}
	
	public String toString()
	{
		return myName;
	}
	
	public String printHand()
	{
		return printClubs() + printDiamonds() + printSpades() + printHearts();
	}
	
	public String printClubs()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("CLUBS: ");
		sb.append(clubs.toString());
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String printDiamonds()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("DIAMONDS: ");
		sb.append(diamonds.toString());
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String printSpades()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SPADES: ");
		sb.append(spades.toString());
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String printHearts()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("HEARTS: ");
		sb.append(hearts.toString());
		sb.append("\n");
		
		return sb.toString();
	}

}
