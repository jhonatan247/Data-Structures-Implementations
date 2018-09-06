package eje4;
import java.util.*;

public class Eje4 {
    
    static Scanner s = new Scanner(System.in);
    static String in = "";
    static String elements[];
    static int indx = 0;
    static int size= 0;
    static long calcul = 0;
    static long calculateInfija(){
        in = elements[indx++];
        if(in.equals("("))
            return calculateInfija();
        long val = Integer.parseInt(in);
        if(indx<size){ 
            in = elements[indx++];
            if(in.equals("+")){
                return val + calculateInfija();
            }else if (in.equals("*")){
                return val*calculateInfija();
            }else{
                if(indx<size){
                    
                }else{
                    return val;
                }
            }
            
        }
        return val;
    }
    public static void main(String[] args) {
        String type = s.nextLine();
        if(type.equals("INFIJA")){
            elements = s.nextLine().split(" ");
            size = elements.length;
            System.out.println(calculateInfija());
        }else{
            
        }
    }
    
}
