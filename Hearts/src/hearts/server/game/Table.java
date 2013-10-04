package hearts.server.game;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Table {
	private Card[] playedCards; //array for the 4 cards played that round by the players
	private Suit leadSuit;
	
	public Table()
	{
		playedCards = new Card[Game.numPlayers];
		leadSuit = null;
	}
	
	public void playCard(int playerNum, Card card)
	{
		playedCards[playerNum] = card;
		if(leadSuit == null)
		{
			leadSuit = card.getSuit();
		}
	}
	
	public void clearTable()
	{
		playedCards = new Card[Game.numPlayers];
		leadSuit = null;
	}
	
	public int takeTrick()
	{
		if(leadSuit == null)
		{
			System.out.println("Lead Suit not set!!");
		}
		int highestValue = -1;
		int playerNum = -1;
		
		for(int i = 0; i < Game.numPlayers; i++)
		{
			Card current = playedCards[i];
			if(current.getSuit().equals(leadSuit))
			{
				if(current.getValue().getNum() > highestValue)
				{
					highestValue = current.getValue().getNum();
					playerNum = i;
				}
			}
		}
		
		return playerNum;
	}
	
	public Card[] getPlayedCards()
	{
		return playedCards;
	}
	
	public Suit getLeadSuit()
	{
		return leadSuit;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < playedCards.length; i++)
		{
			if(playedCards[i] == null)
				sb.append("null");
			else
				sb.append(playedCards[i].toString());
			sb.append(",");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
}
