package com.example.userss.gach;

public class User {

    String ID;
    String Pw;
    String Name;

    public User(String ID, String pw, String name) {
        this.ID = ID;
        Pw = pw;
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
