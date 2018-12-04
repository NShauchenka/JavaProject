/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacontainers;

import datamodels.Course;

import java.util.*;


public class CourseDC {
    
    private ArrayList<Course> listOfCourses = new ArrayList<>();
    
    public CourseDC() {
        
    }
        
        public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }

         public void setListOfCourses(ArrayList<Course> listOfCourses) {
         this.listOfCourses = listOfCourses;
    }
         
    }
    

