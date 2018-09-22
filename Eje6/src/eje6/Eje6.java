package eje6;
import java.io.*;
import java.util.*;
class ArrayQueue<T>{
    int front;
    int back;
    T[] queue;

    public ArrayQueue(int initialCapacity ){
        if(initialCapacity < 1)
            throw new IllegalArgumentException("initialCapacity must be >= 1");
        queue = (T[]) new Object[initialCapacity + 1];
        front = back = 0;
    }
    public ArrayQueue(){
        this(10);
    }
    public boolean isEmpty(){
        return back == front;
    }
    public T getFrontElement(){
        if(isEmpty()) return null;
        else return queue[(front + 1) % queue.length];
    }
    public void add(T theElement){
        if((back + 1) % queue.length == front){
            T[] newQueue = (T []) new Object[2 * queue.length];
            int start = (front + 1) % queue.length;
            if(start < 2)
                System.arraycopy(queue, start, newQueue, 0, queue.length - 1);
            else{
                System.arraycopy(queue, start, newQueue, 0, queue.length - start);
                System.arraycopy(queue, 0, newQueue, queue.length - start, back+ 1);
            }
            front = newQueue.length - 1;
            back = queue.length - 2;
            queue = newQueue;
        }
        back = (back + 1) % queue.length;
        queue[back] = theElement;
    }
    public T remove(){
        if(isEmpty())return null;
        front = (front + 1) % queue.length;
        T frontElement = queue[front];
        queue[front] = null;
        return frontElement;
    }
}
class Carrera{
    ArrayQueue<Estudiante> q;
    String Carrera;

    public Carrera(String nombre, String Carrera) {
        this.Carrera = Carrera;
        q = new ArrayQueue();
        q.add(new Estudiante(nombre));
    }
    
    public void add(String nombre){
        q.add(new Estudiante(nombre));
    }
    public String remove(){
        return q.remove().nombre;
    }
    public boolean isEmpty(){
        return q.isEmpty();
    }
    public void setNull(){
        this.Carrera = "";
        q = null;
    }
}
class Estudiante{
    String nombre;

    public Estudiante(String nombre) {
        this.nombre = nombre;
    }
    
}
public class Eje6 {
    public static void main(String[] args) throws Exception{
        ArrayList<Carrera> s = new ArrayList();
        int actual = 0;
        int size = 0;
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;
        String opt = "";
        String name = "";
        String carrera = "";
        boolean find = false;
        while(true){
            in = new StringTokenizer(b.readLine());
            opt = in.nextToken();
            if(opt.equals("LLEGA")){
                name = in.nextToken();
                carrera = in.nextToken();
                find = false;
                for(int i = actual; i<s.size(); i++){
                    if(s.get(i).Carrera.equals(carrera)){
                        find = true;
                        s.get(i).add(name);
                        break;
                    }
                }
                if(!find){
                    s.add(new Carrera(name, carrera));
                    size++;
                }
            }else if(opt.equals("ATIENDE")){
                if(size>0){
                    System.out.println(s.get(actual).remove());
                    if(s.get(actual).isEmpty()){
                        s.get(actual).setNull();
                        actual++;
                        size--;
                    }
                }else{
                    System.out.println("Forever Alone");
                }
            
            }else
                break;
            
            
        }
        
    }
    
}
