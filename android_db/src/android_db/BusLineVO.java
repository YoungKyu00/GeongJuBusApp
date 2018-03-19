package android_db;

public class BusLineVO {

	
	String route_id;
	String route_name;
	String end_name;
	String max_head;
	String route_type;
	String route_type_name;
	String route_desc;
	String min_head;
	
	public BusLineVO(){
		
	}
	
	public BusLineVO(String route_id, String route_name, String end_name, String route_type, String route_type_name){
		
	}
	
	public BusLineVO(String route_id, String route_name, String end_name, String max_head, String route_type,
			String route_type_name, String route_desc, String min_head) {
		super();
		this.route_id = route_id;
		this.route_name = route_name;
		this.end_name = end_name;
		this.max_head = max_head;
		this.route_type = route_type;
		this.route_type_name = route_type_name;
		this.route_desc = route_desc;
		this.min_head = min_head;
	}
	
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public String getEnd_name() {
		return end_name;
	}
	public void setEnd_name(String end_name) {
		this.end_name = end_name;
	}
	public String getMax_head() {
		return max_head;
	}
	public void setMax_head(String max_head) {
		this.max_head = max_head;
	}
	public String getRoute_type() {
		return route_type;
	}
	public void setRoute_type(String route_type) {
		this.route_type = route_type;
	}
	public String getRoute_type_name() {
		return route_type_name;
	}
	public void setRoute_type_name(String route_type_name) {
		this.route_type_name = route_type_name;
	}
	public String getRoute_desc() {
		return route_desc;
	}
	public void setRoute_desc(String route_desc) {
		this.route_desc = route_desc;
	}
	public String getMin_head() {
		return min_head;
	}
	public void setMin_head(String min_head) {
		this.min_head = min_head;
	}
	
}
