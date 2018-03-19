package com.fronttooth.geongjubusapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

/**
 * Created by Young on 2017-05-30.
 */

public class Json {
    ArrayList<BstopVO> bsstopArray = new ArrayList<BstopVO>();
    ArrayList<BusLineVO> busArray = new ArrayList<BusLineVO>();
    ArrayList<BusLineListVO> ListArray = new ArrayList<BusLineListVO>();
    ArrayList<ArrivalBusVO> RealTimeArray = new ArrayList<ArrivalBusVO>();
    ArrayList<DrawInfoVO> drawArray = new ArrayList<DrawInfoVO>();

    public ArrayList<BstopVO> getJsonBsstop(String result) throws ExecutionException, InterruptedException, JSONException {

        JSONObject jsonBsstop = new JSONObject(result);
        JSONArray jArr = jsonBsstop.getJSONArray("bstopList");
        for (int i = 0; i < jArr.length(); i++) {
            jsonBsstop = jArr.getJSONObject(i);
            if (jsonBsstop != null) {
                BstopVO bus = new BstopVO(jsonBsstop.getString("sbstop_id"), jsonBsstop.getString("Bstop_name"), jsonBsstop.getString("Bstop_id"), jsonBsstop.getString("Bstop_routes"), jsonBsstop.getString("gpsx"), jsonBsstop.getString("gpsy"));
                bsstopArray.add(bus);
            }
        }

        return bsstopArray;
    }

    public ArrayList<BusLineVO> getJsonBus(String result) throws ExecutionException, InterruptedException, JSONException {

        JSONObject jsonBus = new JSONObject(result);
        JSONArray jArr = jsonBus.getJSONArray("buslineList");
        for (int i = 0; i < jArr.length(); i++) {
            jsonBus = jArr.getJSONObject(i);
            if (jsonBus != null) {
                BusLineVO bus = new BusLineVO(jsonBus.getString("route_id"), jsonBus.getString("route_name"), jsonBus.getString("end_name"), jsonBus.getString("route_type"), jsonBus.getString("route_type_name"));
                busArray.add(bus);
            }
        }

        return busArray;
    }

    public ArrayList<BusLineListVO> getJsonBusLine(String result) throws ExecutionException, InterruptedException, JSONException {

        JSONObject jsonLine = new JSONObject(result);
        JSONArray jArr = jsonLine.getJSONArray("lineList");
        for (int i = 0; i < jArr.length(); i++) {
            jsonLine = jArr.getJSONObject(i);
            if (jsonLine != null) {
                BusLineListVO line = new BusLineListVO(jsonLine.getString("route_id"), jsonLine.getString("route_name"), jsonLine.getString("bstop_name"), jsonLine.getString("sbstop_id"),jsonLine.getString("bstop_id"), jsonLine.getString("run_dir"), jsonLine.getString("num"));
                ListArray.add(line);
            }
        }

        return ListArray;
    }

    public ArrayList<ArrivalBusVO> getJsonRealtime(String result) throws ExecutionException, InterruptedException, JSONException {

        JSONObject jobj = new JSONObject(result);
        ArrivalBusVO arrival;
        JSONArray jArr = jobj.getJSONArray("arrivalInfo");

        String bus = (String) jobj.get("lineInfo");

        for (int i = 0; i < jArr.length(); i++) {
            jobj = jArr.getJSONObject(i);
            if (jobj != null) {
                arrival = new ArrivalBusVO(jobj.getString("ROUTE_NAME"), jobj.getString("ROUTE_ID"), jobj.getString("REMAIN_MI_VIEW"), jobj.getString("REMAIN_BSTOP_CNT_VIEW"));
                RealTimeArray.add(arrival);
            }
        }
        Collections.sort(RealTimeArray);
            return RealTimeArray;
    }

    public ArrayList<DrawInfoVO> getJsonDrawInfo(String result) throws ExecutionException, InterruptedException, JSONException {

        JSONObject jsonLine = new JSONObject(result);
        JSONArray jArr = jsonLine.getJSONArray("drawinfo");
        for (int i = 0; i < jArr.length(); i++) {
            jsonLine = jArr.getJSONObject(i);
            if (jsonLine != null) {
                DrawInfoVO line = new DrawInfoVO(jsonLine.getString("route_id"), jsonLine.getString("linkobj_seq"), jsonLine.getString("x_pos"), jsonLine.getString("y_pos"), jsonLine.getString("routelink_seq"), jsonLine.getString("dir_type"));
                drawArray.add(line);
            }
        }

        return drawArray;
    }


}