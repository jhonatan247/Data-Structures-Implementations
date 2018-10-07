/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eje5;

/**
 *
 * @author Usuario
 */
public class Request implements Comparable {
    private String document;
    private String request;
    private double PAPA;
    private int peso;
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public double getPAPA() {
        return PAPA;
    }

    public void setPAPA(double PAPA) {
        this.PAPA = PAPA;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public Request(String document, String request, double PAPA, int peso) {
        this.document = document;
        this.request = request;
        this.PAPA = PAPA;
        this.peso = peso;
    }
    public void print(){
        System.out.println(this.document+" "+this.request);
    }
    @Override
    public int compareTo(Object t) {
        Request s = (Request)t;
        //System.out.println(this.getDocument()+" "+s.getDocument());
        
        if(s.getPAPA()>this.PAPA) return 1;
        if(s.getPAPA()==this.PAPA){
            if(this.peso<s.getPeso()) return -1;
            return 1;
        }
        return -1;
        
    }
}

