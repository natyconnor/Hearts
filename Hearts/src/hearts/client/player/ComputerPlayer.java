package hearts.client.player;

import java.util.Random;

import hearts.server.game.Suit;

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
				//TODO need to fix to handle case if suit picked is empty
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
				//play random card for now
				playCard(hand.get(new Random().nextInt(hand.size()-1)));
			}
		}
		
		
		
	}

}
