package com.example;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Db {
    public void addCustomer(String name,String phone,String address) throws SQLException, ClassNotFoundException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM customer WHERE Phonenumber= ?");
            ps.setString(1,phone);
            ResultSet resultSet=null; 
            resultSet=ps.executeQuery();
            if(resultSet.isBeforeFirst()){
                System.out.println("Customer Already Exits");
            }
            else{
                PreparedStatement ps1= conn.prepareStatement("INSERT INTO customer(Customername,Phonenumber,Address) VALUES(?, ?, ?)");
                ps1.setString(1, name);
                ps1.setString(2, phone);
                ps1.setString(3, address);
                ps1.executeUpdate();
                System.out.println("Customer Saved Successfully");
            }  
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addProduct(String productname,float price,int quantity ) throws SQLException, ClassNotFoundException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM product WHERE Productname= ? ");
            ps.setString(1, productname);
             ResultSet resultSet=null; 
             resultSet=ps.executeQuery();
             if(resultSet.isBeforeFirst()){
                System.out.println("Product Name Already exists");
            }
            else{
                 PreparedStatement ps1= conn.prepareStatement("INSERT INTO product(Productname,price,quantity) VALUES(?, ?, ?)");
                 ps1.setString(1, productname);
                 ps1.setFloat(2, price);
                 ps1.setInt(3, quantity);
                 ps1.executeUpdate();
                 System.out.println("Product Added Successfully");
             }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Customer> getallcustomer(){
        ArrayList<Customer> l1 = new ArrayList<>();
        ResultSet r=null;
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            ps=con.prepareStatement("SELECT * FROM customer");
            r=ps.executeQuery();
            while (r.next()) {
                l1.add(new Customer(r.getInt("idCustomer"),r.getString("Customername"),r.getString("Phonenumber"), r.getString("Address")));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return l1;
    }
    public ArrayList<Product> getallproduct(){
        ArrayList<Product> l1 = new ArrayList<>();
        ResultSet r=null;
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            ps=con.prepareStatement("SELECT * FROM product");
            r=ps.executeQuery();
            while (r.next()) {
                l1.add(new Product(r.getInt("productid"),r.getString("Productname"),r.getInt("quantity"), r.getFloat("price"),r.getInt("tax")));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return l1;
    }
    public Customer particularcustomer(String phone) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer WHERE Phonenumber= ?");
            ps.setString(1, phone);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                // System.out.println("Customer Already Exits");
                // System.out.println(resultSet.getInt("idCustomer") + " " + resultSet.getString("Customername") + " " + resultSet.getString("Phonenumber") + " " + resultSet.getString("Address"));
                return new Customer(resultSet.getInt("idCustomer"), resultSet.getString("Customername"), resultSet.getString("Phonenumber"),resultSet.getString("Address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Customer(0, null, null, null);
    }
    public Product particularProduct(int productid){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE productid= ?");
            ps.setInt(1, productid);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Product(resultSet.getInt("productid"), resultSet.getString("Productname"), resultSet.getInt("quantity"),resultSet.getFloat("price"),resultSet.getInt("tax"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Product(0,null,0,0,0);
    }
    public void productreduce(int quantity,int productid){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("UPDATE product set quantity= quantity- ? WHERE productid= ?");
            ps.setInt(1, quantity);
            ps.setInt(2, productid);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void billGenerator(int billid,String productname,int pid,float productprice,int quantity,float amount){
        try {
            // System.out.println(amount);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("INSERT into billgenerator(Billgeneratorid,productname,quantity,productprice,amount,pid) values(?,?,?,?,?,?)");
            ps.setInt(1,  billid);
            ps.setString(2, productname);
            ps.setInt(3, quantity);
            ps.setFloat(4,productprice);
            ps.setFloat(5,amount);
            ps.setInt(6,pid);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public int getlastid(){
        int lastId=-1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("SELECT MAX(Billsid) FROM bills");
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                lastId = resultSet.getInt(1); 
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lastId;
    }
    public void billssave(int custId,String cusname,Date date,float totalamount){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("INSERT into bills(customerid,date,amount,customername) values(?,?,?,?)");
            ps.setInt(1,custId);
            ps.setDate(2,date);
            ps.setFloat(3, totalamount);
            ps.setString(4, cusname);
            ps.executeUpdate();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updatecustomer(int customerid,String name,String address,String phone){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("UPDATE customer set Customername= ?,Phonenumber= ?,Address= ? WHERE idCustomer= ?");
            ps.setString(1, name);
            ps.setString(2,phone );
            ps.setString(3,address);
            ps.setInt(4, customerid);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateproduct(int productid,String name,float price,int quantity){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("UPDATE product set Productname= ?,price= ?,quantity= ? WHERE productid= ?");
            ps.setString(1, name);
            ps.setFloat(2,price );
            ps.setInt(3,quantity);
            ps.setInt(4, productid);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deletecustomer(String phone){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("DELETE from customer where Phonenumber= ?");
            ps.setString(1, phone);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteproduct(int pid){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("DELETE from product where productid= ?");
            ps.setInt(1, pid);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Bill> getallbBills(){
        ArrayList<Bill> l1 = new ArrayList<>();
        ResultSet r=null;
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            ps=con.prepareStatement("SELECT * FROM bills");
            r=ps.executeQuery();
            while (r.next()) {
                l1.add(new Bill(r.getInt("Billsid"),r.getInt("customerid"),r.getDate("date"), r.getFloat("amount"),r.getString("customername")));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return l1;
    }
    public void gettopthreesold2(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("select productname,sum(quantity) as quantity from billgenerator group by productname order by sum(quantity) desc limit 3");
            ResultSet r=null;
            r=ps.executeQuery();
            System.out.println("Product-Name\t"+"quantity");
            while(r.next()){
                System.out.println(r.getString("productname")+" \t"+r.getInt("quantity"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void gettopthreesold1(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("select productname,sum(amount) as quantity from billgenerator group by productname order by sum(amount) desc limit 3");
            ResultSet r=null;
            r=ps.executeQuery();
            System.out.println("Product-Name\t"+"amount");
            while(r.next()){
                System.out.println(r.getString("productname")+" \t"+r.getInt("quantity"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void topthreeproduct(int month){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("select billgenerator.productname as name,sum(billgenerator.amount) as price from billgenerator inner join bills on Billgeneratorid=Billsid where month(bills.date)= ? group by productname order by price desc  limit 3");
            ps.setInt(1, month);
            ResultSet r=null;
            r=ps.executeQuery();
            System.out.println("Product-Name\t"+"price");
            while(r.next()){
                System.out.println(r.getString("name")+" \t"+r.getInt("price"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void toptwocustomer(int month){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("select customerid, customername,sum(amount) as price from bills where month(date)= ? group by customername,customerid order by price desc limit 2");
            ps.setInt(1, month);
            ResultSet r=null;
            r=ps.executeQuery();
            System.out.println("customerid \t"+"customername \t"+"price \t");
            while(r.next()){
                System.out.println(r.getInt("customerid")+" \t"+r.getString("customername")+" \t"+r.getFloat("price"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Bill> getparicularinvoice(int id){
        //  Bill bills = new Bill(id, id, null, id, null);
        ArrayList<Bill> l1 = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("select * from bills where customerid= ?");
            ps.setInt(1, id);
            ResultSet r=null;
            r=ps.executeQuery();
            // System.out.println("Product-Name\t"+"quantity");
            while(r.next()){
                l1.add(new Bill(r.getInt("Billsid"),r.getInt("customerid"),r.getDate("date"),r.getFloat("amount"),r.getString("customername")));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return l1;
    }
    public ArrayList<BillGenerator> particularbillid(int id)
    {
        ArrayList<BillGenerator> l1 = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("select * from billgenerator where billgeneratorid= ?");
            ps.setInt(1, id);
            ResultSet r=null;
            r=ps.executeQuery();
            while(r.next()){
             l1.add(new BillGenerator(r.getInt("Billgeneratorid"),r.getString("productname"),r.getInt("pid"),r.getInt("quantity"),r.getFloat("productprice"),r.getFloat("amount")));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return l1;

    }
    public ArrayList<Product> getlowproduct(){
        ArrayList<Product> l1 = new ArrayList<>();
        ResultSet r=null;
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            ps=con.prepareStatement("SELECT * FROM product where quantity<=10");
            r=ps.executeQuery();
            while (r.next()) {
                l1.add(new Product(r.getInt("productid"),r.getString("Productname"),r.getInt("quantity"), r.getFloat("price"),r.getInt("tax")));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return l1;
    }
    public void gettoprangemonth(int month1,int month2){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("SELECT billgenerator.productname AS name, SUM(billgenerator.amount) AS price FROM billgenerator INNER JOIN bills ON billgenerator.Billgeneratorid = bills.Billsid \n" + //
                                "WHERE MONTH(bills.date) BETWEEN ? AND ? GROUP BY billgenerator.productname ORDER BY price DESC LIMIT 3");
            ps.setInt(1, month1);
            ps.setInt(2, month2);
            ResultSet r=null;
            r=ps.executeQuery();
            System.out.println("Product-Name\t"+"price");
            while(r.next()){
                System.out.println(r.getString("name")+" \t"+r.getFloat("price"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void topthreecustomer(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("SELECT c.customername FROM customer c JOIN ( SELECT customername, SUM(amount) as total_amount FROM bills GROUP BY customername ORDER BY total_amount DESC LIMIT 3 ) b ON c.customername = b.customername");
            ResultSet r=ps.executeQuery();
            while(r.next()){
                System.out.println(r.getString("customername"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void topthreephonenumber(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456789@A");
            PreparedStatement ps = conn.prepareStatement("SELECT c.phonenumber FROM customer c JOIN ( SELECT customername, SUM(amount) as total_amount FROM bills GROUP BY customername ORDER BY total_amount DESC LIMIT 3 ) b ON c.customername = b.customername");
            ResultSet r=ps.executeQuery();
            while(r.next()){
                System.out.println(r.getString("customername"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    

    
}

