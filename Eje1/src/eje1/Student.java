/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eje1;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Student {
    
    private ArrayList<Subject> subjects;
    private String user;
    private double pappi; 
    private long totalCredits;
    private long validCredits;

    public Student(String user) {
        this.user = user;
        subjects = new ArrayList();
        pappi=0;
        totalCredits = 0;
        validCredits = 0;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public long getTotalCredits() {
        return totalCredits;
    }

    public long getValidCredits() {
        return validCredits;
    }

    public void setValidCredits(long validCredits) {
        this.validCredits += validCredits;
    }

    public void setTotalCredits(long totalCredits) {
        this.totalCredits += totalCredits;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getPappi() {
        return pappi;
    }

    public void setPappi(double pappi) {
        this.pappi += pappi;
    }
    
    public void calculatePappi(){
        DecimalFormat df = new DecimalFormat("#.000000");
        
        pappi = Double.parseDouble(df.format(pappi / totalCredits));
    }
    
    
    
}
