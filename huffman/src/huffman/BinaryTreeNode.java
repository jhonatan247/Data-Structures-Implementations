package huffman;

public class BinaryTreeNode {
    private Character element;
    private Integer count;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;
    
    public BinaryTreeNode(char theElement, int theCount){
        element = theElement;
        count = theCount;
        leftChild = null;
        rightChild = null;
    }
    
    public BinaryTreeNode(BinaryTreeNode theLeftChild, BinaryTreeNode theRightChild){
        element = null;
        if(theLeftChild!= null)
            count = theLeftChild.getCount();
        if(theRightChild != null)
            count +=theRightChild.getCount();
        leftChild = theLeftChild;
        rightChild = theRightChild;
    }
    
    public BinaryTreeNode getLeftChild(){
        return leftChild;
    }
    
    public BinaryTreeNode getRightChild(){
        return rightChild;
    }
    
    public Character getElement(){
        return element;
    }

    public Integer getCount() {
        return count;
    }
}
