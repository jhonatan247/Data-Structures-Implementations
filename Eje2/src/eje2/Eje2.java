/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eje2;
import java.util.Scanner;
/**
 *
 * @author Usuario
 */
public class Eje2 {
    
    /**
     * @param args the command line arguments
     */
    public void printChar(char val, int cant){
        for(int i = 0; i<cant; i++){
            System.out.print(val);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        long n = input.nextInt();
        long m = input.nextInt();
        long parts = Math.round(Math.pow(2, m));
        long max = n/parts;
        boolean pixel= true;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
            
            }
        }
    }
    
}
