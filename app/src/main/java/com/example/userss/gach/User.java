package com.example.userss.gach;

public class User {

    String id;
    String pw;
    String name;

    public User(String id, String pw, String name) {
        this.id = id;
        this.pw = pw;
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}
