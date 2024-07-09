package com.example;

public class BillGenerator {
    private int invoiceId,quantity,id,pid;
    private String name;
    private float productprice,amount;

    public BillGenerator(int invoiceId, String name, int pid,int quantity, float productprice, float amount) {
        this.invoiceId = invoiceId;
        this.name = name;
        this.pid=pid;
        this.quantity = quantity;
        this.productprice = productprice;
        this.amount = amount;
    }
    public int getpid(){
        return pid;
    }
    public void setpid(int pid){
        this.pid=pid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoiceId=" + invoiceId +
                ", quantity=" + quantity +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", Productprice=" + productprice +
                ", amount" + amount +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getproductprice() {
        return productprice;
    }

    public void setproductprice(float productprice ) {
        this.productprice = productprice;
    }

    public float getamount() {
        return amount;
    }

    public void setamount(float amount) {
        this.amount = amount;
    }
}
