package com.fronttooth.geongjubusapp;

import android.support.annotation.NonNull;

public class ArrivalBusVO implements Comparable<ArrivalBusVO> {

	private String routeName;
	private String routeId;
	private String remainTime;
	private String remainBstop;

	public ArrivalBusVO() {

	}

	public ArrivalBusVO(String routeName) {
		this.routeName = routeName;
	}

	public ArrivalBusVO(String routeName, String routeId, String remainTime, String remainBstop) {
		this.routeName = routeName;
		this.routeId = routeId;
		this.remainTime = remainTime;
		this.remainBstop = remainBstop;
	}

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

	@Override
	public int compareTo( ArrivalBusVO o) {

		if (Integer.parseInt(this.routeName) < Integer.parseInt(o.routeName)) {
			return -1;
		} else if (Integer.parseInt(this.routeName) == Integer.parseInt(o.routeName)) {
			return 0;
		} else {
			return 1;
		}
	}
}
