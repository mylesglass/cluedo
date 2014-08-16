package cluedo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioListener implements WindowListener{



	String selected;
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

		JPanel panel = new JPanel();
		JRadioButton yes = new JRadioButton("Yes");
		JRadioButton no = new JRadioButton("no");

		 ActionListener aL = new ActionListener() {

		    	public void actionPerformed(ActionEvent actionEvent) {

		          AbstractButton aButton = (AbstractButton) actionEvent.getSource();
		          System.out.println("Selected: " + aButton.getText());
                  selected = aButton.getText();

		        }};

		JButton button = new JButton("confirm");
        ButtonGroup group = new ButtonGroup();
        group.add(yes);
        group.add(no);
        panel.add(yes);
        panel.add(no);
        yes.addActionListener(aL);
        no.addActionListener(aL);


	    int result = JOptionPane.showOptionDialog(null,panel,"Do you want to close?",JOptionPane.DEFAULT_OPTION,
	    	    JOptionPane.QUESTION_MESSAGE, null, null, null);


       if(selected.equals("Yes")){
    	  System.exit(0);

       }


	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
