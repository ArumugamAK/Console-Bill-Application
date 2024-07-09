package com.Zoho;

import java.util.*;
public class Topping {
    public Scanner obj = new Scanner(System.in);
    HashMap<String,Integer> Toppinghash= new  HashMap<>();
    public Topping(){
        Toppinghash.put("Carrot", 12);
        Toppinghash.put("Cabbage", 20);
        Toppinghash.put("Tomato", 20);
        Toppinghash.put("Cucumber", 25);
        Toppinghash.put("Meat Strip", 45);
    }
    // public  Filling fills = new Filling();
    private Filling fills = new Filling();
    public String toppString="";
    public Boolean isfree=false;
    public ArrayList<String> l1 = new ArrayList<>();
    public int min=12;
    public String freecostString="";
    public boolean check(int index,int star,int end){
        return star<=index && end>index;
    }
    public void toppingselect(){
        ArrayList<String> keys = new ArrayList<>(Toppinghash.keySet());
        for (int i = 0; i < keys.size(); i++) {
            System.out.println((i + 1) + ") " + keys.get(i) + " - $" + Toppinghash.get(keys.get(i)));
        }
        System.out.println("Select 'Topping' (max of 3)");
        int freecost=obj.nextInt();
        if(freecost==3)isfree=true;
        // if(!freecostString.equals("")){
        //     l1.add(freecostString);
        // }
        // System.out.println("fill"+fills.filString);
        for(int i=0;i<freecost;i++){
            toppString="";
            while(!check(keys.indexOf(toppString), 0, keys.size())){
                System.out.println("Enter the choice : ");
                int option=obj.nextInt();
                if(fills.filString.equals("Paneer Tikka")){
                    System.out.println("True");
                    toppString=keys.get(option-1);
                    if(toppString.equals("Meat Strip")){
                        System.out.println("it is pure veg don't choose it");
                        toppString="";
                    }
                }
                else{
                    toppString=keys.get(option-1);
                }
            }
            if(min>=Toppinghash.get(toppString)){
                min=Toppinghash.get(toppString);
                freecostString=toppString;
            }
            System.out.println("Select topping :"+toppString);
            l1.add(toppString);
        }
    }
    

}
