package com.Zoho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Crust {
    public Scanner obj = new Scanner(System.in);
    HashMap<String, Integer> crHashMap = new HashMap<>();
    public Crust() {
        crHashMap.put("Hard", 10);
        crHashMap.put("Thin", 20);
        crHashMap.put("Light", 10);
        crHashMap.put("Soft", 12);
    }
    String cString="";
    public boolean check(int index,int star,int end){
        return star<=index && end>index;
    }
    public void crustselect(){
        ArrayList<String> keys = new ArrayList<>(crHashMap.keySet());
        while(!check(keys.indexOf(cString), 0, keys.size())){
            System.out.println("Select 'Crust' (max of 1)");
            for (int i = 0; i < keys.size(); i++) {
                System.out.println((i + 1) + ") " + keys.get(i) + " - $" + crHashMap.get(keys.get(i)));
            }
            int option=obj.nextInt();
            cString=keys.get(option-1);
        }
        System.out.println("select Crust "+cString);
    }


}
