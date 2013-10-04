package hearts.client.player;

import java.util.ArrayList;

public class HumanPlayer extends Player {

	public static void main(String[] args)
	{
		System.out.println("Hello");
		HumanPlayer h = new HumanPlayer("sup");
	}
	
	public HumanPlayer()
	{
		super();
	}
	
	public HumanPlayer(String name)
	{
		super(name);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playCardPrompt() {
		// TODO Auto-generated method stub
		
	}
}
