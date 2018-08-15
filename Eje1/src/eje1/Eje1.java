/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eje1;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Eje1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList();
        Scanner input = new Scanner(System.in);
        int totalStudents = input.nextInt();
        int totalSubjects;
        String nextS;
        int nextI;
        double nextD;
        for(int i = 0; i<totalStudents; i++){
            students.add(new Student(input.next()));
            ArrayList<Subject> subjects = new ArrayList();
            totalSubjects = input.nextInt();
            for(int f = 0; f< totalSubjects; f++){
                nextI = input.nextInt();
                nextS = input.next();
                nextD = nextS.equals("CANCELADO")? 0: Double.parseDouble(nextS);
                students.get(i).setTotalCredits(nextI);
                students.get(i).setValidCredits(nextS.equals("CANCELADO")? 0: nextI);
                students.get(i).setPappi(nextD*nextI);
                subjects.add(new Subject(nextI, nextD));
            }
            students.get(i).setSubjects(subjects);
            students.get(i).calculatePappi();
        }
        Collections.sort(students, new myComparator());
        
        for(int i = 0; i<totalStudents; i++){
            System.out.println(students.get(i).getUser());
        }
        
    }
    
}
