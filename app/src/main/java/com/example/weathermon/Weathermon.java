package com.example.weathermon;

public class Weathermon {
    private String name;
    private String type;
    private int imageResource;
    private boolean isCaught;

    public Weathermon(String name, String type, int imageResource, boolean isCaught) {
        this.name = name;
        this.type = type;
        this.imageResource = imageResource;
        this.isCaught = isCaught;
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

    public boolean isCaught() {
        return isCaught;
    }
}


