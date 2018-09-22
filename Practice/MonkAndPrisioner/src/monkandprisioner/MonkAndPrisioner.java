package monkandprisioner;
import java.util.*;
import java.io.*;

public class MonkAndPrisioner {

    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(read.readLine());
        StringTokenizer str = new StringTokenizer(read.readLine());
        Stack<Integer> xs = new Stack();
        long all[] = new long[count];
        long val = 0;
        
        long max = 0;
        int nil = -1;
        for(int i = 0; i< count; i++){
            val = Long.parseLong(str.nextElement().toString());
            if(val >=max){
                xs.push(nil);
                max = val;
            }else{
                for(int j = i-1; j>=0; j--){
                    if(all[j] > val){
                        xs.push(j+1);
                        break;
                    }
                }
            }
            all[i] = val;
        }
        max = 0;
        String print = "";
        for(int i = count-1; i>=0; i--){
            val = all[i];
            if(val >=max){
                print = " "+(nil + xs.pop()) +print;
                max = val;
            }else{
                for(int j = i+1; j<count; j++){
                    if(all[j] > val){
                        print = " "+(j+1 + xs.pop()) +print;
                        break;
                    }
                }
            }
        }
        System.out.println(print.trim());
    }
    
}
