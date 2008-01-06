package travian;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

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
public class TravianPanel extends JPanel{
	//public static Logger log=Logger.getLogger(TravianPanel.class);

	static String[] adjust(String ln){
		String[] result=new String[11];
		boolean wordIsClosed=true;
		String s="";
		int counter=0;
		for (int i = 0; i < ln.length(); i++) {
			if(ln.charAt(i)==',' && wordIsClosed){
				result[counter++]=s;
				s="";
				continue;
			}
			if(ln.charAt(i)=='\'')
				wordIsClosed=!wordIsClosed;				
			s+=ln.charAt(i);
		}
		result[counter]=s;
		return result;
	}


	static String[] adjustArray(String[] arr){
		if(arr.length==11)return arr;
		String[] result=new String[11];

		for (int i = 0; i < 5; i++) {
			result[i]=arr[i];
		}
		for(int i=5,j=0; (i+j)<arr.length-1;i++)
			if(i%2==0)
				result[i]=arr[i+j];
			else
				if( arr[i+j].endsWith("'"))
					result[i]=arr[i+j];
				else{
					result[i]=arr[i+j]+arr[i+j+1];
					j++;					
				}

		return arr;

	}

	public static HashSet<Integer> query(Condition cond, HashMap<Integer, Village> map){
		HashSet<Integer> result=new HashSet<Integer>();
		for (Entry<Integer, Village> iter : map.entrySet()) {
			if(cond.accept(iter.getValue()))
				result.add(iter.getKey());
		}
		return result;
	}
	private JSeparator sep1;
	private JMenuItem mnuSetAsHome;
	private JMenuItem mnuResetAllColors;
	private JSeparator sep4;
	private JMenuItem mnueResetZoom;
	private JMenuItem mnuGoAlliance;
	private JMenuItem mnuGo;
	private JMenuItem mnuResetTribeColor;
	private JMenuItem mnuResetAllianceColor;
	private JMenuItem mnuResetColor;
	private JMenuItem mnuGoHome;
	private JCheckBoxMenuItem mnuRuler;
	private JSeparator sep3;
	private JMenuItem mnuSetTribeColor;
	private JMenuItem mnuSetAllicanceColor;
	private JMenuItem mnueSetColor;
	private JSeparator sep2;
	private JMenuItem mnuZoomOut;

	private JMenuItem mnuZoomIn;
	private JPopupMenu mnu;

	/*
	 * Added by 
	 */
	private InfoPanel ip;
	private final int w;
	private HashMap<Integer, Village> m=new HashMap<Integer, Village>();
	private HashSet<Integer> winSet=new HashSet<Integer>();

	private HashMap<Integer, HashSet<Integer>> alliance=new HashMap<Integer, HashSet<Integer>>();
	private HashSet<Integer> interestSet=new HashSet<Integer>();

	private Village curV=null;
	private Village home=null;
	private Village ruler=null;
	private boolean lock=false;

	private int x=0;
	private int y=0;
	private int x1=-300;
	private int y1=-300;
	private int x2=300;
	private int y2=300;
	private int sqr=1;//600/(x2-x1);

	/*
	 * Conditions used in queries
	 */
	Condition condWinSet=new Condition(){
		public boolean accept(Village v){			
			return v.x > x1 && v.x <= x2 && v.y > y1 && v.y <= y2;
		}
	};

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public TravianPanel() {
		super();
		initGUI();
		w=getHeight();
	}
	void center(int x,int y){
		int xx=(x2-x1)/2;
		x1=x-xx;
		x2=x+xx;

		int yy=(y2-y1)/2;
		y1=y-yy;
		y2=y+yy;
		setWindow();
	}

	private  Color color(String title, Color initColor){
		Color c = JColorChooser.showDialog(this, title, initColor);
		if(c!= null)return c;
		return initColor;
	}


	public void  connect(){ 
		//log.info("Connecting to db");
		//m.clear();
		winSet.clear();
		winSet=new HashSet<Integer>(m.keySet());
		//log.debug("data retreived="+ winSet.size());
		repaint();
	}

