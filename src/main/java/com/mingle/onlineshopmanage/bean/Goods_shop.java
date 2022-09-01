package com.mingle.onlineshopmanage.bean;

public class Goods_shop {
    private int Gid;
    private int Sid;

    public Goods_shop() {
    }

    public Goods_shop(int gid, int sid) {
        Gid = gid;
        Sid = sid;
    }

    public int getSid() {
        return Sid;
    }

    public void setSid(int sid) {
        Sid = sid;
    }

    public int getGid() {
        return Gid;
    }

    public void setGid(int gid) {
        Gid = gid;
    }

    @Override
    public String toString() {
        return "Goods_shop{" +
                "Gid=" + Gid +
                ", Sid=" + Sid +
                '}';
    }
}
