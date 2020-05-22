package com.example.orderquick;

public class ExampleItem {
    private int imageSrc;
    private String name;
    private String telephone;

    public ExampleItem(int imageSrc, String name, String telephone) {
        this.imageSrc = imageSrc;
        this.name = name;
        this.telephone = telephone;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
