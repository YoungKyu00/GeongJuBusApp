package com.fronttooth.geongjubusapp;

public class DrawInfoVO {
    private String route_id;
    private String linkobj_seq;
    private String x_pos;
    private String y_pos;
    private String routelink_seq;
    private String dir_type;

    public DrawInfoVO(){
    }

    public DrawInfoVO(String route_id, String linkobj_seq, String x_pos, String y_pos, String routelink_seq, String dir_type) {
        this.route_id = route_id;
        this.linkobj_seq = linkobj_seq;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.routelink_seq = routelink_seq;
        this.dir_type = dir_type;
    }

    public String getRoute_id() {
        return route_id;
    }
    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }
    public String getLinkobj_seq() {
        return linkobj_seq;
    }
    public void setLinkobj_seq(String linkobj_seq) {
        this.linkobj_seq = linkobj_seq;
    }
    public String getX_pos() {
        return x_pos;
    }
    public void setX_pos(String x_pos) {
        this.x_pos = x_pos;
    }
    public String getY_pos() {
        return y_pos;
    }
    public void setY_pos(String y_pos) {
        this.y_pos = y_pos;
    }
    public String getRoutelink_seq() {
        return routelink_seq;
    }
    public void setRoutelink_seq(String routelink_seq) {
        this.routelink_seq = routelink_seq;
    }
    public String getDir_type() {
        return dir_type;
    }
    public void setDir_type(String dir_type) {
        this.dir_type = dir_type;
    }
}
