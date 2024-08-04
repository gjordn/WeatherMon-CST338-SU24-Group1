package com.example.weathermon;

public class Weathermon {
    private String name;
    private String type;
    private int imageResource;
    private boolean obtained;

    public Weathermon(String name, String type, int imageResource, boolean obtained) {
        this.name = name;
        this.type = type;
        this.imageResource = imageResource;
        this.obtained = obtained;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean isObtained() {
        return obtained;
    }
}
