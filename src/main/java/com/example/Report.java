package com.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Report {
    Scanner obj=new Scanner(System.in);
    public static CustomerService cs = new CustomerService();
    public static Db db=new Db();
    public void init() throws SQLException,ClassNotFoundException{
        while(true){
            System.out.println("1. Top Three Products ");
            System.out.println("2. Top Products sold in a specific range");
            System.out.println("3. Top Products sold in a specific month");
            System.out.println("4. Top Customer purchased in a specific month");
            System.out.println("5. Top three Customer ");
            System.out.println("6. Invoices purchased by a specific customer");
            System.out.println("7. Top three Customer phonenumber ");
            System.out.println("8. Exit");
            Db db=new Db();
            int a=obj.nextInt();
            switch (a) {
                case 1:
                System.out.println("Based on quantity or Amount : ");
                String name=obj.next();
                if(name.equals("amount"))db.gettopthreesold1();
                else if(name.equals("quantity"))db.gettopthreesold2();
                break;
                case 2:
                System.out.print("Enter a first month1 : ");
                int month1=obj.nextInt();
                System.out.print("Enter a Second month2 : ");
                int month2=obj.nextInt();
                db.gettoprangemonth(month1,month2);
                    break;
                case 3:
                    System.out.print("Enter a month :");
                    int pmonth=obj.nextInt();
                    db.topthreeproduct(pmonth);
                    break;
                case 4:
                    System.out.print("Enter a month :");
                   int cmonth=obj.nextInt();
                   db.toptwocustomer(cmonth);
                    break;
                case 5:
                    db.topthreecustomer();
                    break;
                case 6:
                System.out.print("Enter a customer-id");
                   int cid=obj.nextInt();
                   cs.getparicularinvoice(cid);
                    break;
                case 7:
                    db.topthreephonenumber();   
                default:
                    break;
            }
            if(a==8)break;
        }
    }
}
