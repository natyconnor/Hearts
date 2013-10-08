package hearts.client.player;

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
					while(true)
					{
						int suitNum = r.nextInt(4);
						if(handBySuit.get(Suit.SUITS.get(suitNum)).size() > 0)
						{
							playCard(handBySuit.get(Suit.SUITS.get(suitNum)).get(0));
							break;
						}
					}
				}
				else
				{
					while(true)
					{
						int suitNum = r.nextInt(3);
						if(handBySuit.get(Suit.SUITS.get(suitNum)).size() > 0)
						{
							playCard(handBySuit.get(Suit.SUITS.get(suitNum)).get(0));
							break;
						}
					}
				}
			}
		} else {
			//following; check if you have cards of lead suit
			boolean haveLeadSuit = true;
			if(leadSuit == Suit.CLUBS)
			{
				if(handBySuit.get(Suit.CLUBS).size() > 0)
				{
					playCard(handBySuit.get(Suit.CLUBS).get(0));
				} else {
					haveLeadSuit = false;
				}
			} else if(leadSuit == Suit.DIAMONDS)
			{
				if(handBySuit.get(Suit.DIAMONDS).size() > 0)
				{
					playCard(handBySuit.get(Suit.DIAMONDS).get(0));
				} else {
					haveLeadSuit = false;
				}
			} else if(leadSuit == Suit.SPADES)
			{
				if(handBySuit.get(Suit.SPADES).size() > 0)
				{
					playCard(handBySuit.get(Suit.SPADES).get(0));
				} else {
					haveLeadSuit = false;
				}
			} else if(leadSuit == Suit.HEARTS)
			{
				if(handBySuit.get(Suit.HEARTS).size() > 0)
				{
					playCard(handBySuit.get(Suit.HEARTS).get(0));
				} else {
					haveLeadSuit = false;
				}
			}
			//If following but don't have lead suit
			if(haveLeadSuit == false)
			{
				//Play largest card from random suit
				
				//if first round
				if(myServer.getGame().getRoundsPlayed() == 0)
				{
					//Can't play hearts
					while(true)
					{
						int suitNum = r.nextInt(3);
						// Make sure suit is not empty and biggest card of suit is not Queen of spades
						if(handBySuit.get(Suit.SUITS.get(suitNum)).size() > 0 && !(suitNum == 2 && handBySuit.get(Suit.SUITS.get(suitNum)).get(handBySuit.get(Suit.SUITS.get(suitNum)).size()-1).getValue() == Value.QUEEN))
						{
							playCard(handBySuit.get(Suit.SUITS.get(suitNum)).get(handBySuit.get(Suit.SUITS.get(suitNum)).size()-1));
							break;
						}
					}
				}
				else
				{
					while(true)
					{
						//Otherwise choose any random suit and play the largest card
						int suitNum = r.nextInt(4);
						if(handBySuit.get(Suit.SUITS.get(suitNum)).size() > 0)
						{
							playCard(handBySuit.get(Suit.SUITS.get(suitNum)).get(handBySuit.get(Suit.SUITS.get(suitNum)).size()-1));
							break;
						}
					}
				}
			}
		}
		
		
		
	}

}
