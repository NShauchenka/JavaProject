/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacontainers;

import datamodels.Faculty;

import java.util.*;


public class FacultyDC {
    
    private ArrayList<Faculty> listOfFaculties = new ArrayList<>();
    
    public FacultyDC() {
        
    }
        
        public ArrayList<Faculty> getListOfFaculties() {
        return listOfFaculties;
    }

         public void setListOfFaculties(ArrayList<Faculty> listOfFaculties) {
         this.listOfFaculties = listOfFaculties;
    }
         
    }
