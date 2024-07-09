package com.example;

import java.sql.Date;

public class Bill {
    private int custId,invoiceId;
    private Date date;
    private String cname;
    private float total;
    public int getCustId() {
        return custId;
    }
    public void setCustId(int custId) {
        this.custId = custId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public int getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public Bill(int invoiceId,int custId, Date date, float total, String cname) {
        this.custId = custId;
        this.invoiceId=invoiceId;
        this.cname=cname;
        this.date = date;
        this.total = total;
    }
}
