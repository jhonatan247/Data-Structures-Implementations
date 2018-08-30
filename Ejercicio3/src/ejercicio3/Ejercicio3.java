
package ejercicio3;

import java.util.Scanner;
import java.util.ArrayList;

public class Ejercicio3 {
 
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n= s.nextInt();
        long a=s.nextInt(), b=s.nextInt(), c=s.nextInt(); 
        ArrayList<Long> l = new ArrayList();
        for(long i =1; i<=n; i++){
            l.add(i);
        }
        long initial =0;
        int mod =0;
        while(n>1){
            mod = (int)((initial+a)%n--);
            l.remove(mod);
            initial =mod;
            if(initial>= n)
                initial = 0;
            a= (a*b+c)%(1000000007);
        }
        System.out.print(l.get(0));
        
        
    }
    
}
