package hearts.client.player;

import java.util.Random;

import hearts.server.game.Suit;

public class ComputerPlayer extends Player {
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playCardPrompt() {
		Suit leadSuit = myServer.getGame().getTable().getLeadSuit();
		if(leadSuit == null)
		{
			//choose suit at random and play lowest card
			Random r = new Random();
			
			//if hearts are broken
			if(myServer.getGame().getHeartsBroken())
			{
				playCard(handBySuit.get(r.nextInt(4)).get(0));
			}
			else
			{
				playCard(handBySuit.get(r.nextInt(3)).get(0));
			}
		} else {
			//check if you have cards of lead suit
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
			
			if(haveLeadSuit == false)
			{
				
			}
		}
		
		
		
	}

}
