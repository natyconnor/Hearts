package hearts.client.player;

import hearts.server.game.Card;
import hearts.server.game.Deck;
import hearts.server.game.Suit;
import hearts.server.game.Value;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {

	Scanner keyboard;
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
		
		chooseSuit();
		
	}
	
	public void passCardPrompt()
	{
		
	}
	
	private void chooseSuit()
	{
		Suit leadSuit = myServer.getGame().getTable().getLeadSuit();
		boolean chosen = false;
		boolean lead = false;
		
		if(leadSuit != null)
		{
			
			//If has lead suit skip to choosing suit
			if(hasSuit(leadSuit))
			{
				System.out.println("Lead suit is " + leadSuit.toString());
				chooseCard(leadSuit);
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
				System.out.println("\n***You had the 2 of Clubs. It's been played for you.***\n");
			}
		}
		
		// If suit hasn't been chosen yet
		while(!chosen)
		{
			System.out.print("Choose your suit: ");
			String suit = keyboard.next();
			
			suit = suit.trim();
			suit = suit.toLowerCase();
			
			if(suit.equals("clubs"))
			{
				if(clubs.size() == 0)
				{
					System.out.println("You're out of that suit");
				} else {
					//choose card
					chooseCard(Suit.CLUBS);
					chosen = true;
				}
			} 
			else if(suit.equals("diamonds"))
			{
				if(diamonds.size() == 0)
				{
					System.out.println("You're out of that suit");
				} else {
					//choose card
					chooseCard(Suit.DIAMONDS);
					chosen = true;
				}
			}
			else if(suit.equals("spades"))
			{
				if(spades.size() == 0)
				{
					System.out.println("You're out of that suit");
				} else {
					//choose card
					chooseCard(Suit.SPADES);
					chosen = true;
				}
			}
			else if(suit.equals("hearts"))
			{
				
				if(myServer.getGame().getRoundsPlayed() == 0)
				{
					System.out.println("Can't play points on first round!");
				}
				else if(hearts.size() == 0)
				{
					System.out.println("You're out of that suit");
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
						chooseCard(Suit.HEARTS);
						chosen = true;
					}
				}
				else
				{
					//choose card
					chooseCard(Suit.HEARTS);
					chosen = true;
				}
			}
			else
			{
				System.out.println("That's not a suit!");
			}
			System.out.println();
		}
	}
	
	private void chooseCard(Suit suit)
	{
		boolean played = false;
		while(!played)
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
				// check if trying to play the queen of spades on first round
				if(suit == Suit.SPADES && handBySuit.get(suit).get(index-1).getValue() == Value.QUEEN && myServer.getGame().getRoundsPlayed() == 0)
				{
					System.out.println("Can't play points on the first round. Even the Queen!");
				}
				else
				{
					playCard(handBySuit.get(suit).get(index-1));
					played = true;
				}
			}
		}
	}
	
	
}
