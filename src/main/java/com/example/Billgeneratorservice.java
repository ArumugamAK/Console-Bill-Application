package com.example;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Billgeneratorservice {
    public static Db db=new Db();
    public static ProductService productServices = new ProductService();
    public static Scanner obj=new Scanner(System.in);
    public int BillGenerator(String phone) throws ClassNotFoundException, SQLException{
            Customer cus=db.particularcustomer(phone);
            if(cus.getName()==null){
                return 1;
            }
            else{
                System.out.println("customerid \t customername \t phone \t address");
                System.out.println(cus.getId()+" \t\t"+cus.getName()+" \t"+cus.getPhone()+" \t"+cus.getAddress());
                System.out.println();
            }
        productServices.viewproduct();
        List<BillGenerator> bill = new ArrayList<>();
        int lastid=db.getlastid()+1;
        while(true){
            System.out.println("1. Add-product");
            System.out.println("2. remove-product");
            System.out.println("3. Preview");
            System.out.println("4. Save");
            System.out.println("5. Exit");
            int check=obj.nextInt();
            if(check==1){
                boolean flag=true;
                int quantity=0;
                System.out.println("Enter the Product-id");
                int productid=obj.nextInt();
                for(int i=0;i<bill.size();i++){
                    BillGenerator b=bill.get(i);
                    if(b.getpid()==productid){
                        System.out.println(b.getName()+" is already added in the bill with quantity "+b.getQuantity());
                        System.out.println("Enter the Updated Quantity");
                        quantity=obj.nextInt();
                        bill.remove(i);
                        flag=false;
                    }
                }
                if(flag){
                    System.out.println("Enter the Quantity");
                    quantity=obj.nextInt();
                }
                Product product=db.particularProduct(productid);
                if(product.getPname()==null){
                    System.out.println("\t......Product Not Found..........");
                }
                else{
                    System.out.println(product.getPname()+" "+product.getPid());
                    if(quantity<=product.getQuantity()){
                        float amount=quantity*product.getPrice();
                        System.out.println("productid \t productname \t price \t quantity \t amount");
                        int sum1 =(int) (quantity*product.getPrice());
                        double tax=sum1*(product.gettax()/100.0);
                        float totalsum=(float) (sum1+tax);
                        System.out.println(product.getPid()+" \t\t"+product.getPname()+" \t\t"+product.getPrice()+" \t\t"+quantity+" \t\t"+totalsum);
                        bill.add(new BillGenerator(lastid,product.getPname(),product.getPid(),quantity,product.getPrice(),totalsum));   
                        
                    }
                    else{
                        System.out.println("\t..........Stock Not avaliable...........");
                    }
                }
            }
            else if(check==2){
                System.out.println("Enter the remove product-id");
                int productid=obj.nextInt();
                int checkid=1;
                for (int i = 0; i < bill.size(); i++) {
                    BillGenerator billItem = bill.get(i);
                    if (productid==billItem.getpid()) {
                        bill.remove(i);
                        System.out.println("Product removed: " + billItem.getName());
                        checkid=0;
                        break;
                    }
                }
                if(checkid==1){
                    System.out.println("Product-id Not Found");
                }

            }
            else if (check==3){
                float totalsum=0;
                System.out.println("Billsid \t productname \t productid \t quantity \t amount");
                for(BillGenerator billitems:bill){
                    System.out.println(billitems.getInvoiceId()+" \t\t"+billitems.getName()+" \t\t"+billitems.getpid()+" \t\t"+billitems.getQuantity()+" \t\t"+billitems.getamount());
                    totalsum+=billitems.getamount();
                }
                if(totalsum>=500){
                    double dis=totalsum*(6.0/100.0);
                    totalsum=(float) (totalsum-dis);
                    
                }
                System.out.println("Total Amount : "+totalsum);
            }
            else if(check==4){
                float totalsum=0;
                System.out.println("Billsid \t productname \t productid \t quantity \t amount");
                for(BillGenerator billitems:bill){
                    System.out.println(billitems.getInvoiceId()+" \t\t"+billitems.getName()+" \t\t"+billitems.getpid()+" \t\t"+billitems.getQuantity()+" \t\t"+billitems.getamount());
                    totalsum+=billitems.getamount();
                }
                if(totalsum>=500){
                    double dis=totalsum*(6.0/100.0);
                    totalsum=(float) (totalsum-dis);
                    
                }
                // double cgst=totalsum*(6.0/100.0);
                // double sgst=totalsum*(6.0/100.0);
                // totalsum=(float) (totalsum+cgst+sgst);
                // System.out.println("CGST 6% : "+cgst);
                // System.out.println("SGST 6% : "+sgst);
                System.out.println("Total Amount : "+totalsum);
                Date currentDate = Date.valueOf(LocalDate.now());
                db.billssave(cus.getId(),cus.getName(),currentDate,totalsum);
                for(BillGenerator billitems:bill){
                    db.productreduce(billitems.getQuantity(),billitems.getpid());
                    db.billGenerator(billitems.getInvoiceId(),billitems.getName(),billitems.getpid(),billitems.getproductprice(),billitems.getQuantity(),billitems.getamount());
                }
                System.out.println("Thank you for visiting! We look forward to welcoming you back soon. Come and enjoy more next time!");
            }
            else if(check==5){
                break;
            }
            else{
                System.out.println("Invalid input");
            }
        }
        
    return 0;
    }
}
