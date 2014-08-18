package cluedo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Neal Hartley && Myles Glass
 * used for setting up radiobuttons prompt on attempted exit of board.
 * implements windowListener.
 *
 */
public class RadioListener implements WindowListener{



	String selected;
	@Override
	public void windowOpened(WindowEvent e) {

	}


	/**
	 * opens up a JOptionPane with a panel containing radio button, on attempted close.
	 *
	 */
	@Override
	public void windowClosing(WindowEvent e) {

		JPanel panel = new JPanel();
		JRadioButton yes = new JRadioButton("Yes");
		JRadioButton no = new JRadioButton("No");

		 ActionListener aL = new ActionListener() {

		    	public void actionPerformed(ActionEvent actionEvent) {

		          AbstractButton aButton = (AbstractButton) actionEvent.getSource();
		          System.out.println("Selected: " + aButton.getText());
                  selected = aButton.getText();

		        }};

        ButtonGroup group = new ButtonGroup();
        group.add(yes);
        group.add(no);
        panel.add(yes);
        panel.add(no);
        yes.addActionListener(aL);
        no.addActionListener(aL);


	    @SuppressWarnings("unused")
		int result = JOptionPane.showOptionDialog(null,panel,"Do you want to close?",JOptionPane.DEFAULT_OPTION,
	    	    JOptionPane.QUESTION_MESSAGE, null, null, null);


	    if(selected == null){

	    	System.exit(0);
	    }
       if(selected.equals("Yes")){
    	  System.exit(0);

       }


	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

}
