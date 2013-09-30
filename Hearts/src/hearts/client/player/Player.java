package hearts.client.player;

import hearts.server.game.*;
import hearts.server.main.Server;

import java.util.ArrayList;

abstract public class Player {
	ArrayList<Card> hand;
	int score;
	String myName;
	Server myServer;
	
	public Player()
	{
		this("");
	}
	
	public Player(String name)
	{
		myName = name;
		hand = new ArrayList<Card>();
		score = 0;
	}
	
	public void setServer(Server s) { myServer = s; }
	
	public void setHand(ArrayList<Card> cards)
	{
		hand = cards;
	}
	
	
	
	public abstract void run();

}