	private void drawGrid(Graphics g, Color c){
		g.setColor(c);
		int shft=sqr/2;
		//cols
		for (int i=x1/10*10; i<= x2; i+=10){
			g.drawLine(xpicel(i)+shft, 0 , xpicel(i)+shft, getHeight());
		}
		//rows
		for (int i=y1/10*10; i<= y2; i+=10){
			g.drawLine(0,ypicel(i)+shft,getWidth() ,ypicel(i)+shft );
		}
	}
	private void drawRuler(Graphics g,Village v1, Village v2){
		if (v1==null || v2==null)return;
		g.setColor(Color.black);
		g.drawLine(xpicel(v1.x)+sqr/2,ypicel(v1.y)+sqr/2, xpicel(v2.x)+sqr/2,ypicel(v2.y)+sqr/2);
		g.drawLine(xpicel(v1.x)+sqr/2+1,ypicel(v1.y)+sqr/2, xpicel(v2.x)+sqr/2+1,ypicel(v2.y)+sqr/2);
		//g.drawLine(xpicel(v1.x),ypicel(v1.y), xpicel(v2.x),ypicel(v2.y));
		int xx=v1.x-v2.x;
		int yy=v1.y-v2.y;
		double distance=(double)((int)(Math.sqrt(xx*xx + yy*yy)*10))/10;
		String d=""+distance;
		g.drawChars( d.toCharArray(), 0, d.length(), xpicel(v1.x), ypicel(v1.y));
		g.drawChars( d.toCharArray(), 0, d.length(), xpicel(v1.x)+1, ypicel(v1.y));
		setToolTipText(""+distance);
	}
	void drawTest(String str){
		connect();
		System.out.println("len(winSet)= "+winSet.size());
		System.out.println("len(m)= "+m.size());
		fillInterestMap(null);
		//draw(g);
		//disconnect();

	}

	private void drawVillage(Graphics g, int key){
		Village v=m.get(key);
		g.setColor(v.c);
		int x=v.x;
		int y=v.y;
		g.fillOval(xpicel(x), ypicel(y), sqr, sqr);		
	}
	private void drawVillage(Graphics g, int key,Color c){
		Village v=m.get(key);
		g.setColor(c);
		int x=v.x;
		int y=v.y;
		g.fillOval(xpicel(x), ypicel(y), sqr, sqr);		
	}

	private void drawVillages(Graphics g,HashSet<Integer> set){
		for (Integer i : set) {
			drawVillage(g, i);
		}
	}


	private void drawVillages(Graphics g,HashSet<Integer> set, Color c){
		for (Integer i : set) {
			drawVillage(g, i,c);
		}
	}
	void fillAlliance(){
		alliance=new HashMap<Integer, HashSet<Integer>>();
		for (Entry<Integer, Village> i : m.entrySet()) {
			int aid=i.getValue().aid;
			int vKey=i.getKey();
			if(alliance.containsKey(aid)){
				alliance.get(aid).add(i.getKey());
			}else{
				HashSet<Integer> ts=new HashSet<Integer>();
				ts.add(vKey);
				alliance.put(aid, ts);
			};
		}
	}

