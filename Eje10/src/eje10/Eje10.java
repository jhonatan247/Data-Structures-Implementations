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

class StreetNode {

    int minI;
    int maxI;
    long sum;
    int value;
    int indxVal;
    int childs;
    int depth;
    int version;

    public StreetNode(int I, int value, int sum, int indxVal) {
        this.minI = I;
        this.maxI = I;
        this.sum = value;
        this.value = value;
        this.indxVal = indxVal;
    }

    public StreetNode(int minI, int maxI, int depth) {
        this.minI = minI;
        this.maxI = maxI;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "[" + minI + "," + maxI + "] = " + value + " -> " + sum;
    }

}

class DesroyedStreet {

    StreetNode street[];
    int size;
    int initialStreet[];
    int edgeLength;
    int depth;
    ArrayList<Integer> updateIndex = new ArrayList();
    ArrayList<Integer> updateValue = new ArrayList();
    ArrayList<Integer> updateDelta = new ArrayList();

    public DesroyedStreet(int n, int f1, int a, int b) {
        GenerateInicialStreet(n, f1, a, b);
        size = generateTerritory(edgeLength);
        street = new StreetNode[size];
        divide();
        inicialize();
        updateIndex.add(0);
        updateValue.add(0);
        updateDelta.add(0);
    }

    public void upgrade(int child, int newV) {
        StreetNode currentChild = street[child];
        int currentV = currentChild.version;
        int index;
        int value;
        if (currentChild.depth == 0) {
            for (int i = currentV + 1; i <= newV; i++) {
                index = updateIndex.get(i);
                if (index == currentChild.minI) {
                    value = updateValue.get(i);
                    currentChild.value = value;
                    currentChild.sum = value;
                }
            }
        } else {
            for (int i = currentV + 1; i <= newV; i++) {
                index = updateIndex.get(i);
                if (index >= currentChild.minI && index <= currentChild.maxI) {
                    value = updateValue.get(i);
                    currentChild.sum += updateDelta.get(i);
                    if (value > currentChild.value) {
                        currentChild.value = value;
                        currentChild.indxVal = index;
                    }else if(value == currentChild.value && index < currentChild.indxVal ){
                        currentChild.indxVal = index;
                    }
                }
            }
        }
        currentChild.version = newV;
    }

    public void update(int i, int value) {
        i--;
        int actual = 1;
        updateIndex.add(i);
        updateValue.add(value);
        int currentVersion = street[actual].version;
        int delta = value - initialStreet[i];
        updateDelta.add(delta);
        while (street[actual].value > value) {
            if (street[actual].version < currentVersion) {
                upgrade(actual, currentVersion);
            }
            if (street[actual].depth == 0) {
                break;
            } else {
                street[actual].version++;
                street[actual].sum += delta;
                actual <<= 1;
                if (street[actual].maxI < i) {
                    street[actual].version++;
                    actual++;
                }
            }
        }
        street[actual].version++;
        street[actual].sum += delta;
        if(street[actual].value == value){
            if(street[actual].indxVal> i){
                street[actual].indxVal = i;
            }
        }else{
            street[actual].value = value;
            street[actual].indxVal = i;
        }
    }
    public void destroyStreet(int init, int fin){
        
        init--;
        fin--;
        if(init > fin){
            int aux = init;
            init = fin;
            fin = aux;
        }
            
        int actual = 1;
        ArrayQueue<Integer> q = new ArrayQueue();
        q.put(actual);
        int currentVersion = street[actual].version;
        int max = 0;
        int indxMax = 0;
        long sum = 0;
        StreetNode current;
        int idChildL;
        int idChildR;
        StreetNode childL;
        StreetNode childR;
        while(!q.isEmpty()){
            actual = q.remove();
            current = street[actual];
            if(current.version < currentVersion)
                upgrade(actual, currentVersion);
            if(init<= current.minI && fin >= current.maxI){
                if(max < current.value){
                    max = current.value;
                    indxMax = current.indxVal;
                }else if(max == current.value && indxMax>current.indxVal){
                    indxMax = current.indxVal;
                }
                sum += current.sum;
            }else{
                idChildL = actual<<1;
                idChildR = idChildL+1;
                childL = street[idChildL];
                childR = street[idChildR];
                if(!(init > childL.maxI))
                    q.put(idChildL);
                if(!(fin < childR.minI))
                    q.put(idChildR);
            }
                
        }
        indxMax++;
        System.out.println(indxMax+" "+sum);
    }
    private int generateSize(int improvizedSize) {
        edgeLength = 2;
        while (edgeLength < improvizedSize) {
            edgeLength <<= 1;
        }
        return edgeLength;
    }

