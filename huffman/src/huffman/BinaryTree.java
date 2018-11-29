package huffman;

import java.util.*;

public class BinaryTree implements Comparable{
    private BinaryTreeNode root;
    
    
    private TreeMap<Character,String> binaryRepresentation;
    private StringBuilder stringRepresentation;
    public BinaryTree(BinaryTree left, BinaryTree right){
        this.root = new BinaryTreeNode(left.root,
                right.root);
    }
    
    public BinaryTree(char element, int count){
        this.root = new BinaryTreeNode(element,
                count);
    }
    
    public TreeMap getBinatyRepresentation(){
        binaryRepresentation = new TreeMap();
        stringRepresentation = new StringBuilder();
        createBinaryRepresentation("", root);
        return binaryRepresentation;
    }
    
    private void createBinaryRepresentation(String key, BinaryTreeNode node){
        if(node.getElement() == null && node.getLeftChild() != null && node.getRightChild() != null){
            createBinaryRepresentation(key+"0", node.getLeftChild());
            createBinaryRepresentation(key+"1", node.getRightChild());
        }else{
            stringRepresentation.append((int)node.getElement());
            stringRepresentation.append(" ");
            stringRepresentation.append(key);
            stringRepresentation.append(System.getProperty("line.separator"));
            
            
            binaryRepresentation.put(node.getElement(), key);
        }
    }
    
    public BinaryTreeNode root(){
        return root;
    }

    @Override
    public int compareTo(Object o) {
        return ((BinaryTree)o).root().getCount().compareTo( root.getCount() );
    }
    
    @Override
    public String toString(){
        if(stringRepresentation == null) return "";
        
        return stringRepresentation.toString();
    }
}
