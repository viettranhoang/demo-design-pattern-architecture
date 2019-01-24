package com.vit.demodesignpatternarchitecture.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Demo Builder Design Pattern
 */

public class User {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    @Expose
    @SerializedName("avatar")
    private String avatar;

    private User(final Builder builder) {
        this.id = id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.avatar = builder.avatar;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    static class Builder{

        private int id;

        private String firstName;

        private String lastName;

        private String avatar;

        public Builder setId(final int id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setAvatar(final  String avatar) {
            this.avatar = avatar;
            return this;
        }

        public User create() {
            User user = new User(this);
            if (user.getFirstName().isEmpty()) {
                throw new IllegalStateException("First name can not be empty");
            }
            return user;
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
