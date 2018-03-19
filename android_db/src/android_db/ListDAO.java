package android_db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ListDAO {

	
	public List getBsstopList(String key) {

		
		List list = new ArrayList();
		ResultSet rs = null;
		ConnectDB cdb = new ConnectDB();
		try {
			rs = cdb.getRs("SELECT * FROM networklab.bstop WHERE BSTOP_NAME like \"%"+key+"%\"");
			while (rs.next()) {//UNION SELECT * FROM networklab.bstop WHERE SBSTOP_ID like \"%"+key+"%\"
				BstopVO bstop = new BstopVO();
				bstop.setSbstop_id(rs.getString(1));
				bstop.setBstop_name(rs.getString(2));
				bstop.setBstop_id(rs.getString(3));
				bstop.setBstop_routes(rs.getString(4));
				bstop.setGpsx(rs.getString(5));
				bstop.setGpsy(rs.getString(6));
				list.add(bstop);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return list;
	}
	
	public List getBuslineList(String key) {

		
		List list = new ArrayList();
		ResultSet rs = null;
		ConnectDB cdb = new ConnectDB();
		try {
			rs = cdb.getRs("SELECT * FROM networklab.busline WHERE route_name like \"%"+key+"%\"");
			while (rs.next()) {
				BusLineVO bus = new BusLineVO();
				bus.setRoute_id(rs.getString(1));
				bus.setRoute_name(rs.getString(2));
				bus.setEnd_name(rs.getString(3));
				bus.setRoute_type(rs.getString(4));
				bus.setRoute_type_name(rs.getString(5));
				list.add(bus);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return list;
	}
	
public List getlineList(String key) {

		
		List list = new ArrayList();
		ResultSet rs = null;
		ConnectDB cdb = new ConnectDB();
		try {
			rs = cdb.getRs("SELECT * FROM networklab.buslinelist WHERE route_id like "+key+" order by num");
			while (rs.next()) {
				BusLineListVO line = new BusLineListVO();
				line.setRoute_id(rs.getString(1));
				line.setRoute_name(rs.getString(2));
				line.setBstop_name(rs.getString(3));
				line.setBstop_id(rs.getString(4));
				line.setSbstop_id(rs.getString(5));
				line.setRun_dir(rs.getString(6));
				line.setNum(rs.getString(7));
				list.add(line);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return list;
	}

public List getDrawInfo(String key) {

	
	List list = new ArrayList();
	ResultSet rs = null;
	ConnectDB cdb = new ConnectDB();
	try {
		rs = cdb.getRs("SELECT * FROM networklab.drawinfo WHERE route_id like "+key);
		while (rs.next()) {
			DrawInfoVO drawinfo = new DrawInfoVO();
			drawinfo.setRoute_id(rs.getString(1));
			drawinfo.setLinkobj_seq(rs.getString(2));
			drawinfo.setX_pos(rs.getString(3));
			drawinfo.setY_pos(rs.getString(4));
			drawinfo.setRoutelink_seq(rs.getString(5));
			drawinfo.setDir_type(rs.getString(6));
			list.add(drawinfo);	
		}
	} catch (SQLException e) {
		e.getMessage();
	}
	return list;
}


}
