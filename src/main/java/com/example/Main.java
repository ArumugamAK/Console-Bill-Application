package com.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (Scanner sc = new Scanner(System.in)) {
            CustomerService cs=new CustomerService();
            ProductService ps=new ProductService();
            Billservice bills = new Billservice();
            Billgeneratorservice bs=new Billgeneratorservice();
            while(true){
                System.out.println("1. Add Customer");
                System.out.println("2. Add Product");
                System.out.println("3. View Customer");
                System.out.println("4. View Product");
                System.out.println("5. Bill Genarator");
                System.out.println("6. Update customer");
                System.out.println("7. Update product");
                System.out.println("8. delete product");
                System.out.println("9. delete customer");
                System.out.println("10. view bills ");
                System.out.println("11. view minimum product");
                System.out.println("12. Report");
                System.out.println("13. Exit");
                int a=sc.nextInt();
                switch(a){
                    case 1:
                        cs.addCustomer();
                        break;
                    case 2:
                        ps.addProduct();
                        break;
                    case 3:
                        cs.viewcustomer(); 
                        break; 
                    case 4:
                        ps.viewproduct();
                        break;  
                    case 5:
                    System.out.println("Already have a account (Yes/No)");
                    String check=sc.next().toLowerCase();
                    switch (check) {
                        case "no":
                            cs.addCustomer();
                            break;
                        case "yes":
                        System.out.print("Enter a Phone Number : ");
                        String phone=sc.next();
                         int flag=bs.BillGenerator(phone);
                         if(flag==1){
                            System.out.println("Customer Not found Create new Customer");
                            break;
                         }
                         break;
                        default:
                            break;
                    }
                        break;
                    case 6:
                        cs.updatecustomer();
                        break;
                    case 7:
                        ps.updateproduct();
                        break;
                    case 8:
                        ps.deleteproduct();
                        break;
                    case 9:
                        cs.deletecustomer();
                        break;
                    case 10:
                        bills.getallbills();
                        break;
                    case 11:
                        ps.lowproduct();
                        break;
                    case 12:
                        Report report=new Report();
                        report.init();
                        break;
                    default:
                        break;
                }
                if(a==13)break;
            }
        }
    }
}