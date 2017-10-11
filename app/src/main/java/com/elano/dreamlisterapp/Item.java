package com.elano.dreamlisterapp;

import java.io.Serializable;

/**
 * Created by Janeth on 10/8/2017.
 */

public class Item implements Serializable {

    private long id;
    private byte[] image;
    private String name, description;
    private double price;

    public Item() {}

    public Item(byte[] image, String name, String description, double price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item(long id, byte[] image, String name, String description, double price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
