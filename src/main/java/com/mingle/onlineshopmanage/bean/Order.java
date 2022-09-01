package com.mingle.onlineshopmanage.bean;

public class Order {

    private String id;
    private String receive;
    private String receivephone;
    private float money;
    private String orderstatus;
    private String paystatus;
    private String payway;
    private String send;

    public Order() {
    }

    public Order(String id, String receive, String receivephone, float money, String orderstatus, String paystatus, String payway, String send) {
        this.id = id;
        this.receive = receive;
        this.receivephone = receivephone;
        this.money = money;
        this.orderstatus = orderstatus;
        this.paystatus = paystatus;
        this.payway = payway;
        this.send = send;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", receive='" + receive + '\'' +
                ", receivephone='" + receivephone + '\'' +
                ", money=" + money +
                ", orderstatus='" + orderstatus + '\'' +
                ", paystatus='" + paystatus + '\'' +
                ", payway='" + payway + '\'' +
                ", send='" + send + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getReceivephone() {
        return receivephone;
    }

    public void setReceivephone(String receivephone) {
        this.receivephone = receivephone;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }
}
