package eje4;
import java.util.*;

public class Eje4 {
    
    static String val[];
    static int indx=0;
    public static long calculateStackSum(Stack<Long> s){
        long retorno = 0;
        while(!s.isEmpty()){
            retorno += s.pop();
        }
        return retorno;
    }
    public static long calculateStackMul(Stack<Long> s){
        long retorno = 1;
        
        while(!s.isEmpty()){
            retorno *= s.pop();
        }
        return retorno;
    }
    public static long stackSum(Long v){
        Stack<Long> s = new Stack();
        if(v!=null)
            s.push(v);
        while(val.length>indx){
            switch(val[indx]){
                case "(": 
                    indx++;
                    s.push(stackSum(null));
                    break;
                case "+": 
                    if(val[indx+1].equals("(")){
                        indx+=2;
                        s.push(stackSum(null));
                        break;
                    }
                    if(indx+2>=val.length){
                        s.push(Long.parseLong(val[indx+1]));
                        indx+=2;
                        return calculateStackSum(s);
                    }
                    if(val[indx+2].equals("*")){
                        indx++;
                        s.push(stackMul(null));
                    }else{
                        s.push(Long.parseLong(val[indx+1]));
                        indx+=2;
                    }
                    break;
                case ")": 
                    indx ++;
                    return calculateStackSum(s);
                case "*": 
                    indx++;
                    s.push(stackMul(s.pop()));
                    break;
                default: 
                    
                    if(indx+1>=val.length){
                        s.push(Long.parseLong(val[indx]));
                        indx+=1;
                        return calculateStackSum(s);
                    }
                    if(val[indx+1].equals(")")){
                        s.push(Long.parseLong(val[indx]));
                        indx+=2;
                        return calculateStackSum(s);
                    }
                    
                    if(val[indx+1].equals("*")){
                        s.push(stackMul(null));
                    }else{
                        
                        s.push(Long.parseLong(val[indx]));
                        indx+=2;
                    }
                    break;
            }
        }
        
        return calculateStackSum(s);
        
        
    }
    public static long stackMul(Long v){
        Stack<Long> s = new Stack();
        if(v!=null)
            s.push(v);
        while(val.length>indx){
            switch(val[indx]){
                case "(": 
                    indx++;
                    s.push(stackSum(null));
                break;
                case "*": 
                    if(val[indx+1].equals("(")){
                        indx+=2;
                        s.push(stackSum(null));
                        break;
                    }
                    if(indx+2>=val.length){
                        s.push(Long.parseLong(val[indx+1]));
                        indx+=2;
                        return calculateStackMul(s);
                    }
                    
                    if(val[indx+2].equals("+")){
                        s.push(Long.parseLong(val[indx+1]));
                        indx += 3;
                        return calculateStackMul(s);
                    }else{
                        s.push(Long.parseLong(val[indx+1]));
                        indx+=2;
                    }
                    break;
                case ")": 
                    return calculateStackMul(s);
                case "+": 
                    indx++;
                    return stackSum(calculateStackMul(s));
                default: 
                    
                    if(indx+1>=val.length){
                        s.push(Long.parseLong(val[indx]));
                        indx+=1;
                        return calculateStackMul(s);
                    }
                    if(val[indx+1].equals(")")){
                        s.push(Long.parseLong(val[indx]));
                        indx+=1;
                        return calculateStackMul(s);
                    }
                    if(val[indx+1].equals("+")){
                        s.push(Long.parseLong(val[indx]));
                        indx+=2;
                        return calculateStackMul(s);
                    }else{
                        s.push(Long.parseLong(val[indx]));
                        indx+=2;
                    }
                    break;
            }
        }
        
        return calculateStackMul(s);
    }
    public static long stackPOS(){
        Stack<Long> s = new Stack<>();
        while(indx<val.length){
            switch(val[indx]){
                case "+":
                    s.push(s.pop()+s.pop());
                    break;
                case "*":
                   s.push(s.pop()*s.pop());
                   break;
                default:
                    s.push(Long.parseLong(val[indx]));
                    break;
            }
            indx++;
        }
        return s.pop();
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String in = "INFIJA";//s.nextLine();
        val = s.nextLine().split(" ");
        if(in.equals("INFIJA")){
            System.out.println(stackSum(null));
        }else{
            System.out.println(stackPOS());
        }
    }
    
}
