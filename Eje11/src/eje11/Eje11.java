package eje11;

import java.io.*;
import java.util.*;

class Eje11 {

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(b.readLine());
        int n = Integer.parseInt(s.nextToken());
        int m = Integer.parseInt(s.nextToken());
        Trie tree = new Trie();
        for (int i = 0; i < n; i++) {
            tree.insert(b.readLine());
        }
        for (int i = 0; i < m; i++) {
            System.out.print(tree.count[i] + " ");
        }
    }

}

class TrieNode {

    TrieNode[] arr;

    public TrieNode() {
        this.arr = new TrieNode[26];
    }
}

class Trie {

    TrieNode root;
    long[] count;

    public Trie() {
        root = new TrieNode();
        count = new long[100];
    }

    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length() && i < 100; i++) {
            int index = word.charAt(i) - 97;
            if (p.arr[index] == null) {
                p.arr[index] = new TrieNode();
                p = p.arr[index];
                count[i]++;
            } else {
                p = p.arr[index];
            }
        }
    }
}
