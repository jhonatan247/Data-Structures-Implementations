package eje12;
import java.util.*;
import java.io.*;

class Student implements Comparable {

    String apellido;
    String nombre;
    String documento;
    byte edad;
    String papa;

    public Student(String documento, String nombre, String apellido, byte edad, String papa) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.papa = papa;
    }

    @Override
    public boolean equals(Object o) {
        return documento.equals(((Student) o).documento);
    }

    @Override
    public int compareTo(Object o) {
        Student os = (Student) o;
        int compare = apellido.compareTo(os.apellido);
        if (compare == 0) {
            compare = nombre.compareTo(os.nombre);
            if (compare == 0) {
                compare = os.papa.compareTo(papa);
                if (compare == 0) {
                    compare = documento.compareTo(os.documento);
                    int i = 1;
                    int a = 4/(i-1);
                }
            }
        }
        return compare;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(documento);
        s.append(" ");
        s.append(nombre);
        s.append(" ");
        s.append(apellido);
        s.append(" ");
        s.append(edad);
        s.append(" ");
        s.append(papa);
        return s.toString();
    }
}

class MapNode {

    PriorityQueue<Student> students;

    public MapNode(Student s) {
        students = new PriorityQueue();
        students.add(s);
    }

    public void Add(Student s) {
        students.add(s);
    }

}

class MapController<T> {

    TreeMap<T, MapNode> map;

    public MapController() {
        map = new TreeMap();
    }

    public void add(T key, Student value) {
        MapNode node = map.get(key);
        if (node != null) {
            node.Add(value);
        } else {
            map.put(key, new MapNode(value));
        }
    }

    public Student get(T key) {
        MapNode node = map.get(key);
        if (node != null) {
            return node.students.peek();
        } else {
            return null;
        }
    }

    public boolean remove(T key, Student value) {
        MapNode node = map.get(key);
        if (node != null) {
            node.students.remove(value);
            if (node.students.isEmpty()) {
                map.remove(key);
            }
            return true;
        } else {
            return false;
        }
    }
}

public class Eje12 {

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input;
        String line;
        TreeMap<String, Student> documentIndex = new TreeMap();
        MapController<String> nameIndex = new MapController();
        MapController<String> lastNameIndex = new MapController();
        MapController<String> papaIndex = new MapController();
        MapController<Byte> ageIndex = new MapController();
        Student current;
        String value;
        String key;
        StringBuilder output = new StringBuilder();
        while (true) {
            line = b.readLine();
            if (line == null) {
                System.out.println(output.toString());
                return;
            }
            input = new StringTokenizer(line);
            value = input.nextToken();
            switch (value) {
                case "CREATE":
                    current = new Student(input.nextToken(), input.nextToken(),
                            input.nextToken(), Byte.parseByte(input.nextToken()), input.nextToken());
                    documentIndex.put(current.documento, current);
                    nameIndex.add(current.nombre, current);
                    lastNameIndex.add(current.apellido, current);
                    papaIndex.add(current.papa, current);
                    ageIndex.add(current.edad, current);
                    break;
                case "READ":
                    current = null;
                    value = input.nextToken();
                    key = input.nextToken();
                    switch (value) {
                        case "ID":
                            current = documentIndex.get(key);
                            break;
                        case "NOMBRE":
                            current = nameIndex.get(key);
                            break;
                        case "APELLIDO":
                            current = lastNameIndex.get(key);
                            break;
                        case "EDAD":
                            current = ageIndex.get(Byte.parseByte(key));
                            break;
                        case "PAPA":
                            current = papaIndex.get(key);
                            break;
                    }
                    if (current != null) {
                        output.append(current.toString());
                        output.append("\n");
                    } else {
                        output.append("no existe estudiante con ");
                        output.append(value);
                        output.append(" ");
                        output.append(key);
                        output.append("\n");
                    }
                    break;
                case "UPDATE":
                    current = documentIndex.get(input.nextToken());
                    if (current != null) {
                        value = input.nextToken();
                        key = input.nextToken();
                        switch (value) {
                            case "NOMBRE":
                                nameIndex.remove(current.nombre, current);
                                current.nombre = key;
                                nameIndex.add(key, current);
                                
                                lastNameIndex.remove(current.apellido, current);
                                lastNameIndex.add(current.apellido, current);
                                papaIndex.remove(current.papa, current);
                                papaIndex.add(current.papa, current);
                                ageIndex.remove(current.edad, current);
                                ageIndex.add(current.edad, current);
                                break;
                            case "APELLIDO":
                                lastNameIndex.remove(current.apellido, current);
                                current.apellido = key;
                                lastNameIndex.add(current.apellido, current);
                                
                                nameIndex.remove(current.nombre, current);
                                nameIndex.add(current.nombre, current);
                                papaIndex.remove(current.papa, current);
                                papaIndex.add(current.papa, current);
                                ageIndex.remove(current.edad, current);
                                ageIndex.add(current.edad, current);
                                break;
                            case "EDAD":
                                ageIndex.remove(current.edad, current);
                                current.edad = Byte.parseByte(key);
                                ageIndex.add(current.edad, current);
                                
                                
                                nameIndex.remove(current.nombre, current);
                                nameIndex.add(current.nombre, current);
                                lastNameIndex.remove(current.apellido, current);
                                lastNameIndex.add(current.apellido, current);
                                papaIndex.remove(current.papa, current);
                                papaIndex.add(current.papa, current);
                                break;
                            case "PAPA":
                                papaIndex.remove(current.papa, current);
                                current.papa = key;
                                papaIndex.add(current.papa, current);
                                
                                
                                nameIndex.remove(current.nombre, current);
                                nameIndex.add(current.nombre, current);
                                lastNameIndex.remove(current.apellido, current);
                                lastNameIndex.add(current.apellido, current);
                                ageIndex.remove(current.edad, current);
                                ageIndex.add(current.edad, current);
                                break;
                        }
                    }
                    break;
                case "DELETE":
                    current = documentIndex.remove(input.nextToken());
                    if (current != null) {
                        nameIndex.remove(current.nombre, current);
                        lastNameIndex.remove(current.apellido, current);
                        papaIndex.remove(current.papa, current);
                        ageIndex.remove(current.edad, current);
                    }
                    break;
            }
        }

    }

}
