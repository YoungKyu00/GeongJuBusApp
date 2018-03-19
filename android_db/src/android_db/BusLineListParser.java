package android_db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BusLineListParser {

	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ConnectDB dbc = new ConnectDB();
	private static ResultSet rs = null;
	private static Statement stmt;

	public static void main(String[] args) throws SQLException, ParseException, IOException {
		// TODO Auto-generated method stub

		getDrawInfo();
	}

	public static void getLineInfo() throws IOException, ParseException, SQLException {
		String key = "";
		List list = new ArrayList();
		conn = dbc.getConn();
		String sql = "select * from networklab.busline";
		rs = dbc.getRs(sql);
		stmt = conn.createStatement();
		int count = 1;
		while (rs.next()) {

			key = rs.getString(1);
			Document doc = Jsoup.connect("http://its.gyeongju.go.kr/bis/main/busLineDetailAjax.do?routeId=" + key)
					.ignoreContentType(true).get();

			JSONParser json = new JSONParser();
			JSONObject jobj = (JSONObject) json.parse(doc.text());
			JSONArray jArr = (JSONArray) jobj.get("lineInfo");
			for (int i = 0; i < jArr.size(); i++) {
				JSONObject tempObj = (JSONObject) jArr.get(i);
				if (tempObj != null) {
					sql = "insert into networklab.buslinelist values (\"" + tempObj.get("ROUTE_ID") + "\", " + "\""
							+ tempObj.get("ROUTE_NAME") + "\", \"" + tempObj.get("BSTOP_NAME") + "\", \""
							+ tempObj.get("BSTOP_ID") + "\", " + "\"" + tempObj.get("SBSTOP_ID") + "\",\""
							+ tempObj.get("RUN_DIR") + "\", " + "\"" + tempObj.get("NUM") + "\")";
					stmt.executeUpdate(sql);
					/*
					 * pstmt = dbc.
					 * getPstmt("insert into networklab.buslinelist values (\""
					 * + tempObj.get("ROUTE_ID") + "\", " + "\"" +
					 * tempObj.get("ROUTE_NAME") + "\", \"" +
					 * tempObj.get("BSTOP_NAME") + "\", \"" +
					 * tempObj.get("BSTOP_ID") + "\", " + "\"" +
					 * tempObj.get("SBSTOP_ID") + "\",\"" +
					 * tempObj.get("RUN_DIR") + "\", "+ "\"" +tempObj.get("NUM")
					 * + "\")");
					 */
				}
			}
			System.out.println(count + "번째 버스");
			count++;
		}

	}

	public static void getDrawInfo() throws SQLException, ParseException, IOException {
		String key = "";
		List list = new ArrayList();
		conn = dbc.getConn();
		String sql = "select * from networklab.busline";
		rs = dbc.getRs(sql);
		stmt = conn.createStatement();
		int count = 1;
		while (rs.next()) {

			key = rs.getString(1);
			Document doc = Jsoup.connect("http://its.gyeongju.go.kr/bis/main/busLineDetailAjax.do?routeId=" + key)
					.ignoreContentType(true).get();

			JSONParser json = new JSONParser();
			JSONObject jobj = (JSONObject) json.parse(doc.text());
			JSONArray jArr = (JSONArray) jobj.get("drawInfo");
			for (int i = 0; i < jArr.size(); i++) {
				JSONObject tempObj = (JSONObject) jArr.get(i);
				if (tempObj != null) {
					/*
					 * sql= "insert into networklab.buslinelist values (\"" +
					 * tempObj.get("ROUTE_ID") + "\", " + "\"" +
					 * tempObj.get("ROUTE_NAME") + "\", \"" +
					 * tempObj.get("BSTOP_NAME") + "\", \"" +
					 * tempObj.get("BSTOP_ID") + "\", " + "\"" +
					 * tempObj.get("SBSTOP_ID") + "\",\"" +
					 * tempObj.get("RUN_DIR") + "\", "+ "\"" +tempObj.get("NUM")
					 * + "\")"; stmt.executeUpdate(sql);
					 */

					sql = "insert into networklab.drawinfo values (\"" + tempObj.get("ROUTE_ID") + "\", " + "\""
							+ tempObj.get("LINKOBJ_SEQ") + "\", \"" + tempObj.get("Y_POS") + "\", \""
							+ tempObj.get("X_POS") + "\", " + "\"" + tempObj.get("ROUTELINK_SEQ") + "\",\""
							+ tempObj.get("DIR_TYPE") + "\")";
					stmt.executeUpdate(sql);

				}
			}
			System.out.println(count + "번째 버스");
			count++;
		}
	}
}
