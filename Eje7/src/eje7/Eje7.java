package eje7;

import java.util.*;
import java.io.*;


public class Eje7 {
    static void print(Stack<Character> c){
        while(!c.isEmpty()){
            System.out.print(c.pop());
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        char in[] = s.readLine().toCharArray();
        
        Stack<Stack<Character>> q = new Stack();
        
        Stack<Character> actual= new Stack();
        char c = ' ';
        for(int i = in.length -1; i>=0; i--){
            c = in[i];
            if(c == '('){
                print(actual);
                actual = new Stack();
            }else if(c == ')'){
                q.push(actual);
                actual = new Stack();
            }else{
                actual.push(c);
            }
        }
        print(actual);
        while(!q.isEmpty()){
            print(q.pop());
        }
    }
    
}