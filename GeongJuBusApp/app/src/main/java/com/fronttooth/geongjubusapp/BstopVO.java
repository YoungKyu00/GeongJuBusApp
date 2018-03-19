package com.fronttooth.geongjubusapp;

import android.os.Parcel;
import android.os.Parcelable;

public class BstopVO implements Parcelable{

	private String sbstop_id;
	private String bstop_name;
	private String bstop_id;
	private String bstop_routes;
	private String gpsx;
	private String gpsy;

	public BstopVO(){

	}
	public BstopVO(String sbstop_id, String bstop_name, String bstop_id, String bstop_routes, String gpsx, String gpsy) {
		this.sbstop_id=sbstop_id;
		this.bstop_name=bstop_name;
		this.bstop_id=bstop_id;
		this.bstop_routes=bstop_routes;
		this.gpsx=gpsx;
		this.gpsy=gpsy;
	}

	protected BstopVO(Parcel in) {
		sbstop_id = in.readString();
		bstop_name = in.readString();
		bstop_id = in.readString();
		bstop_routes = in.readString();
		gpsx = in.readString();
		gpsy = in.readString();
	}

	public static final Creator<BstopVO> CREATOR = new Creator<BstopVO>() {
		@Override
		public BstopVO createFromParcel(Parcel in) {
			return new BstopVO(in);
		}

		@Override
		public BstopVO[] newArray(int size) {
			return new BstopVO[size];
		}
	};

	public String getSbstop_id() {
		return sbstop_id;
	}

	public void setSbstop_id(String sbstop_id) {
		this.sbstop_id = sbstop_id;
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

	public String getBstop_routes() {
		return bstop_routes;
	}

	public void setBstop_routes(String bstop_routes) {
		this.bstop_routes = bstop_routes;
	}

	public String getGpsx() {
		return gpsx;
	}

	public void setGpsx(String gpsx) {
		this.gpsx = gpsx;
	}

	public String getGpsy() {
		return gpsy;
	}

	public void setGpsy(String gpsy) {
		this.gpsy = gpsy;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(sbstop_id);
		dest.writeString(bstop_name);
		dest.writeString(bstop_id);
		dest.writeString(bstop_routes);
		dest.writeString(gpsx);
		dest.writeString(gpsy);
	}
}
