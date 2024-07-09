package com.example;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductService {
    public static Scanner obj=new Scanner(System.in);
    public static Db db=new Db();
    public void addProduct() throws ClassNotFoundException, SQLException {
        System.out.println("Enter Product Name : ");
        String a=obj.next();
        System.out.println("Enter Product Price: ");
        Float b=obj.nextFloat();
        System.out.println("Enter Product Quantity: ");
        int c=obj.nextInt();
        Db db=new Db();
        db.addProduct(a,b,c);
    }
    public void viewproduct(){
        System.out.println("  Product ID  |  Product Name\t|  Quantity\t|  Price");
        for(Product products:db.getallproduct()){
            System.out.println("\t"+products.getPid()+"    |  "+products.getPname()+"  |  "+products.getQuantity()+"  |  "+products.getPrice());
        }
    }
    public void updateproduct(){
        System.out.println("Enter a Product-id");
        int productid=obj.nextInt();
        Product product=db.particularProduct(productid);
        if(product.getPname()!=null){
            String pname=product.getPname();
            float price=product.getPrice();
            int quantity=product.getQuantity();
            System.out.println("1. Product Name Edit ");
            System.out.println("2. Product Price Edit ");
            System.out.println("3. Product Quantity Edit ");
            int check=obj.nextInt();
            switch (check) {
                case 1:
                System.out.println("Enter a Product Nmae : ");
                pname=obj.next();
                break;
                case 2:
                System.out.println("Enter a Product Price : ");
                price=obj.nextFloat();
                break;
                case 3:
                System.out.println("Enter a Product Quantity : ");
                quantity=obj.nextInt();
                break;
                default:
                break;
            }
            db.updateproduct(product.getPid(),pname,price,quantity);
            
        }
        else{
            System.out.println("Product Not found!!");
        }
    }
    public void deleteproduct(){
        System.out.println("Enter a Product-id");
        int id=obj.nextInt();
        Product product=db.particularProduct(id);
        if(product.getPname()==null){
            System.out.println("Product Not found!!");
        }
        else{
            db.deleteproduct(id);
            System.out.println("Deleted Successfully");
        }
    }
    public void lowproduct(){
            System.out.println("  Product ID  |  Product Name\t|  Quantity\t|  Price");
            for(Product products:db.getlowproduct()){
                System.out.println("\t"+products.getPid()+"    |  "+products.getPname()+"  |  "+products.getQuantity()+"  |  "+products.getPrice());
            }
    }
}
