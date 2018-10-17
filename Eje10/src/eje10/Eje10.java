package eje10;

import java.util.*;
import java.io.*;

class ArrayQueue<T> {

    int front;
    int back;
    T[] queue;

    public ArrayQueue(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("initialCapacity must be >= 1");
        }
        queue = (T[]) new Object[initialCapacity + 1];
        front = back = 0;
    }

    public ArrayQueue() {
        this(10);
    }

    public boolean isEmpty() {
        return back == front;
    }

    public T getFrontElement() {
        if (isEmpty()) {
            return null;
        } else {
            return queue[(front + 1) % queue.length];
        }
    }

    public void put(T theElement) {
        if ((back + 1) % queue.length == front) {
            T[] newQueue = (T[]) new Object[2 * queue.length];
            int start = (front + 1) % queue.length;
            if (start < 2) {
                System.arraycopy(queue, start, newQueue, 0, queue.length - 1);
            } else {
                System.arraycopy(queue, start, newQueue, 0, queue.length - start);
                System.arraycopy(queue, 0, newQueue, queue.length - start, back + 1);
            }
            front = newQueue.length - 1;
            back = queue.length - 2;
            queue = newQueue;
        }
        back = (back + 1) % queue.length;
        queue[back] = theElement;
    }

    public T remove() {
        if (isEmpty()) {
            return null;
        }
        front = (front + 1) % queue.length;
        T frontElement = queue[front];
        queue[front] = null;
        return frontElement;
    }
}

class ArrayCity {

    int city[][];
    int latifundiaLargue;

    public ArrayCity(int n, int m) {
        latifundiaLargue = (n > m ? generateSize(n) : generateSize(m));
        city = new int[latifundiaLargue][latifundiaLargue];
    }

    private int generateSize(int improvizedSize) {
        int size = 4;
        while (size < improvizedSize) {
            size *= 4;
        }
        return size;
    }

}

class Node {

    int minI;
    int minJ;
    int maxI;
    int maxJ;
    long sum;
    int value;
    int childs;
    int depth;

    public Node(int I, int J, int value) {
        this.minI = I;
        this.minJ = J;
        this.maxI = I;
        this.maxJ = J;
        this.sum = value;
        this.value = value;
    }

    public Node(int minI, int minJ, int maxI, int maxJ, int depth) {
        this.minI = minI;
        this.minJ = minJ;
        this.maxI = maxI;
        this.maxJ = maxJ;
        this.depth = depth;
    }

}

class DesroyedCity {

    Node city[];
    int size;
    ArrayCity initialCity;
    int edgeLength;
    int depth;

    public DesroyedCity(ArrayCity initialCity) {
        edgeLength = initialCity.latifundiaLargue;
        size = generateTerritory(edgeLength * edgeLength);
        this.initialCity = initialCity;
        try{
            city = new Node[size];
            divideCity();
        }catch(Exception e){
            
        }
    }

    public void divideCity() {
        city[1] = new Node(0, 0, edgeLength-1, edgeLength-1, depth);
        ArrayQueue<Node> parents = new ArrayQueue();
        parents.put(city[1]);
        Node currentParent;
        int halfMaxI;
        int halfMaxJ;
        int halfMaxINext;
        int halfMaxJNext;
        Node current;
        int currentDepth;
        for (int i = 2; i < size;) {
            currentParent = parents.remove();
            currentDepth = currentParent.depth - 1;
            if (currentDepth > 0) {
                halfMaxI = currentParent.maxI / 2;
                halfMaxJ = currentParent.maxJ / 2;
                halfMaxJNext = halfMaxJ + 1;
                halfMaxINext = halfMaxI + 1;
                current = new Node(currentParent.minI, currentParent.minJ,
                        halfMaxI, halfMaxJ, currentDepth);
                city[i++] = current;
                parents.put(current);

                current = new Node(currentParent.minI, halfMaxJNext,
                        halfMaxI, currentParent.maxJ, currentDepth);
                city[i++] = current;
                parents.put(current);

                current = new Node(halfMaxINext, currentParent.minJ,
                        currentParent.maxI, halfMaxJ, currentDepth);
                city[i++] = current;
                parents.put(current);

                current = new Node(halfMaxINext, halfMaxJNext,
                        currentParent.maxI, currentParent.maxJ, currentDepth);
                city[i++] = current;
                parents.put(current);
            }else{
                city[i++] = new Node(currentParent.minI, currentParent.minJ, 
                        initialCity.city[currentParent.minI][currentParent.minJ]);
                city[i++] = new Node(currentParent.minI, currentParent.maxJ, 
                        initialCity.city[currentParent.minI][currentParent.maxJ]);

                city[i++] = new Node(currentParent.maxI, currentParent.minJ, 
                        initialCity.city[currentParent.maxI][currentParent.minJ]);

                city[i++] = new Node(currentParent.maxI, currentParent.maxJ, 
                        initialCity.city[currentParent.maxI][currentParent.maxJ]);
            }
        }
    }

    private int generateTerritory(int populationBase) {
        int sections = 5;
        int territory = 4;
        depth = 1;
        while (territory < populationBase) {
            territory *= 4;
            sections += territory;
            depth++;
        }
        return sections + 1;
    }

}

class Eje10 {

    public static ArrayCity GenerateInicialCity(int n, int m, int f11, int a, int b) {
        int aExp = 1;
        int aExpSum = 0;
        ArrayCity territory = new ArrayCity(n, m);
        int mod = 1000000;
        a %= mod;
        b %= mod;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                territory.city[i][j] = ((aExp * f11) % mod + (b * aExpSum) % mod) % mod;
                aExpSum += aExp;
                aExpSum %= mod;
                aExp *= a;
                aExp %= mod;

            }
        }
        return territory;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String type = input.readLine();
        StringTokenizer s;
        if (type.equals("AVENIDA")) {
            int n = Integer.parseInt(input.readLine());
            s = new StringTokenizer(input.readLine());
            int f1 = Integer.parseInt(s.nextToken());
            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());
            DesroyedCity protestCity = new DesroyedCity(
                    GenerateInicialCity(n, 1, f1, a, b));
            String line;
            int i, j, x;
            while (true) {
                line = input.readLine();
                if (line == null) {
                    break;
                }
                s = new StringTokenizer(line);
                if (s.nextToken().equals("MARCHA")) {
                    i = Integer.parseInt(s.nextToken());
                    j = Integer.parseInt(s.nextToken());
                } else {
                    i = Integer.parseInt(s.nextToken());
                    x = Integer.parseInt(s.nextToken());
                }
            }
        } else {
            s = new StringTokenizer(input.readLine());
            int n = Integer.parseInt(s.nextToken());
            int m = Integer.parseInt(s.nextToken());
            s = new StringTokenizer(input.readLine());
            int f11 = Integer.parseInt(s.nextToken());
            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());
            DesroyedCity protestCity = new DesroyedCity(
                    GenerateInicialCity(n, m, f11, a, b));
            String line;
            int i, j, i2, j2, x;
            while (true) {
                line = input.readLine();
                if (line == null) {
                    break;
                }
                s = new StringTokenizer(line);
                if (s.nextToken().equals("MARCHA")) {
                    i = Integer.parseInt(s.nextToken());
                    j = Integer.parseInt(s.nextToken());
                    i2 = Integer.parseInt(s.nextToken());
                    j2 = Integer.parseInt(s.nextToken());
                } else {
                    i = Integer.parseInt(s.nextToken());
                    j = Integer.parseInt(s.nextToken());
                    x = Integer.parseInt(s.nextToken());

                }
            }
        }
    }

}
