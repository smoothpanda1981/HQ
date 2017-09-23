package com.wang.yan.mvc.model;

/**
 * Created by ywang on 19/12/15.
 */
public class UpdateLogin {

    private String current_username;
    private String current_password;
    private String new_username;
    private String new_password;

    public String getCurrent_username() {
        return current_username;
    }

    public void setCurrent_username(String current_username) {
        this.current_username = current_username;
    }

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

    public String getNew_username() {
        return new_username;
    }

    public void setNew_username(String new_username) {
        this.new_username = new_username;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
