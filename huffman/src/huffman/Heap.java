package huffman;

import java.util.*;

public class Heap {

    private BinaryTree[] BinaryHeap;
    private int size;

    public Heap(String text) {
        initialize(text);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public BinaryTree getMax() {
        return (size == 0) ? null : BinaryHeap[1];
    }

    public void add(BinaryTree x) {
        BinaryHeap[++size] = x;
        int currentNode = size;
        BinaryTree Element = BinaryHeap[size];
        while (currentNode != 1
                && BinaryHeap[currentNode / 2].compareTo(Element) < 0) {
            BinaryHeap[currentNode] = BinaryHeap[currentNode / 2];
            currentNode /= 2;
        }
        BinaryHeap[currentNode] = Element;
    }

    public BinaryTree removeMax() {
        size--;
        BinaryTree maxElement = BinaryHeap[1];
        if (size == 0) {
            return maxElement;
        }

        BinaryHeap[1] = BinaryHeap[size + 1];
        BinaryTree Element = BinaryHeap[1];

        int currentNode = 1;
        int child = 2;
        while (child <= size) {
            if (child < size && BinaryHeap[child].compareTo(BinaryHeap[child+ 1]) < 0) {
                child++;
            }
            if (Element.compareTo(BinaryHeap[child]) >= 0) {
                break;
            }
            BinaryHeap[currentNode] = BinaryHeap[child];
            currentNode = child;
            child *= 2;
        }
        BinaryHeap[currentNode] = Element;
        return maxElement;
    }
    private void initialize(String text) {
        TreeMap<Character, Integer> map = new TreeMap();
        
        size = 0;
        int characterCount[] = new int[10000];
        char current;
        for(int i = 0; i< text.length(); i++){
            current = text.charAt(i);
            if(characterCount[current]==0){
                size++;
                characterCount[current] = 1;
            }else{
                characterCount[current]++;
            }
        }
        BinaryHeap = new BinaryTree[size+1];
        int x = 1;
        for(char i = 0; i< characterCount.length; i++){
            if(characterCount[i]!=0){
                BinaryHeap[x++] = new BinaryTree(i, characterCount[i]);
            }
        }
        
        for (int root = size / 2; root >= 1; root--) {
            BinaryTree rootElement = BinaryHeap[root];
            int child = 2 * root;
            while (child <= size) {
                if (child < size && BinaryHeap[child].compareTo(BinaryHeap[child + 1]) < 0) {
                    child++;
                }
                if (rootElement.compareTo(BinaryHeap[child]) >= 0) {
                    break;
                }
                BinaryHeap[child / 2] = BinaryHeap[child];
                child *= 2;
            }
            BinaryHeap[child / 2] = rootElement;
        }
    }
}
