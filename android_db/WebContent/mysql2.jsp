<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*, android_db.*,org.json.simple.*"%>
<%
	request.setCharacterEncoding("UTF-8");

	String key = request.getParameter("key");
	String keytype = request.getParameter("keytype");
	CreateJson json = new CreateJson();
	RealTimeDataParser realtimedata = new RealTimeDataParser();
	
	if (keytype != null && keytype.equals("bstop")) {
		JSONObject bstop = json.getBstop(key);
		System.out.println(bstop.toJSONString());
		out.print(bstop.toJSONString());
	} else if (keytype != null && keytype.equals("bus")) {
		System.out.println(key + keytype);
		JSONObject bus = json.getBusline(key);
		System.out.println(bus.toJSONString());
		out.print(bus.toJSONString());
	} else if(keytype != null && keytype.equals("list")){
		System.out.println(key+keytype);
		JSONObject line = json.getBuslineList(key);
		System.out.println(line.toJSONString());
		out.print(line.toJSONString());
	} else if(keytype != null && keytype.equals("realtime")){
		System.out.println(key + keytype);
		JSONObject realtime = realtimedata.takeRealTime(key);
		System.out.println(realtime.toJSONString());
		out.print(realtime.toJSONString());
	}else if(keytype != null && keytype.equals("drawinfo")){
		System.out.println(key + keytype);
		JSONObject drawinfo = json.getDrawInfo(key);
		System.out.println(drawinfo.toJSONString());
		out.print(drawinfo.toJSONString());
	}
	/* BstopDAO bstopList = new BstopDAO();
	
	List r = bstopList.getList2(key);
	
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
	out.print(bstopObject.toJSONString()); */
%>
