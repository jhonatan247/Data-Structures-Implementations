package eje8;

import java.text.DecimalFormat;
import java.io.*;
import java.math.RoundingMode;
import java.util.*;

enum TYPE {

    OPERATOR,
    VALUE
}

class Node {

    int left;
    int right;
    TYPE type;
    String value;
    boolean visit;

    public Node(int left, int right, String value, boolean type) {
        this.left = left;
        this.right = right;
        if (type) {
            this.type = TYPE.OPERATOR;
        } else {
            this.type = TYPE.VALUE;
        }
        this.value = value;
        visit = false;
    }

}

public class Eje8 {

    static ArrayList<Node> tree;

    static boolean isOperator(String val) {
        return val.equals("+") || val.equals("-") || val.equals("/") || val.equals("*");
    }

    public static double Calculate() throws Exception {
        Stack<Node> s = new Stack();
        s.push(tree.get(0));
        Stack<String> operators = new Stack();
        Node actual;
        while (!s.isEmpty()) {
            actual = s.peek();
            if (actual.visit) {
                s.pop();
                if (actual.type == TYPE.OPERATOR) {
                    s.push(tree.get(actual.right));
                }
            } else {
                operators.push(actual.value);
                if (actual.type == TYPE.OPERATOR) {
                    s.push(tree.get(actual.left));
                }
                actual.visit = true;

            }
        }
        double a, b;
        char op;
        double total;
        Stack<String> others = new Stack();
        while (operators.size() != 1) {
            while (!isOperator(operators.peek())) {
                others.push(operators.pop());
            }
            a = Double.parseDouble(others.pop());
            b = Double.parseDouble(others.pop());
            op = operators.pop().charAt(0);
            switch (op) {
                case '+':
                    total = a + b;
                    break;
                case '*':
                    total = a * b;
                    break;
                case '/':
                    if(b==0)
                        throw new Exception();
                    total = a / b;
                    break;
                case '-':
                    total = a - b;
                    break;
                default:
                    total = 0;
                    break;
            }
            operators.push(total + "");
            while(!others.isEmpty()){
                operators.push(others.pop());
            }

        }
        return Double.parseDouble(operators.pop());

    }

    public static void main(String[] args) throws Exception {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(b.readLine());
        tree = new ArrayList(n);
        StringTokenizer reader;
        String value;
        for (int i = 0; i < n; i++) {
            reader = new StringTokenizer(b.readLine());
            value = reader.nextToken();
            tree.add(new Node(Integer.parseInt(reader.nextToken()) - 1, Integer.parseInt(reader.nextToken()) - 1, value, isOperator(value)));
        }

        DecimalFormat df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.HALF_UP);
        try {
            System.out.printf("%.6f\n", Double.parseDouble(df.format(Calculate())));
        } catch (Exception e) {
            System.out.println("ERROR");
        }

    }

}
