package android_db;

public class ArrivalBusVO {
	
	private String routeName;
	private String routeId;
	private String remainTime;
	private String remainBstop;
	
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}
	public String getRemainBstop() {
		return remainBstop;
	}
	public void setRemainBstop(String remainBstop) {
		this.remainBstop = remainBstop;
	}

}
