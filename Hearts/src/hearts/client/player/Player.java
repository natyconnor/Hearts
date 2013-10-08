package hearts.client.player;

import hearts.server.game.*;
import hearts.server.main.Server;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

abstract public class Player {
	ArrayList<Card> hand;
	ArrayList<Card> clubs;
	ArrayList<Card> diamonds;
	ArrayList<Card> spades;
	ArrayList<Card> hearts;
	Hashtable<Suit,ArrayList<Card>> handBySuit;
	int score;
	String myName;
	Server myServer;
	
	protected Random r;
	
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
		
		handBySuit = new Hashtable<Suit,ArrayList<Card>>();
		score = 0;
		
		r = new Random();
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
		
		handBySuit.put(Suit.CLUBS, clubs);
		handBySuit.put(Suit.DIAMONDS, diamonds);
		handBySuit.put(Suit.SPADES, spades);
		handBySuit.put(Suit.HEARTS, hearts);
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
	
	protected boolean hasSuit(Suit s)
	{
		if(s == Suit.CLUBS)
			return clubs.size() > 0;
		else if(s == Suit.DIAMONDS)
			return diamonds.size() > 0;
		else if(s == Suit.SPADES)
			return spades.size() > 0;
		else if(s == Suit.HEARTS)
			return hearts.size() > 0;
		else
			return false;
	}
	
	public boolean equals(Player p)
	{
		return myName.equals(p.myName);
	}
	
	public abstract void run();
	
	public abstract void playCardPrompt();
	
	public Hashtable<Suit, ArrayList<Card>> getHandBySuit()
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
	
	public String printSuit(Suit s)
	{
		switch(s)
		{
			case CLUBS:
				return printClubs();
			case DIAMONDS:
				return printDiamonds();
			case SPADES:
				return printSpades();
			case HEARTS:
				return printHearts();
			default:
				System.out.println("Invalid Suit requested to print!");
				return "";
		}
	}
	
	private String printClubs()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("CLUBS: ");
		sb.append(clubs.toString());
		sb.append("\n");
		
		return sb.toString();
	}
	
	
	
	private String printDiamonds()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("DIAMONDS: ");
		sb.append(diamonds.toString());
		sb.append("\n");
		
		return sb.toString();
	}
	
	private String printSpades()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SPADES: ");
		sb.append(spades.toString());
		sb.append("\n");
		
		return sb.toString();
	}
	
	private String printHearts()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("HEARTS: ");
		sb.append(hearts.toString());
		sb.append("\n");
		
		return sb.toString();
	}

}
