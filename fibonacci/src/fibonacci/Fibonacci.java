package fibonacci;

import java.util.ArrayList;

public class Fibonacci {
    public static ArrayList<Integer> result= new ArrayList();
    public static ArrayList<Long> f = new ArrayList();
    public static Long init = new Long("2");
    public static long tot = 0;
    public static int siz= 0;
    
    public static void fibonacci(){
        f = new ArrayList();
        f.add(new Long("0"));
        f.add(new Long("1"));
        long current = 1;
        
        for(int i = 2; current <= init; i++){
            current = f.get(i-1) + f.get(i-2);
            f.add( current );
        }
    }
    public static boolean recursive(int n){
        if(tot>init){
            return false;
        }else if(tot == init){
            return true;
        }
        for(int i = n-1; i>0; i--){
            result.add(i);
            tot += f.get(i);
            if(recursive(i))
                return true;
            
            tot -= f.get(i);
            result.remove(result.size()-1);
        }
        return false;
    }
    public static void main(String[] args) {
        fibonacci();
        siz = f.size();
        for(int i = siz-1; i>0; i--){
            result.clear();
            result.add(i);
            tot = f.get(i);
            if(recursive(i)) break;
        }
        System.out.println(result);
    }
    
}
