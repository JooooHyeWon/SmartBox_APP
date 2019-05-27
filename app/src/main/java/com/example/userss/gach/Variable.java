package com.example.userss.gach;

import java.util.ArrayList;

public class Variable {
    private static String ServerURl = "http://52.78.132.239:3000";

    private static User user = new User("1","1","1");


    private static ArrayList<Item> Item;

    private static ArrayList<Item> Cart;   // 장바구니

    public static ArrayList<com.example.userss.gach.Item> getCart() {
        return Cart;
    }

    public static void setCart(ArrayList<com.example.userss.gach.Item> cart) {
        Cart = cart;
    }

    public static ArrayList<com.example.userss.gach.Item> getItem() {
        return Item;
    }

    public static void setItem(ArrayList<com.example.userss.gach.Item> item) {
        Item = item;
    }

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
