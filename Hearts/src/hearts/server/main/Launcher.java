package hearts.server.main;

import hearts.client.player.ComputerPlayer;
import hearts.client.player.HumanPlayer;
import hearts.server.game.*;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Set up server (which sets up game)
		Server server = new Server();
		
		//Add players to server/game
		HumanPlayer h1 = new HumanPlayer("Player1");
		ComputerPlayer h2 = new ComputerPlayer("Player2");
		ComputerPlayer h3 = new ComputerPlayer("Player3");
		ComputerPlayer h4 = new ComputerPlayer("Player4");
		
		h1.setServer(server);
		h2.setServer(server);
		h3.setServer(server);
		h4.setServer(server);
		
		server.getGame().addPlayer(h1);
		server.getGame().addPlayer(h2);
		server.getGame().addPlayer(h3);
		server.getGame().addPlayer(h4);
		
		
		//Game loop; while high score is less than 100
		while(server.getGame().getHighScore() < 100)
		{
			//New Game
			//shuffle cards
			server.getGame().getDeck().shuffle();
			//deal them
			server.getGame().dealHand();
			
			//while there are still rounds
			while(server.getGame().getRoundsPlayed() < Game.numRounds)
			{
			
				//ask each player to play a card
				server.getGame().promptPlayers();
				
				//see who takes trick
				int winningPlayer = server.getGame().getTable().takeTrick();
				
				//add points to their game score
				server.getGame().addTrickPoints(winningPlayer);
				
				//clear table
				server.getGame().getTable().clearTable();
				
				server.getGame().incrementRound();
				
				System.out.println("\n-----------------------------------------\n");

			}
			
			//update match score from game score
			server.getGame().updateMatchScore();
			
			//reset for next game
			server.getGame().newGame();
		}
	}

}
