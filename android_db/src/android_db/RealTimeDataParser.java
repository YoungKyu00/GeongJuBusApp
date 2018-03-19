package android_db;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class RealTimeDataParser {

		
	private Document doc;
	private String url;
	private JSONParser json;
	private JSONObject jobj;
	
		public JSONObject takeRealTime(String bstopId) throws IOException, ParseException{
			
			url = "http://its.gyeongju.go.kr/bis/main/bstopDetailSearchAjax.do?bStopid="+bstopId;
			doc = Jsoup.connect(url).ignoreContentType(true).get();
			
			if(null!= doc){
			json = new JSONParser();
			jobj = (JSONObject)json.parse(doc.text());
			}
			return jobj;
			
		}
		
	}

