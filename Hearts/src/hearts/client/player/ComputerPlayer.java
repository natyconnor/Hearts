package hearts.client.player;

import java.util.Random;

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
				playCard(handBySuit.get(0).get(0));
			else
			{
				//choose suit at random and play lowest card
				Random r = new Random();
				
				//if hearts are broken				
				if(myServer.getGame().getHeartsBroken())
				{
					while(true)
					{
						int suit = r.nextInt(4);
						if(handBySuit.get(suit).size() > 0)
						{
							playCard(handBySuit.get(suit).get(0));
							break;
						}
					}
				}
				else
				{
					while(true)
					{
						int suit = r.nextInt(3);
						if(handBySuit.get(suit).size() > 0)
						{
							playCard(handBySuit.get(suit).get(0));
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
				if(handBySuit.get(0).size() > 0)
				{
					playCard(handBySuit.get(0).get(0));
				} else {
					haveLeadSuit = false;
				}
			} else if(leadSuit == Suit.DIAMONDS)
			{
				if(handBySuit.get(1).size() > 0)
				{
					playCard(handBySuit.get(1).get(0));
				} else {
					haveLeadSuit = false;
				}
			} else if(leadSuit == Suit.SPADES)
			{
				if(handBySuit.get(2).size() > 0)
				{
					playCard(handBySuit.get(2).get(0));
				} else {
					haveLeadSuit = false;
				}
			} else if(leadSuit == Suit.HEARTS)
			{
				if(handBySuit.get(3).size() > 0)
				{
					playCard(handBySuit.get(3).get(0));
				} else {
					haveLeadSuit = false;
				}
			}
			//If following but don't have lead suit
			if(haveLeadSuit == false)
			{
				//Play largest card from random suit
				Random r = new Random();
				
				//if first round
				if(myServer.getGame().getRoundsPlayed() == 0)
				{
					//Can't play hearts
					while(true)
					{
						int suitNum = r.nextInt(3);
						// Make sure suit is not empty and biggest card of suit is not Queen of spades
						if(handBySuit.get(suitNum).size() > 0 && !(suitNum == 2 && handBySuit.get(suitNum).get(handBySuit.get(suitNum).size()-1).getValue() == Value.QUEEN))
						{
							playCard(handBySuit.get(suitNum).get(handBySuit.get(suitNum).size()-1));
							break;
						}
					}
				}
				else
				{
					while(true)
					{
						int suit = r.nextInt(4);
						if(handBySuit.get(suit).size() > 0)
						{
							playCard(handBySuit.get(suit).get(handBySuit.get(suit).size()-1));
							break;
						}
					}
				}
			}
		}
		
		
		
	}

}
