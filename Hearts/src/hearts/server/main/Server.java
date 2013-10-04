package hearts.server.main;

import hearts.server.game.Game;

public class Server {
	private Game myGame;
	
	public Server()
	{
		myGame = new Game();
	}
	
	public Game getGame()
	{
		return myGame;
	}

}
