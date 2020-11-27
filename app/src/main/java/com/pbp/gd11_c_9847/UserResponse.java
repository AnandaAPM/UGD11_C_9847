package com.pbp.gd11_c_9847;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName ( "dataBuku" )
    @Expose
    private List<UserDAO> users = null;

    @SerializedName ( "message" )
    @Expose
    private String message;

    public List<UserDAO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDAO> users) {
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
