package com.fronttooth.geongjubusapp;

public class BusLineListVO {

	String route_id;
	String route_name;
	String bstop_name;
	String bstop_id;
	String sbstop_id;
	String rud_dir;
	String num;

	public BusLineListVO() {
	}

	public BusLineListVO(String route_id, String route_name, String bstop_name,String sbstop_id, String bstop_id, String rud_dir, String num) {
		this.route_id = route_id;
		this.route_name = route_name;
		this.bstop_name = bstop_name;
		this.bstop_id = bstop_id;
		this.rud_dir = rud_dir;
		this.sbstop_id = sbstop_id;
		this.num = num;
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
	public String getBstop_name() {
		return bstop_name;
	}
	public void setBstop_name(String bstop_name) {
		this.bstop_name = bstop_name;
	}
	public String getBstop_id() {
		return bstop_id;
	}
	public void setBstop_id(String bstop_id) {
		this.bstop_id = bstop_id;
	}
	public String getRud_dir() {
		return rud_dir;
	}
	public void setRud_dir(String rud_dir) {
		this.rud_dir = rud_dir;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSbstop_id() {return sbstop_id;}
	public void setSbstop_id(String sbstop_id) {this.sbstop_id = sbstop_id;}
	
}
