package travian;
import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class AboutPanel extends javax.swing.JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JTextArea txt;

	/**
	 * Auto-generated main method to display this 
	 * JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new AboutPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public AboutPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(400, 468));
			{
				txt = new JTextArea();
				this.add(txt, BorderLayout.CENTER);
				txt.setText("TravianEarth:\n\nThis software is not complete yet. However, You can use it til I fix some known bugs.\n\nDownload \"map.sql\" file from your Travian server.   \nExamples:\n\n    * http://www.travian.org/karte.sql\n    * http://welt1.travian.de/karte.sql.gz\n    * http://s1.travian.com/map.sql\n    * http://speed.travian.nl/map.sql.gz\n    * http://s1.travian.ae/map.sql.gz\n    \nUnzip the file and open it using this software.\nUsing TravianEarth is easy and straightforward. You only use the pop-up menu and the mouse to navigate inside your Travian world.\n\nFor more information refer to:\nhttp://code.google.com/p/travianearth/\n\nRegards,\ntravianEarth@googlemail.com");
				txt.setPreferredSize(new java.awt.Dimension(400, 431));
				txt.setWrapStyleWord(true);
				txt.setLineWrap(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
