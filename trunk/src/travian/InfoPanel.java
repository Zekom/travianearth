package travian;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//import org.apache.log4j.Logger;

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
public class InfoPanel extends javax.swing.JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//public static Logger log=Logger.getLogger(InfoPanel.class);	
	
	private JLabel lblxy;
	private JTextField txtPlayer;
	private JButton btnAddInterestVilages;
	private JButton btnGo;
	private JLabel lblID;
	private JButton btnAbout;
	private JButton btnGoAlliance;
	private JButton btnPlayer;
	private JButton btnVillage;
	private JLabel lblY;
	private JLabel lblX;
	private JLabel lblAllinceNumber;
	private JTextField txtTotalPopulation;
	private JTextField txtAllicanceVillages;
	private JLabel lblAlliancePopulation;
	private JLabel lblPopulation;
	private JLabel lblTribe;
	private JTextField txtPopulation;
	private JTextField txtAlliance;
	private JTextField txtVillage;
	private JTextField txtY;
	private JTextField txtX;
	private JTextField txtId;
	private JTextField txtTrib;
	
	private TravianPanel tp;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new InfoPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public InfoPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(217, 595));
			this.setLayout(null);
			this.setSize(280, 595);
			this.setBackground(new java.awt.Color(212,208,200));
			{
				lblxy = new JLabel();
				this.add(lblxy);
				lblxy.setText("lblxy");
				lblxy.setBounds(91, 56, 168, 28);
				lblxy.setBackground(new java.awt.Color(200,200,200));
			}
			{
				txtTrib = new JTextField();
				this.add(txtTrib);
				txtTrib.setText("Tribe");
				txtTrib.setBounds(91, 185, 168, 28);
			}
			{
				txtPlayer = new JTextField();
				this.add(txtPlayer);
				txtPlayer.setText("Player");
				txtPlayer.setBounds(91, 320, 168, 28);
			}
			{
				txtId = new JTextField();
				this.add(txtId);
				txtId.setText("ID");
				txtId.setBounds(91, 275, 168, 28);
			}
			{
				txtX = new JTextField();
				this.add(txtX);
				txtX.setText("X");
				txtX.setBounds(119, 98, 49, 28);
			}
			{
				txtY = new JTextField();
				this.add(txtY);
				txtY.setText("Y");
				txtY.setBounds(210, 98, 49, 28);
			}
			{
				txtVillage = new JTextField();
				this.add(txtVillage);
				txtVillage.setText("Village");
				txtVillage.setBounds(91, 140, 168, 28);
			}
			{
				txtAlliance = new JTextField();
				this.add(txtAlliance);
				txtAlliance.setText("Alliance");
				txtAlliance.setBounds(91, 365, 168, 28);
			}
			{
				txtPopulation = new JTextField();
				this.add(txtPopulation);
				txtPopulation.setText("Population");
				txtPopulation.setBounds(91, 230, 168, 28);
			}
			{
				txtAllicanceVillages = new JTextField();
				this.add(txtAllicanceVillages);
				txtAllicanceVillages.setText("Alliance villages");
				txtAllicanceVillages.setBounds(91, 410, 168, 28);
			}
			{
				txtTotalPopulation = new JTextField();
				this.add(txtTotalPopulation);
				txtTotalPopulation.setText("Total population");
				txtTotalPopulation.setBounds(91, 455, 168, 28);
			}
			{
				lblID = new JLabel();
				this.add(lblID);
				lblID.setText("ID");
				lblID.setBounds(7, 275, 77, 28);
				lblID.setBackground(new java.awt.Color(200,200,200));
			}
			{
				lblTribe = new JLabel();
				this.add(lblTribe);
				lblTribe.setText("Tribe");
				lblTribe.setBounds(7, 185, 77, 28);
				lblTribe.setBackground(new java.awt.Color(200,200,200));
			}
			{
				lblPopulation = new JLabel();
				this.add(lblPopulation);
				lblPopulation.setText("Population");
				lblPopulation.setBounds(7, 230, 77, 28);
				lblPopulation.setBackground(new java.awt.Color(200,200,200));
			}
			{
				lblAlliancePopulation = new JLabel();
				this.add(lblAlliancePopulation);
				lblAlliancePopulation.setText("A. Poplulation");
				lblAlliancePopulation.setBounds(7, 455, 77, 28);
				lblAlliancePopulation.setBackground(new java.awt.Color(200,200,200));
			}
			{
				lblAllinceNumber = new JLabel();
				this.add(lblAllinceNumber);
				lblAllinceNumber.setText("Alliance Villages");
				lblAllinceNumber.setBounds(7, 410, 77, 28);
				lblAllinceNumber.setBackground(new java.awt.Color(200,200,200));
			}
			{
				lblX = new JLabel();
				this.add(lblX);
				lblX.setText("X");
				lblX.setBounds(105, 98, 14, 28);
				lblX.setBackground(new java.awt.Color(200,200,200));
			}
			{
				lblY = new JLabel();
				this.add(lblY);
				lblY.setText("Y");
				lblY.setBounds(196, 98, 14, 28);
				lblY.setBackground(new java.awt.Color(200,200,200));
			}
			{
				btnGo = new JButton();
				this.add(btnGo);
				btnGo.setText("Go");
				btnGo.setBounds(7, 98, 77, 28);
				btnGo.setBackground(new java.awt.Color(212,208,200));
				btnGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnGoActionPerformed(evt);
					}
				});
			}
			{
				btnAddInterestVilages = new JButton();
				this.add(btnAddInterestVilages);
				btnAddInterestVilages.setText("Color Villages");
				btnAddInterestVilages.setBounds(84, 14, 175, 28);
				btnAddInterestVilages.setBackground(new java.awt.Color(212,208,200));
				btnAddInterestVilages.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnAddInterestVilagesActionPerformed(evt);
					}
				});
			}
			{
				btnVillage = new JButton();
				this.add(btnVillage);
				btnVillage.setText("Village");
				btnVillage.setBounds(7, 140, 77, 28);
				btnVillage.setBackground(new java.awt.Color(212,208,200));
				btnVillage.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnVillageActionPerformed(evt);
					}
				});
			}
			{
				btnPlayer = new JButton();
				this.add(btnPlayer);
				btnPlayer.setText("Player");
				btnPlayer.setBounds(7, 322, 77, 28);
				btnPlayer.setBackground(new java.awt.Color(212,208,200));
				btnPlayer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnPlayerActionPerformed(evt);
					}
				});
			}
			{
				btnGoAlliance = new JButton();
				this.add(btnGoAlliance);
				btnGoAlliance.setText("Alliance");
				btnGoAlliance.setBounds(7, 364, 77, 28);
				btnGoAlliance.setBackground(new java.awt.Color(212,208,200));
				btnGoAlliance.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnGoAllianceActionPerformed(evt);
					}
				});
			}
			{
				btnAbout = new JButton();
				this.add(btnAbout);
				btnAbout.setText("About");
				btnAbout.setBounds(91, 497, 168, 28);
				btnAbout.setBackground(new java.awt.Color(212,208,200));
				btnAbout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnAboutActionPerformed(evt);
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void info(int x,int y){
		lblxy.setText("("+x+" , "+y+")");
	}
	public void info(Village v){
		if (v==null)return;
		txtId.setText(""+v.id);
		txtPlayer.setText(v.player);
		txtTrib.setText(Village.tribe(v.tid));
		txtX.setText(""+v.x);
		txtY.setText(""+v.y);
		txtVillage.setText(v.village);		
		txtAlliance.setText(v.alliance);
		txtPopulation.setText(""+v.population);

	}

	public void setTravianPanel(TravianPanel tp) {
		this.tp = tp;
	}
	
	private void btnAddInterestVilagesActionPerformed(ActionEvent evt) {
		JTextArea txt=new JTextArea();
		int result = JOptionPane.showOptionDialog(tp 
                , new JScrollPane(txt), "Str1" 
                , JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null); 
        if(result==JOptionPane.OK_OPTION){
            String str=txt.getText();
            tp.fillInterestMap(null, str);
            //log.info(str);
        }
    }
    
	private void btnGoActionPerformed(ActionEvent evt) {
		int x,y;
		try {
			 x=Integer.parseInt( txtX.getText());
			 y=Integer.parseInt( txtY.getText());
		} catch (Exception e) {
			//log.error("x="+txtX.getText()+",y="+txtY.getText());
			return;
		}
		tp.goXY(x, y);
		
	}
	
	
	private void btnVillageActionPerformed(ActionEvent evt) {
		tp.goVillage(txtVillage.getText());
	}
	
	private void btnPlayerActionPerformed(ActionEvent evt) {
		tp.goPlayer(txtPlayer.getText());
	}
	
	private void btnGoAllianceActionPerformed(ActionEvent evt) {
		tp.goAlliance(txtAlliance.getText());
	}
	
	private void btnAboutActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(tp, new AboutPanel(), "About", JOptionPane.PLAIN_MESSAGE);

	}

}
