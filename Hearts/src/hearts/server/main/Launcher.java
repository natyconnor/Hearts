package hearts.server.main;

import hearts.server.game.*;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck d = new Deck();
		d.shuffle();
		System.out.println(d.toString());
	}

}
