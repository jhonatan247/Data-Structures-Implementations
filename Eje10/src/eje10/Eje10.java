package eje10;

import java.util.*;
import java.io.*;

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
            DestroyedStreet protestCity = new DestroyedStreet(n, f1, a, b);
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
                    protestCity.destroyStreet(i, j);
                } else if (select.equals("C")) {
                    break;
                } else {
                    i = Integer.parseInt(s.nextToken());
                    x = Integer.parseInt(s.nextToken());
                    protestCity.update(i, x);
                }
            }
            System.out.println(protestCity.script);

        } else {
            s = new StringTokenizer(input.readLine());
            int n = Integer.parseInt(s.nextToken());
            int m = Integer.parseInt(s.nextToken());
            s = new StringTokenizer(input.readLine());
            int f11 = Integer.parseInt(s.nextToken());
            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());
            //DesroyedCity protestCity = new DesroyedCity(n, m, f11, a, b);
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