	public void fillInterestMap(JFrame frame,String txt){
		Color c=color("Pick a Color ", Color.red);
		String[] line=txt.trim().split("\n");
		for(int i = 0; i < line.length; i++) {

			if(line[i].startsWith("#"))continue;
			if(line[i].length() < 3)continue;

			String[] arr=line[i].split(",");
			int x,y;
			try {
				x=Integer.parseInt( arr[0].trim());
				y=Integer.parseInt( arr[1].trim());

			} catch (Exception e) {

				//log.error("Integer parsing");
				continue;
			}
			Village v=m.get(Village.key(x, y));
			if (v==null)continue;
			v.c=c;
			interestSet.add(v.key());
		}		
	}
	public HashMap<Integer, Color> fillInterestMap(String filename){
		HashMap<Integer, Color> interestMap=new HashMap<Integer, Color>();

		try {
			if(filename==null)
				filename="data/my.txt";

			BufferedReader myin = new BufferedReader(new FileReader(filename));
			String mystr;
			Color c=Color.red;
			while ((mystr = myin.readLine()) != null) {
				if(mystr.startsWith("#"))continue;
				if (mystr.startsWith("@")){
					c=getColor(mystr.substring(1));
					if (c==Color.black)System.out.println("error");
					continue;
				}
				if(mystr.length() < 3)continue;

				String[] arr=mystr.split(",");
				int x=Integer.parseInt( arr[0].trim());
				int y=Integer.parseInt( arr[1].trim());
				Village v=m.get(Village.key(-x, -y));
				if (v==null)continue;
				v.c=Color.red;
				interestMap.put(v.key(), c);
			}
			System.out.println("im sz "+interestMap.size());
			myin.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return interestMap;
	}

	Color getColor(int i){
		Color[] carr={
				Color.black,
				Color.cyan,
				Color.darkGray,
				Color.green,
				Color.lightGray,
				Color.magenta,
				Color.orange,
				Color.pink,
				Color.red,
				Color.white,
				Color.yellow,
		};
		if(i<carr.length)
			return carr[i];
		else return Color.yellow;
	}

	Color getColor(String clrName){
		clrName=clrName.toLowerCase().trim();
		if(clrName=="bule")return Color.blue;
		if(clrName=="cyne")return Color.cyan;
		if(clrName=="darkgray")return Color.darkGray;
		if(clrName=="green")return Color.green;
		if(clrName=="lightgray")return Color.lightGray;
		if(clrName=="magenta")return Color.magenta;
		if(clrName=="orange")return Color.orange;
		if(clrName=="pink")return Color.pink;
		if(clrName=="red")return Color.red;
		if(clrName=="white")return Color.white;
		if(clrName=="yellow")return Color.yellow;
		return Color.red;
	}
	//public Village getVillage(int key){
	//return m.get(key);
	//}

	public void goAlliance(int aid, Color c){
		TreeSet<Integer> xx=new TreeSet<Integer>();
		TreeSet<Integer> yy=new TreeSet<Integer>();
		int sumX=0;
		int sumY=0;
		for(Integer key: alliance.get(aid)){
			Village v= m.get(key);
			if(v.aid== aid){
				v.c=c;
				xx.add(v.x);
				yy.add(v.y);
				sumX+=v.x;
				sumY+=v.y;
			}
		}
		if(xx.size()==0){
			//log.info("No alliance found");
			return;
		}

		initDimensions();
		zoomIn(sumX/xx.size(), sumY/yy.size());
		zoomIn(sumX/xx.size(), sumY/yy.size());
		repaint();
	}
	public void goAlliance(String alnc){
		ArrayList<String> values=new ArrayList<String>();
		ArrayList<Integer> keys=new ArrayList<Integer>();

		for(HashSet<Integer> iter:alliance.values()){
			Village v= m.get(iter.iterator().next());
			if(v.alliance.toLowerCase().contains(alnc.toLowerCase())){
				keys.add(v.aid);
				values.add(v.alliance);
			}
		}
		if(keys.size()==0){
			JOptionPane.showMessageDialog(this, "There is no alliance in this name:" +alnc);
			return;
		}
		
		Color c=color("Choose Alliance Color",Color.blue);
		if(keys.size()==1){
			goAlliance(keys.get(0), c);
			return;
		}
		int index=choose("Which alliance?", values);
		if (index==-1)return;
		goAlliance(keys.get(index), c);
		return;

	}

	public void goPlayer(String player){
		ArrayList<String> values=new ArrayList<String>();
		ArrayList<Integer> keys=new ArrayList<Integer>();

		for(Village v:m.values()){
			if(v.player.toLowerCase().contains( player.toLowerCase())){
				keys.add(v.key());
				values.add(v.player);
			}
		}
		if(keys.size()==0){
			JOptionPane.showMessageDialog(this, "There is no player in this name:" +player);
			return;
		}
		if(keys.size()==1){
			goXY(keys.get(0));
			return;
		}
		int index=choose("Which player?", values);
		if (index==-1)return;
		goXY(keys.get(index));

		//log.error("Player "+ player + " was not found");
	}

	public void goVillage(String vil) {
		ArrayList<String> values=new ArrayList<String>();
		ArrayList<Integer> keys=new ArrayList<Integer>();
		for(Village v:m.values()){
			if(v.village.toLowerCase().contains( vil.toLowerCase())){
				keys.add(v.key());
				values.add(v.village);
			}
		}
		if(keys.size()==0){
			JOptionPane.showMessageDialog(this, "There is no village in this name:" +vil);
			return;
		}
		
		if(keys.size()==1){
			goXY(keys.get(0));
			return;
		}
		int index=choose("Which village?", values);
		if (index==-1)return;
		goXY(keys.get(index));


		//log.error("Village "+ vil + " was not found");
	}
	public static int choose(String msg,  List list){
		String result=(String)JOptionPane.showInputDialog(null, msg, "Search Results",
				JOptionPane.QUESTION_MESSAGE, null, list.toArray(), "");
		System.out.println(list.indexOf(result));
		return list.indexOf(result);
	}

	public void goXY(int x,int y) {
		int key=Village.key(x, y);
		goXY(key);
	}
	public void goXY(int key) {
		if(! m.containsKey(key)){
			//log.error("Village not found");
			return;
		}
		Village v=m.get(key);
		goXY(v,Color.green);
	}

	
	public void goXY(Village v, Color c) {
		v.c=c;
		interestSet.add(v.key());
		initDimensions();
		
		zoomIn(v.x, v.y);			
		zoomIn(v.x, v.y);			
		zoomIn(v.x, v.y);	
		zoomIn(v.x, v.y);	
		center(v.x, v.y);

		
		setWindow();
		repaint();		
	}
		
	public void goXY(int x,int y, Color c) {
		int key=Village.key(x, y);
		if(! m.containsKey(key)){
			//log.error("Village not found");
			return;
		}
		Village v=m.get(key);
		v.c=c;
		interestSet.add(key);
		initDimensions();
		center(x, y);
		
		zoomIn(v.x, v.y);			
		zoomIn(v.x, v.y);			
		zoomIn(v.x, v.y);	
		
		setWindow();
		repaint();

	}

	void highlightAlliance(int oldAid, int aid, Color c){
		if (oldAid != 0)
			drawVillages(getGraphics(), alliance.get(oldAid));
		if (aid >0 ){
			drawVillages(getGraphics(), alliance.get(aid), c);
		}
	}
	private void initDimensions(){
		x=0;
		y=0;
		x1=-300;
		y1=-300;
		x2=300;
		y2=300;
		sqr=1;//600/(x2-x1);		
	}
	private void initGUI() {
		try {
			{
				this.setSize(600, 600);
				this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				this.setPreferredSize(new java.awt.Dimension(600, 600));
				this.setBackground(new java.awt.Color(253,253,253));
				this.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						if (evt.getButton() == evt.BUTTON1)
							lock = !lock;
						ip.infoLock(lock);

					}
				});
				this.addMouseWheelListener(new MouseWheelListener() {
					public void mouseWheelMoved(MouseWheelEvent evt) {
						rootMouseWheelMoved(evt);
					}
				});
				this.addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseDragged(MouseEvent evt) {
						rootMouseDragged(evt);
					}
					public void mouseMoved(MouseEvent evt) {
						rootMouseMoved(evt);
					}
				});
				{
					mnu = new JPopupMenu();
					setComponentPopupMenu(this, mnu);
					{
						mnuZoomIn = new JMenuItem();
						mnu.add(mnuZoomIn);
						mnuZoomIn.setText("Zoom In +");
						mnuZoomIn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								zoomIn(x, y);
							}
						});
					}
					{
						mnuZoomOut = new JMenuItem();
						mnu.add(mnuZoomOut);
						mnuZoomOut.setText("Zoom Out -");
						mnuZoomOut.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								zoomOut(x,y);
							}
						});
					}
					{
						mnueResetZoom = new JMenuItem();
						mnu.add(mnueResetZoom);
						mnueResetZoom.setText("Reset Zoom");
						mnueResetZoom.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnueResetZoomActionPerformed(evt);
							}
						});
					}
					{
						sep1 = new JSeparator();
						mnu.add(sep1);
					}
					{
						mnuGo = new JMenuItem();
						mnu.add(mnuGo);
						mnuGo.setText("Go");
						mnuGo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuGoActionPerformed(evt);
							}
						});
					}
					{
						mnuGoHome = new JMenuItem();
						mnu.add(mnuGoHome);
						mnuGoHome.setText("Go Home");
						mnuGoHome.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuGoHomeActionPerformed(evt);
							}
						});
					}
					{
						mnuGoAlliance = new JMenuItem();
						mnu.add(mnuGoAlliance);
						mnuGoAlliance.setText("Go Alliance");
						mnuGoAlliance.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuGoAllianceActionPerformed(evt);
							}
						});
					}
					{
						mnuSetAsHome = new JMenuItem();
						mnu.add(mnuSetAsHome);
						mnuSetAsHome.setText("Set as Home");
						mnuSetAsHome.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuSetAsHomeActionPerformed(evt);
							}
						});
					}
					{
						sep2 = new JSeparator();
						mnu.add(sep2);
					}
					{
						mnueSetColor = new JMenuItem();
						mnu.add(mnueSetColor);
						mnueSetColor.setText("Set Color");
						mnueSetColor.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnueSetColorActionPerformed(evt);
							}
						});
					}
					{
						mnuSetAllicanceColor = new JMenuItem();
						mnu.add(mnuSetAllicanceColor);
						mnuSetAllicanceColor.setText("Set Alliance Color");
						mnuSetAllicanceColor
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuSetAllicanceColorActionPerformed(evt);
							}
						});
					}
					{
						mnuSetTribeColor = new JMenuItem();
						mnu.add(mnuSetTribeColor);
						mnuSetTribeColor.setText("Set Tribe Color");
						mnuSetTribeColor
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuSetTribeColorActionPerformed(evt);
							}
						});
					}
					{
						sep3 = new JSeparator();
						mnu.add(sep3);
					}
					{
						mnuResetColor = new JMenuItem();
						mnu.add(mnuResetColor);
						mnuResetColor.setText("Reset Color");
						mnuResetColor.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuResetColorActionPerformed(evt);
							}
						});
					}
					{
						mnuResetAllianceColor = new JMenuItem();
						mnu.add(mnuResetAllianceColor);
						mnuResetAllianceColor.setText("Reset Alliance Color");
						mnuResetAllianceColor
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuResetAllianceColorActionPerformed(evt);
							}
						});
					}
					{
						mnuResetTribeColor = new JMenuItem();
						mnu.add(mnuResetTribeColor);
						mnuResetTribeColor.setText("Reset Tribe Color");
						mnuResetTribeColor
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuResetTribeColorActionPerformed(evt);
							}
						});
					}
					{
						mnuResetAllColors = new JMenuItem();
						mnu.add(mnuResetAllColors);
						mnuResetAllColors.setText("Reset All Colors");
						mnuResetAllColors
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuResetAllColorsActionPerformed(evt);
							}
						});

					}
					{
						sep4 = new JSeparator();
						mnu.add(sep4);
					}
					{
						mnuRuler = new JCheckBoxMenuItem();
						mnu.add(mnuRuler);
						mnuRuler.setText("Ruler");
						mnuRuler.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuRulerActionPerformed(evt);
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadSqlFile(String sqlFileName,JFrame frame){
		m.clear();
		winSet.clear();
		ProgressMonitor pm = new ProgressMonitor(frame, "Creates and loads database from \n"+sqlFileName, "0 % completed", 0, 100);
		// To make progress monitor popup immediately, set both to 0
		pm.setMillisToPopup(0);
		pm.setMillisToDecideToPopup(0);

		int counter=0;
		try {
			String ln;
			FileInputStream fis = new FileInputStream(sqlFileName);
			InputStreamReader isr = new InputStreamReader(fis, "UTF8");

			BufferedReader myin =new BufferedReader(isr);
			//log.info("Start loading the rows");
			while ((ln = myin.readLine()) != null) {
				int index=ln.indexOf("INSERT INTO");
				ln=ln.substring(index);
				ln=ln.substring(30);
				ln=ln.substring(0,ln.length()-2);

				String[] arr=ln.split(",");
				if(arr.length>11)
					arr=adjust(ln);
				try {
				} catch (Exception e) {
					//log.error("arr length="+ arr.length);
					String msgLog="";
					for (int i = 0; i < arr.length; i++) {
						msgLog+=arr[i]+"\t";
					}
					//log.error(msgLog);
					e.printStackTrace();
				}
				Village v=new Village(arr);
				m.put(v.key(),v);
				winSet.add(v.key());

				if (counter++ % 320 ==0){
					pm.setNote(""+counter/320+" % completed");
					pm.setProgress(counter/320);
				}

			}
			pm.setNote("100 % completed");
			pm.setProgress(100);

			//log.info("loading rows completed "+m.size());

			///test interest masp
			//fillInterestMap("data/my.txt");
			fillAlliance();
			setWindow();
			repaint();

		} catch (Exception e) {
			//log.error("Error loading map");
			e.printStackTrace();//to be deleted later
		}

	}

	private void mnueResetZoomActionPerformed(ActionEvent evt) {
		initDimensions();
		setWindow();
		repaint();
	}

	private void mnueSetColorActionPerformed(ActionEvent evt) {
		if(curV==null)return;
		curV.c=color("Set Village Color ", curV.c);
		interestSet.add(curV.key());
		repaint();

	}

	private void mnuGoActionPerformed(ActionEvent evt) {
		if (curV.c == Village.tribeColor[curV.tid])
			curV.c=Color.green;
		goXY(curV.x, curV.y, curV.c);
	}

	private void mnuGoAllianceActionPerformed(ActionEvent evt) {
		if(curV.aid > 0){
			Color c=color("Choose alliance color", curV.c);
			goAlliance(curV.aid,c);
		}
	}
	private void mnuGoHomeActionPerformed(ActionEvent evt) {
		if(home==null)return;
		center(home.x, home.y);
		for (int i = sqr/2; i < 4; i++) {
			zoomIn(home.x, home.y);			
		}
		setWindow();
		repaint();

	}

	private void mnuResetAllColorsActionPerformed(ActionEvent evt) {
		for (int i=0; i<5; i++){
			Village.tribeColor[i]=Color.lightGray;
		}
		for (Village v : m.values()) {
			v.resetColor();
		}
		if (home!=null)home.c=Color.red;
		repaint();
	}

	private void mnuResetAllianceColorActionPerformed(ActionEvent evt) {
		for (Integer i : alliance.get(curV.aid)) {
			Village v=m.get(i);
			v.resetColor();
		}
		repaint();
	}

	private void mnuResetColorActionPerformed(ActionEvent evt) {
		if(curV==null)return;
		curV.resetColor();
		interestSet.remove(curV);
		repaint();

	}

	private void mnuResetTribeColorActionPerformed(ActionEvent evt) {
		Village.tribeColor[curV.tid]=Color.lightGray;
		for (Village v : m.values()) {
			if (v.tid ==curV.tid )
				v.resetColor();
		}
		repaint();
	}
	private void mnuRulerActionPerformed(ActionEvent evt) {
		if(mnuRuler.isSelected())
			if(curV != null){
				ruler=curV;				
			}else{
				mnuRuler.setSelected(false);
				setToolTipText(null);
			}
	}

	private void mnuSetAllicanceColorActionPerformed(ActionEvent evt) {
		if(curV==null || curV.aid==0){
			JOptionPane.showMessageDialog(this, "No village or alliance was selected", "Alliance not found", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Color c=color("Set Alliance Color ", curV.c);

		//log.info("Set alliance color for aid ="+ curV.aid);

		for (Integer i : alliance.get(curV.aid)) {
			Village v=m.get(i);
			v.c=c;
		}
//		CondAllianceColor ca=new CondAllianceColor(curV.aid,c);
//		query(ca, m);
		repaint();
//		//log.info("total villages"+ ca.totalVillages);
//		//log.info("total population"+ ca.totalPopulation);


	}

	private void mnuSetAsHomeActionPerformed(ActionEvent evt) {
		if(curV==null){
			JOptionPane.showMessageDialog(null, "Select a village first !");
			return;
		}
		if(home != null){
			Village v=m.get(home.key());
			v.c=Color.lightGray;
		}
		home=curV;
		home.c=Color.red;
		repaint();

	}

	private void mnuSetTribeColorActionPerformed(ActionEvent evt) {
		if(curV==null ){
			JOptionPane.showMessageDialog(this, "No village  was selected", "Village was not found", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Village.tribeColor[curV.tid]=color("Set Tribe Color ", Village.color(curV.tid));


		for (Village v : m.values()) {
			if (v.tid ==curV.tid )
				v.resetColor();
		}
		repaint();

	}
	public void paintComponent( Graphics g ){
		super.paintComponent( g ); 

		drawGrid(g,Color.lightGray);
		drawVillages(g,winSet);
		for (Integer i : interestSet) {
			drawVillage(g, i);
		}
		if(mnuRuler.isSelected())
			drawRuler(g, curV, ruler);
		//highlightAlliance();
		//if (isDrawAlliance)	drawAlliance(g);
	}

	private int picselToKey(int x,int y){
		int keyX= x/sqr+x1;
		int keyY= (w-y)/sqr+y1+1;
		return Village.key(keyX, keyY);

	}

	private void rootMouseDragged(MouseEvent evt) {
		int key=picselToKey(evt.getX(), evt.getY());
		int wx=Village.x(key)-x;
		int wy=Village.y(key)-y;

		x1=x1-wx;
		x2=x2-wx;

		y1=y1-wy;
		y2=y2-wy;
		setWindow();
		repaint();
	}
	private void rootMouseMoved(MouseEvent evt) {
		//repaint();
		if (lock)return;
		int key=picselToKey(evt.getX(), evt.getY());
		x=Village.x(key);
		y=Village.y(key);
		ip.info(x,y);
		Village v=m.get(key);
		if (v==null || v==curV)return;
		ip.info(v);
		//added to show alliances
		if(curV!=null){
			highlightAlliance(curV.aid, v.aid, Color.black);
		}
		curV=v;
		if(mnuRuler.isSelected()){
			repaint();
		}
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(
			final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
		});
	}
	public void setInfoPanel(InfoPanel ip){
		this.ip=ip;
	}

	public void setInterestSet(HashSet<Integer> interestSet) {
		this.interestSet = interestSet;
	}

	void setPanelDimensions(int px1,int py1, int px2,int py2){
		x1=px1;
		y1=py1;
		x2=px2;
		y2=py2;
		sqr=this.getHeight()/(y2-y1);		
	}

	private void setWindow(){
		winSet.clear();
		winSet = query(condWinSet,m);
	}

	public void setWinSet(HashSet<Integer> winSet) {
		this.winSet = winSet;
	}

	private int xpicel(int x){
		return sqr*(x-x1);
	}

	private int ypicel(int y){
		return w-sqr*(y-y1);
	}

	public void zoomIn(int x,int y) {
		sqr*=2;
		int xx=(x2-x1)/4;
		x1=x-xx;
		x2=x+xx;

		int yy=(y2-y1)/4;
		y1=y-yy;
		y2=y+yy;
		setWindow();
		repaint();
	}

	public void zoomOut() {
		int x=(x1+x2)/2;
		int y=(y2+y2)/2;
		zoomOut(x,y);

	}

	public void zoomOut(int x,int y) {
		if(sqr==1){
			initDimensions();
			setWindow();
			repaint();
			return;
		}

		sqr/=2;
		int xx=(x2-x1);
		x1=x-xx;
		x2=x+xx;

		int yy=(y2-y1);
		y1=y-yy;
		y2=y+yy;

		setWindow();
		repaint();
	}

	private void rootMouseWheelMoved(MouseWheelEvent evt) {

		int wheelscroll=evt.getScrollAmount();
		if (wheelscroll<3)return;
		int rotation=evt.getWheelRotation();
		if (rotation==1)
			zoomIn(x, y);
		else
			zoomOut(x,y);
	}

};


