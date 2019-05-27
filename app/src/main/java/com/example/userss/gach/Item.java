package com.example.userss.gach;

public class Item {

    private String list_name;
    private int list_favorite;
    private int list_touch;
    private int list_shake;
    private int list_tryon;
    private String list_photo;



    public Item(String list_name, int list_favorite, int list_touch, int list_shake, int list_tryon, String list_photo) {
        this.list_name = list_name;
        this.list_favorite = list_favorite;
        this.list_touch = list_touch;
        this.list_shake = list_shake;
        this.list_tryon = list_tryon;
        this.list_photo = list_photo;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public int getList_favorite() {
        return list_favorite;
    }

    public void setList_favorite(int list_favorite) {
        this.list_favorite = list_favorite;
    }

    public int getList_touch() {
        return list_touch;
    }

    public void setList_touch(int list_touch) {
        this.list_touch = list_touch;
    }

    public int getList_shake() {
        return list_shake;
    }

    public void setList_shake(int list_shake) {
        this.list_shake = list_shake;
    }

    public int getList_tryon() {
        return list_tryon;
    }

    public void setList_tryon(int list_tryon) {
        this.list_tryon = list_tryon;
    }

    public String getList_photo() {
        return list_photo;
    }

    public void setList_photo(String list_photo) {
        this.list_photo = list_photo;
    }
}
