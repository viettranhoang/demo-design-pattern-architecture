package com.vit.demodesignpatternarchitecture.data.remote.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vit.demodesignpatternarchitecture.data.model.User;

public class UserResponse {

    @Expose
    @SerializedName("data")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
