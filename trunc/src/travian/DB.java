package travian;

//import org.apache.log4j.*;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * 
 * @author travianeatrh@googlemail.com
 *
 */

public class DB {
	//public static Logger log=Logger.getLogger(DB.class);

	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";
	private static String username = "user1";
	private static String password = "user1";
	private static Properties props = new Properties();

	public static Statement connect(String dbName){
		Statement s=null;
		try {
			props.put("user", username);
			props.put("password", password);
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(protocol +dbName, props);
			//log.info("Connected ");
			s = conn.createStatement();
		} catch (Exception e) { 
			//log.error("Error connecting database: "+dbName );
			e.printStackTrace();
		}
		return s;

	}
	public static void close(Statement s){
		try {
			Connection conn=s.getConnection();
			s.close();
			conn.close();
		} catch (Exception e) {}
	}
	private static String decodeLine(String ln){
		int index=ln.indexOf("INSERT INTO");
		ln=ln.substring(index);
		ln=ln.replace("`", "");
		ln=ln.replace(";", "");
		return ln;
	}

	public static HashSet<Integer> getIdQuery(String sqlStr,Statement s){
		if (s==null){
			//log.error("null Statemet");
			return null;
		}
		String sql="select x,y from x_world "+sqlStr;
		HashSet<Integer> result=new HashSet<Integer>();
		try {
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				int x=rs.getInt("x");
				int y=rs.getInt("y");
				result.add(Village.key(x, y));
			}
			rs.close();
		} catch (Exception e) {}
		return result;
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					//log.info("Could not delete old database");
					return false;
				}
			}
		}
		// The directory is now empty so delete it
		return dir.delete();
	}

	public static void main(String[] args) throws Exception{
		//testQuery();
		//testLoadDatabase();
	}

	static void testQuery()throws Exception{
		//log.info("start testing query");
		Statement s=DB.connect("data/db1");
		HashSet<Integer> r=null;
		for (int i = 0; i < 20; i++) {
			r=DB.getIdQuery("", s);
			//log.info("len(getIdQuery "+i+ ") = "+r.size());
			r.clear();
		}
		s.getConnection().close();
		s.close();
		s=DB.connect("data/db1");
		r=DB.getIdQuery("where x<0 and y<0", s);
		//log.info("len(getIdQuery 2) = "+r.size());

		//log.info("end test query");
	}
	
}


