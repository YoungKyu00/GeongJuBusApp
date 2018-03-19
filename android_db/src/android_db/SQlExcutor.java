package android_db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SQlExcutor {

	private static BufferedReader br = null;
	private static String s;
	private static Connection conn = null;
	private static String file = "JsonData/busLineList.txt";
	private static ConnectDB dbc = new ConnectDB();
	private static PreparedStatement pstmt= null;
	
	public static void main(String[] args) {	

		//executeBusLine();
		executeBstopList();
	}

	public static void executeBusLine() {

		ArrayList<BusLineVO> busArray = new ArrayList<BusLineVO>();

		try {
			br = new BufferedReader(new FileReader("JsonData/busLineList.txt"));
			FileReader fr = new FileReader(file);
			conn = dbc.getConn();
			String strSQL;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}

			JSONParser json = new JSONParser();
			JSONObject jobj = (JSONObject) json.parse(fr);
			JSONArray jArr = (JSONArray) jobj.get("busLineList");
			for (int i = 0; i < jArr.size(); i++) {
				JSONObject tempObj = (JSONObject) jArr.get(i);
				if (tempObj != null && tempObj.get("SBSTOP_ID") != null) {
					System.out.println("route_name = " + tempObj.get("SBSTOP_ID"));
				pstmt =	conn.prepareStatement("insert into networklab.bstop values (\"" + tempObj.get("SBSTOP_ID") + "\", " + "\""
							+ tempObj.get("BSTOP_NAME") + "\", \"" + tempObj.get("BSTOP_ID") + "\", \""
							+ tempObj.get("BSTOP_ROUTES") + "\", " + "\"" + tempObj.get("GPSY_POS") + "\", \"" +tempObj.get("GPSX_POS") + "\")");
				}
			}
			br.close();
		}catch(SQLException e){
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);

		} catch (ParseException e) {
			System.err.println(e);
		}
	}
	
	public static void executeBstopList() {

		ArrayList<BstopVO> bstopArray = new ArrayList<BstopVO>();

		try {
			br = new BufferedReader(new FileReader("JsonData/bstopList.txt"));
			FileReader fr = new FileReader("JsonData/bstopList.txt");
			conn = dbc.getConn();
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}
			int count =0;
			JSONParser json = new JSONParser();
			JSONObject jobj = (JSONObject) json.parse(fr);
			JSONArray jArr = (JSONArray) jobj.get("searchList");
			for (int i = 0; i < jArr.size(); i++) {
				count++;
				System.out.println(count);
				JSONObject tempObj = (JSONObject) jArr.get(i);
				if(tempObj.get("SBSTOP_ID")!= null){
				if (tempObj != null) {
					System.out.println("insert into networklab.bstop values (\"" + tempObj.get("SBSTOP_ID") + "\", " + "\""
							+ tempObj.get("BSTOP_NAME") + "\", \"" + tempObj.get("BSTOP_ID") + "\", \""
							+ tempObj.get("BSTOP_ROUTES") + "\", " + "\"" + tempObj.get("GPSY_POS") + "\", \""+tempObj.get("GPSX_POS")+ "\")");
				try {
					pstmt=	dbc.getPstmt("insert into networklab.bstop values (\"" + tempObj.get("SBSTOP_ID") + "\", " + "\""
								+ tempObj.get("BSTOP_NAME") + "\", \"" + tempObj.get("BSTOP_ID") + "\", \""
								+ tempObj.get("BSTOP_ROUTES") + "\", " + "\"" + tempObj.get("GPSY_POS") + "\", \""+tempObj.get("GPSX_POS")+ "\")");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				}
			}
				
		}catch (IOException e) {
			System.err.println(e);
			System.exit(1);	

		} catch (ParseException e) {	
			System.err.println(e);
		} 
	}
}
