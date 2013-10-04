package hearts.server.game;

import java.util.ArrayList;

import hearts.client.player.Player;

public class Game {

	private Table myTable;
	private Deck  myDeck;
	public static final int numPlayers = 4;
	public static final int numRounds = Deck.numCards / numPlayers;
	ArrayList<Player> myPlayers;
	ArrayList<Integer> matchScores;
	ArrayList<Integer> gameScores;
	
	private int roundsPlayed;
	private boolean heartsBroken;
	
	public Game()
	{
		myTable	= new Table();
		myDeck = new Deck();
		myPlayers = new ArrayList<Player>();
		matchScores = new ArrayList<Integer>();
		gameScores = new ArrayList<Integer>();
		roundsPlayed = 0;
		heartsBroken = false;
	}
	
	public void addPlayer(Player p)
	{
		if(myPlayers.size() == numPlayers)
		{
			System.out.println("Sorry, max number of players already!");
		} else {
			myPlayers.add(p);
			matchScores.add(0);
			gameScores.add(0);
		}
	}
	
	public void dealHand()
	{
		for(int i = 0; i < Deck.numCards; i+=4)
		{
			myPlayers.get(0).giveCard(myDeck.getDeck().get(i));
			myPlayers.get(1).giveCard(myDeck.getDeck().get(i+1));
			myPlayers.get(2).giveCard(myDeck.getDeck().get(i+2));
			myPlayers.get(3).giveCard(myDeck.getDeck().get(i+3));
		}
		
		// for now, just tell players to sort hand
		for(Player p : myPlayers)
		{
			p.sortHand();
		}
	}
	
	public void promptPlayers()
	{
		for(Player p : myPlayers)
		{
			
		}
	}
	
	public void playCard(Player p, Card c)
	{
		myTable.playCard(myPlayers.indexOf(p), c);
	}
	
	public void updateMatchScore()
	{
		if(gameScores.contains(new Integer(26)))
		{
			for(int i = 0; i < numPlayers; i++)
			{
				if(gameScores.get(i) == 0)
					gameScores.set(i, 26);
				else if(gameScores.get(i) == 26)
					gameScores.set(i, 0);
				else
					System.out.print("Something is wrong with Score!");
			}
		} else {
			for(int i = 0; i < numPlayers; i++)
			{
				matchScores.set(i, matchScores.get(i) + gameScores.get(i));
				gameScores.set(i, 0);
			}
		}
	}
	
	public void addTrickPoints(int playerNum)
	{
		for(Card c : myTable.getPlayedCards())
		{
			if(c.getSuit() == Suit.HEARTS)
			{
				gameScores.set(playerNum, gameScores.get(playerNum) + 1);
				if(heartsBroken == false)
					heartsBroken = true;
			}
			else if(c.getSuit() == Suit.SPADES && c.getValue() == Value.QUEEN)
				gameScores.set(playerNum, gameScores.get(playerNum) + 13);
		}
	}
	
	public void incrementRound()
	{
		roundsPlayed++;
	}
	
	public void newGame()
	{
		roundsPlayed = 0;
		heartsBroken = false;
	}
	
	public int getRoundsPlayed()
	{
		return roundsPlayed;
	}
	
	public boolean getHeartsBroken()
	{
		return heartsBroken;
	}
	
	public int getHighScore()
	{
		int highScore = -1;
		for(int score : matchScores)
		{
			if(score > highScore)
				highScore = score;
		}
		
		return highScore;
	}
	
	public Table getTable()
	{
		return myTable;
	}
	
	public Deck getDeck()
	{
		return myDeck;
	}
	
}
