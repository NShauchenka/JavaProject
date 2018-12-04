/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacontainers;

import datamodels.Person;

import java.util.*;


public class PersonDC {
    
    private ArrayList<Person> listOfPersons = new ArrayList<>();
    
    public PersonDC() {
        
    }
        
        public ArrayList<Person> getListOfPersons() {
        return listOfPersons;
    }

         public void setListOfCourses(ArrayList<Person> listOfPersons) {
         this.listOfPersons = listOfPersons;
    }
         
    }
