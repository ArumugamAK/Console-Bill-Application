package com.example;

import java.util.Scanner;

public class Billservice {
    public static Scanner obj = new Scanner(System.in);
    public static Db db = new Db();
    public void getallbills(){
        System.out.println("Bill No  |  Customer Id  |  Date  |  Name  |  Total Amount");
        for(Bill bills : db.getallbBills()){
            System.out.println("    "+bills.getInvoiceId()+"  |      "+bills.getCustId()+"    |  "+bills.getDate()+"  |  "+bills.getCname()+"  |  "+bills.getTotal());
        }
    }
}
