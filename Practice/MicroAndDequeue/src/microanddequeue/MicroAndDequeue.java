/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microanddequeue;
import java.util.*;
import java.io.*;
/**
 *
 * @author Usuario
 */
public class MicroAndDequeue {

    
    public static void main(String[] args) throws Exception {
        ArrayDeque<Integer> q = new ArrayDeque();
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        int queries = Integer.parseInt(b.readLine());
        
        StringTokenizer input;
        for(int i = 0; i<queries; i++){
            input = new StringTokenizer(b.readLine());
            if(input.nextElement().toString().equals("E")){
                q.add(Integer.parseInt(input.nextToken().toString()));
                System.out.println(q.size());
            }else{
                if(q.size() >0)
                    System.out.println(q.poll()+" "+ q.size());
                else
                    System.out.println("-1 "+ q.size());
            }
        }
        
        
    }
    
}
