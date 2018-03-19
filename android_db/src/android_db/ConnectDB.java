package android_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ConnectDB {

	private static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	}

	public ConnectDB() {
	}

	String Url = "jdbc:mysql://localhost:3306/networklab?autoReconnect=true&useSSL=false";
	String Id = "young";
	String Pw = "1370";
	String sql = "select * from bstop";

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	List list = new ArrayList();
	
	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Url,Id,Pw);
		} catch (ClassNotFoundException e) {
			e.getStackTrace();
			System.err.println("ClassNotFoundException");
		} catch (SQLException e) {
			e.getSQLState();
		}
		return conn;
	}
	public ResultSet getRs(String sql) {
		try {
			ConnectDB dbc = new ConnectDB();
			conn = dbc.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public PreparedStatement getPstmt(String sql){
		try{
			ConnectDB dbc = new ConnectDB();
			conn = dbc.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.getSQLState();
		}
		
		return pstmt;
	}

}
