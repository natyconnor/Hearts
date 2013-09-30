package hearts.server.game;

public class Table {
	private Card[] playedCards; //array for the 4 cards played that round by the players
	public static final int numPlayers = 4;
	
	public Table()
	{
		playedCards = new Card[numPlayers];
		
	}
	
	public void playCard(int playerNum, Card card)
	{
		playedCards[playerNum] = card;
	}
}
