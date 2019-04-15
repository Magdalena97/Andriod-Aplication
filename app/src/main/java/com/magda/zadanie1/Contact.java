package com.magda.zadanie1;

public class Contact {
    private String name = "";
    private int sound;
    private int images;


    public Contact(String name, int sound, int images) {
        this.name = name;
        this.sound = sound;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
