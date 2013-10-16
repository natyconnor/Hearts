package hearts.client.player;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HeartsPanel extends JPanel implements ActionListener{

	protected JTextField myTextField, otherText1, otherText2, otherText3;
	protected JTextArea myTextArea;
	
	public HeartsPanel(String myName, String otherName1, String otherName2, String otherName3)
	{
		super(new GridBagLayout());
		
		myTextField = new JTextField();
		myTextField.addActionListener(this);
		
		otherText1 = new JTextField();
		otherText1.setEditable(false);
		
		otherText2 = new JTextField();
		otherText2.setEditable(false);
		
		otherText3 = new JTextField();
		otherText3.setEditable(false);
		
		myTextArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(myTextArea);
		
		//Add Components to this panel
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 10;     c.ipady = 5;
		
		//top padding
		c.gridx = 0;      c.gridy = 0;
		c.gridwidth = 3;  c.gridheight = 1; 
		c.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel(""), c);
		
		//add labels
		c.gridx = 0;      c.gridy = 2;
		c.gridwidth = 1;  c.gridheight = 1; 
		c.weightx = 0.0;  c.weighty = 1.0;
		c.anchor = GridBagConstraints.PAGE_END;
		add(new JLabel(otherName1, JLabel.CENTER), c);
		
		c.gridx = 2;
		add(new JLabel(otherName3, JLabel.CENTER), c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		add(new JLabel(otherName2, JLabel.CENTER), c);
		
		c.gridy = 4;
		c.anchor = GridBagConstraints.PAGE_END;
		add(new JLabel(myName, JLabel.CENTER), c);
		
		//add text fields
		c.gridx = 0; c.gridy = 3;
		c.weightx = 1.0; c.weighty = 0.0;
		c.anchor = GridBagConstraints.CENTER;
		add(otherText1, c);
		
		c.gridx = 2;
		add(otherText3, c);
		
		c.gridx = 1; c.gridy = 1;
		add(otherText2, c);
		
		c.gridy = 5;
		add(myTextField, c);
		
		//Center space
		
		
		//Player text area
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0; c.gridy = 6;
		c.gridwidth = 3; c.gridheight = 2;
		c.weightx = 1.0; c.weighty = 1.0;
		add(scrollPane, c);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