    public final void GenerateInicialStreet(int n, int f1, int a, int b) {
        int aExp = 1;
        int aExpSum = 0;
        initialStreet = new int[generateSize(n)];
        int mod = 1000000;
        a %= mod;
        b %= mod;
        for (int i = 0; i < n; i++) {
            initialStreet[i] = ((aExp * f1) % mod + (b * aExpSum) % mod) % mod;
            aExpSum += aExp;
            aExpSum %= mod;
            aExp *= a;
            aExp %= mod;
        }
    }

    public final void divide() {
        street[1] = new StreetNode(0, edgeLength - 1, depth);
        ArrayQueue<StreetNode> parents = new ArrayQueue();
        parents.put(street[1]);
        StreetNode currentParent;
        int halfMaxI;
        int halfMaxINext;
        StreetNode current;
        int currentDepth;
        for (int i = 2; i < size;) {
            currentParent = parents.remove();
            currentDepth = currentParent.depth - 1;
            if (currentDepth > 0) {
                halfMaxI = currentParent.minI + ((currentParent.maxI - currentParent.minI) >> 1);
                halfMaxINext = halfMaxI + 1;
                current = new StreetNode(currentParent.minI, halfMaxI, currentDepth);
                street[i++] = current;
                parents.put(current);

                current = new StreetNode(halfMaxINext, currentParent.maxI, currentDepth);
                street[i++] = current;
                parents.put(current);
            } else {
                street[i++] = new StreetNode(currentParent.minI,
                        initialStreet[currentParent.minI], initialStreet[currentParent.minI], currentParent.minI);

                street[i++] = new StreetNode(currentParent.maxI,
                        initialStreet[currentParent.maxI],initialStreet[currentParent.maxI], currentParent.maxI);
            }
        }
    }

    public final void inicialize() {
        ArrayQueue<StreetNode> streetValues = new ArrayQueue<StreetNode>();
        StreetNode current;
        StreetNode childL;
        StreetNode childR;
        for (int i = size - 1; i > 0; i--) {
            current = street[i];
            if (current.depth > 0) {
                childL = streetValues.remove();
                childR = streetValues.remove();
                if(childR.value> childL.value){
                    current.value = childR.value;
                    current.indxVal = childR.indxVal;
                }else if(childL.value == childR.value){
                    if(childL.indxVal> childR.indxVal){
                        current.indxVal = childL.indxVal;
                    }else{
                        current.indxVal = childL.indxVal;
                    }
                }else{
                    current.value = childL.value;
                    current.indxVal = childL.indxVal;
                }
                current.value = max(childL.value, childR.value);
                current.sum = childL.sum + childR.sum;
            }
            streetValues.put(current);
        }
    }

    private int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    private int generateTerritory(int populationBase) {
        depth = 0;
        for (int x = populationBase; x > 1; x = x >> 1, depth++);
        return populationBase << 1;
    }

    public final void print() {
        for (int i = 1; i < size; i++) {
            System.out.println(street[i].toString());
        }
    }
}

class CityNode {

    int minI;
    int minJ;
    int maxI;
    int maxJ;
    long sum;
    int value;
    int childs;
    int depth;

    public CityNode(int I, int J, int value) {
        this.minI = I;
        this.minJ = J;
        this.maxI = I;
        this.maxJ = J;
        this.sum = value;
        this.value = value;
    }

    public CityNode(int minI, int minJ, int maxI, int maxJ, int depth) {
        this.minI = minI;
        this.minJ = minJ;
        this.maxI = maxI;
        this.maxJ = maxJ;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "[" + minI + "," + minJ + "]" + "[" + maxI + "," + maxJ + "] = " + value + " -> " + sum;
    }

}

class DesroyedCity {

    CityNode city[];
    int size;
    int initialCity[][];
    int edgeLength;
    int depth;

    public DesroyedCity(int n, int m, int f11, int a, int b) {
        GenerateInicialCity(n, m, f11, a, b);
        size = generateTerritory(edgeLength);
        city = new CityNode[size];
        divide();
        inicialize();
        print();
    }

    private int generateSize(int improvizedSize) {
        edgeLength = 2;
        while (edgeLength < improvizedSize) {
            edgeLength <<= 1;
        }
        return edgeLength;
    }

