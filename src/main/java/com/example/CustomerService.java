package com.example;

import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.Scanner;
public class CustomerService {
    public static Scanner obj=new Scanner(System.in);
    public static Db db=new Db();
    public void addCustomer() throws ClassNotFoundException, SQLException {
        System.out.println("Enter Customer Name : ");
        String a=obj.next();
        System.out.println("Enter Customer PhoneNumber : ");
        String b=obj.next();
        System.out.println("Enter Customer Address : ");
        String c=obj.next();
        db.addCustomer(a, b, c);
    }
    public void viewcustomer(){
        System.out.println("  Customer ID  |  Customer Name\t|Phone Number\t|Address");
        for(Customer customers:db.getallcustomer()){
            System.out.println("\t"+customers.getId()+"      |    "+customers.getName()+"      |    "+customers.getPhone()+"    |    "+customers.getAddress());
        }
    }
    public void updatecustomer(){
        System.out.print("Enter a Phone Number : ");
        String custphone=obj.next();
        Customer customer=db.particularcustomer(custphone);
        if(customer.getName()!=null){
            String cname=customer.getName(),add=customer.getAddress(),cphone=customer.getPhone();
            System.out.println("1. Customer Name Edit");
            System.out.println("2. Customer Address Edit");
            System.out.println("3. Customer Phone Number Edit");
            int check=obj.nextInt();
            switch (check) {
                case 1:
                System.out.print("Enter a Customer Name : ");
                cname=obj.next();
                break;
                case 2:
                System.out.print("Enter a Address : ");
                add=obj.next();
                break;
                case 3:
                System.out.print("Enter a Phone Number : ");
                cphone=obj.next();
                break;
                default:
                System.out.println("Wrong choice");
                break;
            }
            db.updatecustomer(customer.getId(),cname,add,cphone);  
        }
        else{
            System.out.println("Customer Not found!!!");
        }
    }
    public void deletecustomer(){
        System.out.print(" Enter a Customer Phone Number : ");
        String phone=obj.next();
        Customer customer=db.particularcustomer(phone);
        if(customer.getName()==null){
            System.out.println("Customer Not found!!!");
        }
        else{
            db.deletecustomer(phone);
            System.out.println("Deleted Successfully");
        }
    }
    public void getparicularinvoice(int id){
        System.out.println("billsid \t"+"cusid \t"+"customername \t"+"Date \t"+"Totalamount \t");
        for(Bill bills : db.getparicularinvoice(id)){
            System.out.println(bills.getInvoiceId()+" "+bills.getCustId()+" "+bills.getCname()+" "+bills.getDate()+" "+bills.getTotal());
        }
        System.out.println("Enter a particular bill-id to display the product : ");
        int billid=obj.nextInt();
        System.out.println("Billid \t"+" productname \t"+"pid \t"+"productprice \t"+"amount \t");
        for(BillGenerator bg : db.particularbillid(billid))
        System.out.println(bg.getInvoiceId()+" \t"+bg.getName()+" \t"+bg.getpid()+" \t"+bg.getproductprice()+" \t"+bg.getamount());

    }
}
