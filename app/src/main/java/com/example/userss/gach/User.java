package com.example.userss.gach;

public class User {

    String ID;
    String Pw;
    String Name;

    public User(String id, String pw, String name) {
        this.ID = id;
        Pw = pw;
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        ID = id;
    }

    public String getPw() {
        return Pw;
    }

    public void setPw(String pw) {
        Pw = pw;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
