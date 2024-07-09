package com.Zoho;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        // String str="zohofinance";
        // int l=0;
        // int r=str.length()-1;
        // char matrix[][]=new char [str.length()][str.length()];
        // for(int i=0;i<str.length();i++){
        //     for(int j=0;j<str.length();j++){
        //         if(i==j && j==str.length()-i-1){
        //             matrix[i][j]=str.charAt(l++);
        //             matrix[i][j]=str.charAt(r--);
        //         }
        //         else if(i==j){
        //             matrix[i][j]=str.charAt(l++);
        //         }
        //         else if(j==str.length()-i-1){
        //             matrix[i][j]=str.charAt(r--);
        //         }
        //         else{
        //             matrix[i][j]='*';
        //         }

        //     }
        // }
        // for(int i=0;i<str.length();i++){
        //     for(int j=0;j<str.length();j++){
        //         if(matrix[i][j]=='*')System.out.print(" ");
        //         else System.out.print(matrix[i][j]);
        //     }
        //     System.out.println();
        // }
        int n=obj.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=obj.nextInt();
        }
        // ArrayList<Integer> final = new ArrayList<>();
    //   ArrayList<Integer<Integer>> ans = new ArrayList<>();
    //   ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
      int sum=(int) Math.pow(2,n);
        for (int i = sum-1; i >=0; i--) {
            ArrayList<Integer> subsequence = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subsequence.add(arr[j]);
                }
            }
            System.out.println(subsequence);
            // int flag=1;
            // for(int k=0;k<subsequence.size()-1;k++){
            //     if(subsequence.get(k)>subsequence.get(k+1)){
            //         flag=0;
            //         break;
            //     }
            // }
            // if(flag==1){
            //     System.out.println(subsequence);
            //     break;
            // }
        }
    }
}
