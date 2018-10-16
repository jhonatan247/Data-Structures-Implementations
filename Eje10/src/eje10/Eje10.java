package eje10;

import java.util.*;
import java.io.*;

class Node {

    int minI;
    int minJ;
    int maxI;
    int maxJ;
    long sum;
    int value;
    int childs;

    public Node(int minI, int minJ, int maxI, int maxJ, long sum, int value) {
        this.minI = minI;
        this.minJ = minJ;
        this.maxI = maxI;
        this.maxJ = maxJ;
        this.sum = sum;
        this.value = value;
    }
    public Node(int minI, int minJ, int maxI, int maxJ) {
        this.minI = minI;
        this.minJ = minJ;
        this.maxI = maxI;
        this.maxJ = maxJ;
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

class DesroyedCity {

    Node city[];
    int size;
    ArrayCity initialCity;
    int edgeLength;
    int depth;
    public DesroyedCity(ArrayCity initialCity) {
        edgeLength = initialCity.latifundiaLargue;
        size = generateTerritory( edgeLength*edgeLength);
        this.initialCity = initialCity;
        city = new Node[size];
    }

    public void divideCity() {
        city[1] = new Node(0,0,edgeLength,edgeLength);
        Stack<Node> parents = new Stack();
        parents.push(city[1]);
        Node currentParent= city[1];
        int currentdepth = depth-1;
        for(int i = 0; i< size; i++){
            switch(currentParent.childs){
                case 0:
                    
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
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
        return sections+1;
    }

}

public class Eje10 {

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
