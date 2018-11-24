package eje12;
import java.util.*;
import java.io.*;

public class Eje12 {

    
    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input;
        String line;
        while(true){
            line = b.readLine();
            if(line == null) return;
            input = new StringTokenizer(line);
            switch(input.nextToken()){
                case "CREATE":
                    
                    break;
                case "READ":
                    
                    break;
                case "UPDATE":
                    
                    break;
                case "DELETE":
                    
                    break;
            }
        }
        
    }
    
}
