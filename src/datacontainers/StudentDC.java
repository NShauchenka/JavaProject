/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacontainers;
import java.util.*;
import datamodels.Student;


/**
 *
 * @author nastassiashauchenka
 */
public class StudentDC {
    
    private ArrayList<Student> listOfStudents = new ArrayList<>();
    
    public StudentDC() {
    
}
    
public ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

         public void setListOfStudents(ArrayList<Student> listOfStudents) {
         this.listOfStudents = listOfStudents;
    }
}