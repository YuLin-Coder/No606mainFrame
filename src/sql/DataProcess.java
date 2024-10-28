package sql;
import java.sql.*;

public class DataProcess {
	private Connection conn = null;
	public Connection getConn() {//鑾峰彇jdbc杩炴帴瀵硅薄
	    String driver = "com.mysql.cj.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/car_manager?serverTimezone=GMT&characterEncoding=utf-8";
	    String username = "root";
	    String password = "root";
	    try {
	        Class.forName(driver); 
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	        return conn;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public int Count(String sql) {//鏌ヨ鏁版嵁搴撴暟閲�
		
		getConn();
		int count=0;
		try {
			Statement stm=conn.createStatement();
			ResultSet result=stm.executeQuery(sql);
			if(result.next()) {
				count=result.getInt(1);
				result.close();
			}
			stm.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public int isExist(String sql) {
		int i=0;
		getConn();
		try {
			Statement stm=conn.createStatement();
			ResultSet result=stm.executeQuery(sql);
			if(result.next()) {
				i=1;
				result.close();
			}
			stm.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
    public int ExeQuery(String sql) {//鎵ц涓�鏉ql璇彞,骞惰繑鍥炴墽琛岀殑缁撴灉,-1琛ㄧず澶辫触,1琛ㄧず鎴愬姛
    	int done=-1;
    	getConn();
    	try {
    		Statement stm=conn.createStatement();
    		stm.executeUpdate(sql);
    		stm.close();
    		conn.close();
    		done=1;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return done;
    }
    public ResultSet getResult(String sql) {
    	getConn();
    	ResultSet rs=null;
    	try {
    		Statement stm=conn.createStatement();
    		rs=stm.executeQuery(sql);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return rs;
    }
    public void CloseResultSet(ResultSet rs) {
    	try {
    		rs.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    public void closeConn() {
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
