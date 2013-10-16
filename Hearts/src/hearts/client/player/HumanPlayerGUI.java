package hearts.client.player;

import javax.swing.JFrame;

public class HumanPlayerGUI extends JFrame{
	
	public HumanPlayerGUI(String myName, String otherName1, String otherName2, String otherName3)
	{
		add(new HeartsPanel(myName, otherName1, otherName2, otherName3));
		
		setTitle("Hearts");
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
	}

}
