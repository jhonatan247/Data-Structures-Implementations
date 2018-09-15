package eje5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Eje5 {

    public static void main(String[] args) throws java.io.IOException {
        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(s.readLine());
        String opt = "";
        PriorityQueue<Request> q = new PriorityQueue();
        for(int i = 0; i< n; i++){
            opt = s.readLine();
            if(opt.equals("reclamo")){
                q.add(new Request(s.readLine(), s.readLine(), Double.parseDouble(s.readLine()), i));
            }else{
                if(q.isEmpty()){
                    System.out.println("No hay reclamos pendientes");
                }else{
                    q.remove().print();
                }
            }
        }
        
        
    }
    
}