    public final void GenerateInicialCity(int n, int m, int f11, int a, int b) {
        int aExp = 1;
        int aExpSum = 0;
        if (n > m) {
            initialCity = new int[generateSize(n)][edgeLength];
        } else {
            initialCity = new int[generateSize(m)][edgeLength];
        }
        int mod = 1000000;
        a %= mod;
        b %= mod;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                initialCity[i][j] = ((aExp * f11) % mod + (b * aExpSum) % mod) % mod;
                aExpSum += aExp;
                aExpSum %= mod;
                aExp *= a;
                aExp %= mod;

            }
        }
    }

    public final void divide() {
        city[1] = new CityNode(0, 0, edgeLength - 1, edgeLength - 1, depth);
        ArrayQueue<CityNode> parents = new ArrayQueue();
        parents.put(city[1]);
        CityNode currentParent;
        int halfMaxI;
        int halfMaxJ;
        int halfMaxINext;
        int halfMaxJNext;
        CityNode current;
        int currentDepth;
        for (int i = 2; i < size;) {
            currentParent = parents.remove();
            currentDepth = currentParent.depth - 1;
            if (currentDepth > 0) {
                halfMaxI = currentParent.minI + ((currentParent.maxI - currentParent.minI) >> 1);
                halfMaxJ = currentParent.minJ + ((currentParent.maxJ - currentParent.minJ) >> 1);
                halfMaxJNext = halfMaxJ + 1;
                halfMaxINext = halfMaxI + 1;
                current = new CityNode(currentParent.minI, currentParent.minJ,
                        halfMaxI, halfMaxJ, currentDepth);
                city[i++] = current;
                parents.put(current);

                current = new CityNode(currentParent.minI, halfMaxJNext,
                        halfMaxI, currentParent.maxJ, currentDepth);
                city[i++] = current;
                parents.put(current);

                current = new CityNode(halfMaxINext, currentParent.minJ,
                        currentParent.maxI, halfMaxJ, currentDepth);
                city[i++] = current;
                parents.put(current);

                current = new CityNode(halfMaxINext, halfMaxJNext,
                        currentParent.maxI, currentParent.maxJ, currentDepth);
                city[i++] = current;
                parents.put(current);
            } else {
                city[i++] = new CityNode(currentParent.minI, currentParent.minJ,
                        initialCity[currentParent.minI][currentParent.minJ]);
                city[i++] = new CityNode(currentParent.minI, currentParent.maxJ,
                        initialCity[currentParent.minI][currentParent.maxJ]);

                city[i++] = new CityNode(currentParent.maxI, currentParent.minJ,
                        initialCity[currentParent.maxI][currentParent.minJ]);

                city[i++] = new CityNode(currentParent.maxI, currentParent.maxJ,
                        initialCity[currentParent.maxI][currentParent.maxJ]);
            }
        }
    }

    public final void inicialize() {
        ArrayQueue<CityNode> streetValues = new ArrayQueue<CityNode>();
        CityNode current;
        CityNode child1;
        CityNode child2;
        CityNode child3;
        CityNode child4;
        for (int i = size - 1; i > 0; i--) {
            current = city[i];
            if (current.depth > 0) {
                child1 = streetValues.remove();
                child2 = streetValues.remove();
                child3 = streetValues.remove();
                child4 = streetValues.remove();
                current.value = max(child1.value, child2.value, child3.value, child4.value);
                current.sum = child1.sum + child2.sum + child3.sum + child4.sum;
            }
            streetValues.put(current);
        }
    }

    private int max(int a, int b, int c, int d) {
        if (a >= b && a >= c && a >= d) {
            return a;
        }
        if (b >= c && b >= d) {
            return b;
        }
        if (c >= d) {
            return c;
        }
        return d;
    }

    public final void print() {
        for (int i = 1; i < size; i++) {
            System.out.println(city[i].toString());
        }
    }

    private int generateTerritory(int populationBase) {
        int exponencialBase = populationBase;
        depth = 0;
        for (int x = populationBase; x > 1; x >>= 1, depth++);
        exponencialBase <<= depth;
        int sections = exponencialBase;
        for (int x = 1; x < exponencialBase; sections += x, x <<= 2);
        return sections + 1;
    }

}

class Eje10 {

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
            DesroyedStreet protestCity = new DesroyedStreet(n, f1, a, b);
            String line;
            int i, j, x;
            String select;
            while (true) {
                line = input.readLine();
                if (line == null) {
                    break;
                }
                s = new StringTokenizer(line);
                select = s.nextToken();
                if (select.equals("MARCHA")) {
                    i = Integer.parseInt(s.nextToken());
                    j = Integer.parseInt(s.nextToken());
                    protestCity.destroyStreet(i,j);
                } else if (select.equals("C")) {
                    protestCity.print();
                    return;
                } else {
                    i = Integer.parseInt(s.nextToken());
                    x = Integer.parseInt(s.nextToken());
                    protestCity.update(i, x);
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
            DesroyedCity protestCity = new DesroyedCity(n, m, f11, a, b);
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
