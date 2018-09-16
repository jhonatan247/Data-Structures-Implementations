package frustratedcoders;
import java.util.*;
import java.io.*;
 
 
public class TestClass {
    public static void main(String args[] ) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //Scanner in = new Scanner(System.in);
        
        int count = Integer.parseInt(in.readLine());
        ArrayList<Integer> skills = new ArrayList();
        StringTokenizer str = new StringTokenizer(in.readLine());
        for(int i = 0; i< count; i++){
            skills.add(Integer.parseInt(str.nextElement().toString()));
        }
        Collections.sort(skills);
        Stack<Stack> sums = new Stack();
        Stack<Integer> actual = new Stack();
        actual.push(skills.get(0));
        int pos = 0;
        for(int i = 1; i< count; i++){
            pos = skills.get(i);
            if(pos>actual.peek()){
                actual.pop();
                if(!actual.isEmpty()){
                    sums.push(actual);
                    actual = new Stack();
                }
                actual.push(pos);
            }else{
                actual.push(pos);
                if(!sums.isEmpty()){
                    sums.peek().pop();
                    if(sums.peek().isEmpty())
                        sums.pop();
                }
                
            }
        }
        sums.push(actual);
        count = sums.size();
        int count2 = 0;
        int sum = 0;
        actual = new Stack();
        int size = 0;
        for(int i = 0; i< count; i++){
            actual = sums.peek();
            size = actual.size();
            for(int j = 0; j< size; j++)
                sum+=actual.pop();
            sums.pop();
        }
        System.out.println(sum);
    }
}
