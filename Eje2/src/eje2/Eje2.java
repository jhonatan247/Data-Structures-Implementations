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
    public static void printTriangleLine(long n, long m, long h) {
        if (m == 0) {
            for (int i = 0; i < n - h; i++) {
                System.out.print("#");
            }
            for (int i = 0; i < h; i++) {
                System.out.print("_");
            }
        } else {

            printTriangleLine(n / 2, m - 1, h % (n / 2));
            if (h < n / 2) {
                printTriangleLine(n / 2, m - 1, h % (n / 2));
            } else {

                for (int i = 0; i < n / 2; i++) {
                    System.out.print("_");
                }
            }
        }
    }
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long m = s.nextLong();
        for (long i = n-1; i >=0; i--) {
            printTriangleLine(n, m, i);
            System.out.println("");
        }

    }
    
}
