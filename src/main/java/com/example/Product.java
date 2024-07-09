package com.example;

public class Product {
    private int pid,quantity;
    private String pname;
    private float price;
    private int tax;

    public Product(int pid, String pname, int quantity, float price,int tax) {
        this.pid = pid;
        this.quantity = quantity;
        this.pname = pname;
        this.price =  price;
        this.tax=tax;
        
    }

    public int getPid() {
        return pid;
    }
    public int gettax(){
        return tax;
    }
    public void settax(int tax){
        this.tax=tax;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
