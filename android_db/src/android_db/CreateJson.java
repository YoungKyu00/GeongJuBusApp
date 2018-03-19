package android_db;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJson {

	
	
	
	public JSONObject getBstop(String key){
	ListDAO bstopList = new ListDAO();

	List r = bstopList.getBsstopList(key);

	Iterator p = r.iterator();
	BstopVO bstop = null;

	JSONObject bstopObject = new JSONObject();
	JSONArray bstopArr = new JSONArray();

	while (p.hasNext()) {
		bstop = (BstopVO) p.next();
		JSONObject bstopInfo = new JSONObject();

		bstopInfo.put("sbstop_id", bstop.getSbstop_id());
		bstopInfo.put("Bstop_name", bstop.getBstop_name());
		bstopInfo.put("Bstop_id", bstop.getBstop_id());
		bstopInfo.put("Bstop_routes", bstop.getBstop_routes());
		bstopInfo.put("gpsx", bstop.getGpsx());
		bstopInfo.put("gpsy", bstop.getGpsy());
		bstopArr.add(bstopInfo);
	}
	bstopObject.put("bstopList", bstopArr);
	System.out.println(bstopObject);
	
	return bstopObject;
	}
	
	public JSONObject getBusline(String key){
		ListDAO buslineList = new ListDAO();

		List r = buslineList.getBuslineList(key);
		Iterator p = r.iterator();
		BusLineVO bus = null;

		JSONObject busObject = new JSONObject();
		JSONArray busArr = new JSONArray();

		while (p.hasNext()) {
			bus = (BusLineVO) p.next();
			JSONObject busInfo = new JSONObject();

			busInfo.put("route_id", bus.getRoute_id());
			busInfo.put("route_name", bus.getRoute_name());
			busInfo.put("end_name", bus.getEnd_name());
			busInfo.put("route_type", bus.getRoute_type());
			busInfo.put("route_type_name", bus.getRoute_type_name());
			busArr.add(busInfo);
		}
		busObject.put("buslineList", busArr);
		
		return busObject;
		}	
	
	public JSONObject getBuslineList(String key){
		ListDAO buslineList = new ListDAO();

		List r = buslineList.getlineList(key);
		Iterator p = r.iterator();
		BusLineListVO bus = null;

		JSONObject busObject = new JSONObject();
		JSONArray busArr = new JSONArray();

		while (p.hasNext()) {
			bus = (BusLineListVO) p.next();
			JSONObject busInfo = new JSONObject();

			busInfo.put("route_id", bus.getRoute_id());
			busInfo.put("route_name", bus.getRoute_name());
			busInfo.put("bstop_name", bus.getBstop_name());
			busInfo.put("bstop_id", bus.getBstop_id());
			busInfo.put("sbstop_id", bus.getSbstop_id());
			busInfo.put("run_dir", bus.getRun_dir());
			busInfo.put("num", bus.getNum());
			busArr.add(busInfo);
		}
		busObject.put("lineList", busArr);
		
		return busObject;
		}	
		
	public JSONObject getDrawInfo(String key){
		ListDAO drawlist = new ListDAO();

		List r = drawlist.getDrawInfo(key);
		Iterator p = r.iterator();
		DrawInfoVO drawinfo = null;

		JSONObject drawObject = new JSONObject();
		JSONArray drawArr = new JSONArray();

		while (p.hasNext()) {
			drawinfo = (DrawInfoVO) p.next();
			JSONObject draw = new JSONObject();

			draw.put("route_id", drawinfo.getRoute_id());
			draw.put("linkobj_seq", drawinfo.getLinkobj_seq());
			draw.put("x_pos", drawinfo.getX_pos());
			draw.put("y_pos", drawinfo.getY_pos());
			draw.put("routelink_seq", drawinfo.getRoutelink_seq());
			draw.put("dir_type", drawinfo.getDir_type());
			drawArr.add(draw);
		}
		drawObject.put("drawinfo", drawArr);
		
		return drawObject;
		}	
		
	}

