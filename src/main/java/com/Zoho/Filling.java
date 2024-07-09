package com.Zoho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Filling {
    public Scanner obj = new Scanner(System.in);
    public String filString="";
    HashMap<String,Integer> fillhash=new HashMap<>();
    public Filling(){
        fillhash.put("Paneer Tikka", 100);
        fillhash.put("Turkey Meat", 130);
        fillhash. put("Chicken Tikka", 120);
        fillhash.put("Chicken", 120);
    }
     public boolean check(int index,int star,int end){
        return star<=index && end>index;
    }
    public void fillselect(){
        ArrayList<String> keys = new ArrayList<>(fillhash.keySet());
        while(!check(keys.indexOf(filString), 0, keys.size())){
            System.out.println("Select 'Filling' (max of 1)");
            for (int i = 0; i < keys.size(); i++) {
                System.out.println((i + 1) + ") " + keys.get(i) + " - $" + fillhash.get(keys.get(i)));
            }
            int option=obj.nextInt();
            filString=keys.get(option-1);
        }
        System.out.println("select 'filling' "+filString);
        
    }
}
