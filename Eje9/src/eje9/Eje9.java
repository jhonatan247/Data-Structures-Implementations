package eje9;
import java.util.*;
import java.io.*;

class IndexList{
    int index[];
    int size = 0;
    public IndexList(int size) {
        index = new int[size];
    }
    public void remove(int id){
        index[id] = 0;
        size--;
    }
    public void set(int id, int value){
        index[id] = value;
    }
    public int get(int id){
        return index[id];
    }
    public boolean exist(int id){
        return index[id] != 0;
    }
    
}
class Task {
    static int count = 0;
    int id;
    long priority;
    String description;
    int index;

    public Task(int id, long priority, String description) {
        this.id = id;
        this.priority = priority;
        this.description = description;
        this.index = count++;
    }
    public void changePriotity(long priority){
        this.priority = priority;
    }
    public int compareTo(Task task) {
        if (priority > task.priority) {
            return 1;
        }
        if (priority < task.priority) {
            return -1;
        }
        if (index < task.index) {
            return 1;
        }

        return -1;
    }

}
class TaskQueue {
    IndexList indexList;
    Task[] taskTree;
    int size;

    public TaskQueue(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException(" initialCapacity mustbe >= 1");
        }
        taskTree = new Task[initialCapacity];
        size = 0;
        indexList = new IndexList(initialCapacity);
    }

    public TaskQueue() {
        this(200001);
    }

    public void add(Task x) {
        taskTree[++size] = x;
        percolateUp(size);
    }

    public String removeMax() {
        if (size == 0) {
            return "TASK NOT FOUND";
        }
        size --;
        Task maxElement = taskTree[1];
        indexList.remove(taskTree[1].id);
        if (size == 0) {
            return maxElement.description;
        }
        
        taskTree[1] = taskTree[size + 1];
        indexList.set(taskTree[1].id,1);
        percolateDown(1);
        return maxElement.description;
    }

    public String kill(int id) {
        if (!indexList.exist(id)) {
            return "TASK NOT FOUND";
        }
        changePriority(id, new Long("1000000000001"));
        removeMax();
        return "TASK KILLED";
    }
    public String changePriority(int id, long priority){
        if (!indexList.exist(id)) {
            return "TASK NOT FOUND";
        }
        int indx = indexList.get(id);
        Task element = taskTree[indx];
        long diff = priority - element.priority;
        element.changePriotity(priority);
        if(diff>0){
            percolateUp(indx);
        }else{
            percolateDown(indx);
        }
        return "TASK RESCHEDULED";
    }
    public void percolateUp(int index){
        int currentNode = index;
        Task Element = taskTree[index];
        while (currentNode != 1
                && taskTree[currentNode / 2].compareTo(Element) < 0) {
            taskTree[currentNode] = taskTree[currentNode / 2];
            indexList.set(taskTree[currentNode].id, currentNode);
            currentNode /= 2;
        }
        taskTree[currentNode] = Element; 
        indexList.set(Element.id, currentNode);
    }
    public void percolateDown(int index){
        Task Element = taskTree[index];
        
        int currentNode = index;
        int child = index*2;
        while (child <= size) {
            if (child < size && taskTree[child].compareTo(taskTree[child
                    + 1]) < 0) {
                child++;
            }
            if (Element.compareTo(taskTree[child]) >= 0) {
                break;
            }
            taskTree[currentNode] = taskTree[child];
            indexList.set(taskTree[currentNode].id, currentNode);
            currentNode = child;
            child *= 2;
        }
        taskTree[currentNode] = Element;
        indexList.set(Element.id, currentNode);
    }
    public void Clear(){
        for(int i = 1; i<= size; i++){
            indexList.remove(taskTree[i].id);
        }
        size = 0;
    }
}

class Eje9 {
    public static void main(String[] args) throws Exception {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        long priority;
        TaskQueue taskManager = new TaskQueue(); 
        while (true) {
            line = b.readLine();
            if(line==null) return;
            switch (line) {
                case "TASK":
                    taskManager.add(new Task(Integer.parseInt(b.readLine()),Long.parseLong(b.readLine()), b.readLine()));
                    break;
                case "EXECUTE":
                    System.out.println(taskManager.removeMax());
                    break;
                case "KILL":
                    System.out.println(taskManager.kill(Integer.parseInt(b.readLine())));
                    break;
                case "CHANGE":
                    System.out.println(taskManager.changePriority(Integer.parseInt(b.readLine()), Long.parseLong(b.readLine())));
                    break;
                case "CLEAR":
                    System.out.println("CLEARED");
                    taskManager.Clear();
                    break;
                default:
                    return;
            }

        }

    }

}