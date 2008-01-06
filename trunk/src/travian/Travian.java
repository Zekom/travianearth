package travian;

//import org.apache.log4j.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ProgressMonitor;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;



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
public class Travian extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	//public static Logger log=Logger.getLogger(Travian.class);
	private JPanel pnlLoadMap;

	//private JPanel p;
	private TravianPanel tp;
	private InfoPanel ip;
	private JToggleButton btnLoadMap;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		Travian inst = new Travian();
		inst.setVisible(true);
	}

	public Travian() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(200,200,200));
			this.setTitle("Travian Earth");
			{
				tp = new TravianPanel();
				//TODO: test 	tp.setInfoPanel(ip); 
				getContentPane().add(tp);
				tp.setLayout(null);
				tp.setBounds(308, 14, 602, 602);

				tp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			}
			{
				ip=new InfoPanel();
				ip.setTravianPanel(tp);
				getContentPane().add(ip);
				ip.setLayout(null);
				ip.setBounds(14, 84, 280, 532);
				ip.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				tp.setInfoPanel(ip);

			}
			{
				pnlLoadMap = new JPanel();
				getContentPane().add(pnlLoadMap);
				pnlLoadMap.setLayout(null);
				pnlLoadMap.setBounds(14, 14, 280, 56);
				pnlLoadMap.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				pnlLoadMap.setBackground(new java.awt.Color(200,200,200));
				{
					btnLoadMap = new JToggleButton();
					pnlLoadMap.add(btnLoadMap);
					btnLoadMap.setText("Load Travian Map");
					btnLoadMap.setBounds(21, 14, 245, 28);
					btnLoadMap.setBackground(new java.awt.Color(200,200,200));
					btnLoadMap.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnLoadMapActionPerformed(evt);
						}
					});
				}
			}
			pack();
			this.setSize(932, 657);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Auto-generated method for setting the popup menu for a component
	 */

	private void btnLoadMapActionPerformed(ActionEvent evt) {
		//test
		if(false){
			tp.loadSqlFile("d://travian/map.sql", this);
			return;
		}
		JFileChooser chooser=new JFileChooser();
		// Set the current directory
		chooser.addChoosableFileFilter(new MapSqlFilter());
		int result = chooser.showOpenDialog(this);		
		//log.debug("chooser result="+result);

		switch (result) {
		case JFileChooser.CANCEL_OPTION:
			//log.debug("No file was choosed");
			return;
		case JFileChooser.ERROR_OPTION:
			//log.error("The selection process did not complete successfully");
			return;

		case JFileChooser.APPROVE_OPTION:
			//log.debug("File selected");
			break;
		}
		//log.debug("file="+chooser.getSelectedFile());
		final String sqlFileName=chooser.getSelectedFile().getAbsolutePath();
		new Thread(){
			public void run(){
				btnLoadMap.setEnabled(false);
				tp.setEnabled(false);
				tp.loadSqlFile(sqlFileName,Travian.this);
				btnLoadMap.setEnabled(true);
				tp.setEnabled(true);
			}
		}.start();
		//log.debug("New map was initiated" );
	}

	void testProgress(){
		ProgressMonitor pm = new ProgressMonitor(this, "Creates and loads database from \n", "0 % completed", 0, 100);
		// To make progress monitor popup immediately, set both to 0
		pm.setMillisToPopup(0);
		pm.setMillisToDecideToPopup(0);
		for (int i = 0; i < 100; i++) {

			pm.setNote(""+i+" % completed");
			pm.setProgress(i);
			try {
				Thread.sleep(100);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}

}
