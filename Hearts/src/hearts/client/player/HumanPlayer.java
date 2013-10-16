package hearts.client.player;

import hearts.server.game.Card;
import hearts.server.game.Suit;
import hearts.server.game.Value;

import java.util.Scanner;

public class HumanPlayer extends Player {

	Scanner keyboard;
	HumanPlayerGUI gui;
	public static void main(String[] args)
	{
		System.out.println((-1 % 4 + 4) % 4);
	}
	
	public HumanPlayer()
	{
		this("");
		
	}
	
	public HumanPlayer(String name)
	{
		super(name);
		keyboard = new Scanner(System.in);
		gui = new HumanPlayerGUI(myName, "Player 1", "Player 2", "Player 3");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public void kill()
	{
		keyboard.close();
	}

	@Override
	public void playCardPrompt() {
		System.out.println();
		System.out.println(printHand());
		
		Suit leadSuit = myServer.getGame().getTable().getLeadSuit();
		boolean chosen = false;
		boolean played = false;
		boolean lead = false;
		
		Suit suitToPlay = leadSuit;
		Card cardToPlay;
		
		if(leadSuit != null)
		{
			
			//If has lead suit skip to choosing suit
			if(hasSuit(leadSuit))
			{
				System.out.println("Lead suit is " + leadSuit.toString());
				//cardToPlay = chooseCard(leadSuit);
				suitToPlay = leadSuit;
				chosen = true;
			}
			else
			{
				System.out.println("You don't have lead suit!");
			}
		}
		else
		{
			//If there is no lead suit
			System.out.println("You're leading.");
			lead = true;
			if(have2Clubs())
			{
				playCard(handBySuit.get(Suit.CLUBS).get(0));
				chosen = true;
				played = true;
				System.out.println("\n***You had the 2 of Clubs. It's been played for you.***\n");
			}
		}
		
		// If suit hasn't been chosen yet
		while(!chosen)
		{
			suitToPlay = chooseSuit();
			
			if(handBySuit.get(suitToPlay).size() == 0)
			{
				System.out.println("You're out of that suit!");
			}
			else if(suitToPlay == Suit.HEARTS)
			{
				if(myServer.getGame().getRoundsPlayed() == 0)
				{
					System.out.println("Can't play points on first round!");
				}
				else if(!myServer.getGame().getHeartsBroken())
				{
					// If I'm able to pick my suit I'm either leading or out of the suit
					
					//If I'm leading and I have any other suit of cards, I can't play hearts
					if(lead && (clubs.size() > 0 || diamonds.size() > 0 || spades.size() > 0))
					{
						System.out.println("Hearts haven't been broken yet!");
					}
					else // otherwise I'm out of lead suit so I can break hearts
					{
						chosen = true;
					}
				}
				else
				{
					chosen = true;
				}
			}
			else 
			{
				chosen = true;
			}
			
			System.out.println();
		}
		//Suit has been chosen so choose card now
		
		
		while(!played){
			cardToPlay = chooseCard(suitToPlay);
			
			// check if trying to play the queen of spades on first round
			if(suitToPlay == Suit.SPADES && cardToPlay.getValue() == Value.QUEEN && myServer.getGame().getRoundsPlayed() == 0)
			{
				System.out.println("Can't play points on the first round. Even the Queen!");
			}
			else
			{
				playCard(cardToPlay);
				played = true;
			}
		}				
	}
	
	public void passCardPrompt()
	{
		System.out.println("Choose 3 cards to pass to the " + myServer.getGame().getPassingDirection() + "\n");
		for (int i = 0; i < 3; i++) {
			System.out.println(printHand());
			Suit suit = null;
			while (true) {
				suit = chooseSuit();
				if (handBySuit.get(suit).size() == 0) {
					System.out.println("You don't have any of that suit!");
				} else {
					break;
				}
			}
			passCard(chooseCard(suit));
		}
		
		
	}
	
	private Suit chooseSuit()
	{
		
		String suit;
		
		while (true) {
			System.out.print("Choose your suit: ");
			suit = keyboard.next();
			suit = suit.trim();
			suit = suit.toLowerCase();
			if(Suit.getSuitFromString(suit) != null)
				break;
			else
				System.out.println("That's not a suit!");
		}
		return Suit.getSuitFromString(suit);
		
	}
	
	private Card chooseCard(Suit suit)
	{
		while(true)
		{
			System.out.println(printSuit(suit));
			
			System.out.print("Choose the index of the card (starting with 1): ");

			int index = 0;
			if (keyboard.hasNextInt()) {
				index = keyboard.nextInt();
			} else {
				keyboard.next(); //garbage step to process line and move on
			}

			if(index < 1 || index > handBySuit.get(suit).size())
			{
				System.out.println("Invalid index\n");
			}
			else
			{
				return handBySuit.get(suit).get(index-1);
			}
		}
	}
	
	
}
