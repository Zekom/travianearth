package travian;

//import org.apache.log4j.*;

import java.awt.Color;

public class Village {
	//public static Logger log=Logger.getLogger(Village.class);
	private static final int range=300;
	private static final int shift=100000;
	
	int id,x,y,tid,vid,uid,aid,population;
	String 	village,player,alliance;
	Color c;
	
	
	public static Color[] tribeColor={
			Color.lightGray,
			new Color(210,192,192),
			new Color(192,220,192),
			new Color(192,192,210),
			Color.lightGray,
			Color.lightGray
	};
	
	public static Color color(int tid){
		return tribeColor[tid];
	}
	
	public void resetColor(){
		c=color(tid);
	}
	public Village(String[] arr){
		setV(arr);
	}
	void setV(String[] arr){
		try {
		id=Integer.parseInt(arr[0]);
		x=Integer.parseInt(arr[1]);
		y=Integer.parseInt(arr[2]);
		tid=Integer.parseInt(arr[3]);
		vid=Integer.parseInt(arr[4]);
		c=tribeColor[tid];
		village=arr[5].substring(1,arr[5].length()-1);
		uid=Integer.parseInt(arr[6]);
		player=arr[7].substring(1,arr[7].length()-1);
		aid=Integer.parseInt(arr[8]);
		alliance=arr[9].substring(1,arr[9].length()-1);
		population=Integer.parseInt(arr[10]);
		} catch (Exception e) {
			//log.error("arr length="+ arr.length);
			String msgLog="";
			for (int i = 0; i < arr.length; i++) {
				msgLog+=arr[i]+"\t";
			}
			//log.error(msgLog);
		}
	}
	
	public int key(){
		return key(x, y);
	}
	
	public static int  key(int x,int y){
		return (range-x)+(range-y)*shift;
	}
	public static int x(int k){
		return range-k%shift;
	}
	public static int y(int k){
		return range-k/shift;
	}
	
	public static String tribe(int tid){
		switch (tid) {
		case 1:
			return "Roman";
		case 2:
			return "Teuton";
		case 3:
			return "Gaul";
		case 4:
			return "Nature";
		case 5:
			return "Natars";
		default:
			return "Error";
		}
	}


}
