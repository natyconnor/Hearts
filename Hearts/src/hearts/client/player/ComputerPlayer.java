package hearts.client.player;

import hearts.server.game.Card;
import hearts.server.game.Suit;
import hearts.server.game.Value;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer()
	{
		super();
	}
	
	public ComputerPlayer(String name)
	{
		super(name);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playCardPrompt() {
		if(hand.size() == 0)
			System.out.println("Wait, I'm out of cards!!");
		
		Suit leadSuit = myServer.getGame().getTable().getLeadSuit();
		
		//if leading
		if(leadSuit == null)
		{
			// Have to play 2 of clubs first
			if(have2Clubs())
				playCard(handBySuit.get(Suit.CLUBS).get(0));
			else
			{
				//choose suit at random and play lowest card
							
				//if hearts are broken or you're out of all other suits
				if(myServer.getGame().getHeartsBroken() || (clubs.size() == 0 && diamonds.size() == 0 && spades.size() == 0))
				{
					//Play a random small card
					playCard(getRandomSmallCard());
				}
				else
				{
					while(true)
					{
						//Play a random small card that's not a heart
						Card c = getRandomSmallCard();
						if(c.getSuit() != Suit.HEARTS)
						{
							playCard(c);
							break;
						}
					}
				}
			}
		} else {			
			//If you have the lead suit, play the smallest one
			if(handBySuit.get(leadSuit).size() > 0)
			{
				playCard(handBySuit.get(leadSuit).get(0));
			}
			//If following but don't have lead suit
			else
			{
				//Play largest card from random suit
				
				//if first round
				if(myServer.getGame().getRoundsPlayed() == 0)
				{
					//Can't play points
					while(true)
					{
						Card c = getRandomLargeCard();
						
						//Make sure suit is not hearts and if it's a spade, not the queen
						if(c.getSuit() != Suit.HEARTS && !(c.getSuit() == Suit.SPADES && c.getValue() != Value.QUEEN))
						{
							playCard(c);
							break;
						}
					}
				}
				else
				{
					playCard(getRandomLargeCard());
				}
			}
		}
		
		
		
	}

	public void passCardPrompt() 
	{
		for(int i = 0; i < 3; i++)
		{
			passCard(getRandomLargeCard());
		}
	}
	
	
	
	private Card getRandomLargeCard()
	{
		while(true)
		{
			Suit suit = Suit.SUITS.get(r.nextInt(4));
			if(handBySuit.get(suit).size() > 0)
			{
				return handBySuit.get(suit).get(handBySuit.get(suit).size()-1);
			}
		}
	}
	
	private Card getRandomSmallCard()
	{
		while(true)
		{
			Suit suit = Suit.SUITS.get(r.nextInt(4));
			if(handBySuit.get(suit).size() > 0)
			{
				return handBySuit.get(suit).get(0);
			}
		}
	}
}
