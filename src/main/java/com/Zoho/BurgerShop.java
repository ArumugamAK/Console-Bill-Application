package com.Zoho;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BurgerShop {
    public Scanner obj = new Scanner(System.in);
    public Random random = new Random();
    private Filling fills = new Filling();
    private Topping top = new Topping();
    private Crust crust = new Crust();
    public ArrayList<String> crus = new ArrayList<>(crust.crHashMap.keySet());
    public ArrayList<String> fill = new ArrayList<>(fills.fillhash.keySet());
    public ArrayList<String> toping = new ArrayList<>(top.Toppinghash.keySet());
    public void billgenerate(){
        System.out.println("Bill Generate : ");
        int total=0;
        System.out.println("Crust"+crust.cString+"$ "+crust.crHashMap.get(crust.cString));
        total+=crust.crHashMap.get(crust.cString);
        System.out.println("Filling"+fills.filString+"$ "+fills.fillhash.get(fills.filString));
        total+=fills.fillhash.get(fills.filString);
        // System.out.println("Error is free :"+top.isfree+"--: "+top.freecostString);
        for(String ans :top.l1){
            if(top.isfree && ans.equals(top.freecostString)){
                System.out.println("Topping : "+ans+"$ 0");
            }
            else{
                System.out.println("Topping : "+ans+"$ "+top.Toppinghash.get(ans));
                total+=top.Toppinghash.get(ans);
            }
        }
        if(top.l1.size()==0){
            System.out.println("Topping : "+top.toppString+"$ "+top.Toppinghash.get(top.toppString));
            // System.out.println();
        }
        System.out.println("Total ::  "+  total);

    }
    public void orderingBurger(int n){
        if(n==1){
            int index1 =random.nextInt(crus.size());
            // System.out.println(index1);
            crust.cString=crus.get(index1);
            // System.out.println(crust.cString+"fill "+fill.size());
            int index2 =random.nextInt(fill.size());
            // System.out.println(index2);
            fills.filString=fill.get(index2);
            // System.out.println(fills.filString);
            int index3=random.nextInt(toping.size());
            // System.out.println(index3);
            if(fills.filString.equals("Paneer Tikka")){
                while(index3!=3){
                    index3=random.nextInt(toping.size());
                }
            }
            top.toppString=toping.get(index3);
            // System.out.println(top.toppString);
            billgenerate();
        }
        else if(n==2){
            crust.crustselect();
            fills.fillselect();
            System.out.println(fills.filString);
            top.toppingselect();
            billgenerate();
        }

    }
}
