/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eje1;
import java.util.Comparator;
/**
 *
 * @author Usuario
 */
public class myComparator implements Comparator<Student> {
    @Override
    public int compare(Student o2, Student o1) {
        int result = Double.compare(o1.getPappi(), o2.getPappi());
        if(result!= 0)
            return result;
        
        result = Integer.compare(o1.getValidCredits(), o2.getValidCredits());
        if(result!= 0)
            return result;
        
        return o2.getUser().compareTo(o1.getUser());
    }
}