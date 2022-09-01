package com.mingle.onlineshopmanage.bean;

public class Shop {
    private int id;
    private String name;
    private String describe;
    private float score;

    public Shop(int id, String name, String describe, float score) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.score = score;
    }

    public Shop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", score=" + score +
                '}';
    }
}
