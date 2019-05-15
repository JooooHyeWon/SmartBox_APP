package com.example.userss.gach;

public class Variable {
    private static String ServerURl = "http://52.78.132.239:3000";

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Variable.user = user;
    }

    public static String getServerURl() {
        return ServerURl;
    }

    public static void setServerURl(String serverURl) {
        ServerURl = serverURl;
    }
}
