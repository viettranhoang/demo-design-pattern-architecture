package com.vit.demodesignpatternarchitecture.data.remote.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vit.demodesignpatternarchitecture.data.model.User;

import java.util.List;

public class UsersResponse {

    @Expose
    @SerializedName("data")
    private List<User> users;
    @Expose
    @SerializedName("total_pages")
    private int totalPages;
    @Expose
    @SerializedName("total")
    private int total;
    @Expose
    @SerializedName("per_page")
    private int perPage;
    @Expose
    @SerializedName("page")
    private int page;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
