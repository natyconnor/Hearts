package hearts.server.main;

import hearts.client.player.ComputerPlayer;
import hearts.client.player.HumanPlayer;
import hearts.server.game.Game;
import hearts.server.game.PassDirection;

public class Server {
	private Game myGame;
	
	public Server()
	{
		myGame = new Game();
		
		//temp setup for one game and one thread
		//Add players to server/game
		HumanPlayer h1 = new HumanPlayer("Player1");
		//ComputerPlayer h1 = new ComputerPlayer("Player1");
		ComputerPlayer h2 = new ComputerPlayer("Player2");
		ComputerPlayer h3 = new ComputerPlayer("Player3");
		ComputerPlayer h4 = new ComputerPlayer("Player4");
		
		h1.setServer(this);
		h2.setServer(this);
		h3.setServer(this);
		h4.setServer(this);
		
		myGame.addPlayer(h1);
		myGame.addPlayer(h2);
		myGame.addPlayer(h3);
		myGame.addPlayer(h4);
		
		runGame(myGame);
	}
	
	private void runGame(Game g)
	{
		//Game loop; while high score is less than 100
		while(g.getHighScore() < 100)
		{
			//New Game
			//shuffle cards
			g.getDeck().shuffle();
			//deal them
			g.dealHand();
			
			//Have players pass cards
			if(g.getPassingDirection() != PassDirection.KEEP)
			{
				g.promptPassCards();
				g.givePassCards();
			}
			
			//while there are still rounds
			while(g.getRoundsPlayed() < Game.numRounds)
			{
			
				//ask each player to play a card
				g.promptPlayCard();
				
				//see who takes trick
				int winningPlayer = g.getTable().takeTrick();
				
				//add points to their game score
				g.addTrickPoints(winningPlayer);
				
				//clear table
				g.getTable().clearTable();
				
				g.incrementRound();
				
				System.out.println("\n-----------------------------------------\n");

			}
			
			//update match score from game score
			g.updateMatchScore();
			
			//reset for next game
			g.newGame();
		}
		
		System.out.println("Game Over!");
		System.out.println(g.getWinningPlayer().toString() + " wins!!");

	}
	
	public Game getGame()
	{
		return myGame;
	}

}
