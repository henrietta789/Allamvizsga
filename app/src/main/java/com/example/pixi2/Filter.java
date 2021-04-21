package com.example.pixi2;

public class Filter {
    private String filter_name;
    private int Photo;

    public Filter(String filter_name, int photo) {
        this.filter_name = filter_name;
        Photo = photo;
    }

    public Filter() {
    }

    public String getFilter_name() {
        return filter_name;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setFilter_name(String filter_name) {
        this.filter_name = filter_name;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

}
