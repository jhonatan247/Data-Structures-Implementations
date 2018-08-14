/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eje1;

/**
 *
 * @author Usuario
 */
public class Subject {
    private int credits;
    private double calification;

    public Subject(int credits, double calification) {
        this.credits = credits;
        this.calification = calification;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }
    
    public double calculateTotal(){
        return credits*calification;
    }
    
}
