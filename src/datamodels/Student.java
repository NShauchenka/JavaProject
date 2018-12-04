
package datamodels;

import static controllers.Application.getAUDIT_LOGGER;
import static controllers.Application.getDEBUG_LOGGER;
import interfaces.ICourse;
import interfaces.IStudent;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person implements IStudent {

    private float gpa;
    private LocalDate dateOfGraduation;
    private int studentID;
    private ArrayList<Course> listOfCourses  = new ArrayList<Course>();
    
    public Student(){
        getAUDIT_LOGGER().info("Created new Student: ");
    }
    @Override
    public int getStudentID() {
        return studentID;
    }

    @Override
    public void setStudentID(int p_studentID) {
        studentID = p_studentID;
        getDEBUG_LOGGER().finest("Set Student ID: " + studentID);
    }

    @Override
    public String toString() {
        return "Student{studentID=" + studentID + ", dateOfGraduation=" + dateOfGraduation + ", gpa=" + gpa + ", listOfCourses=[" + listOfCourses.get(0) + "]}";  
    }

    @Override
    public void setName(String p_name) {
        name = p_name;
        getDEBUG_LOGGER().finest("Set Student Name: " + name);
    }

    @Override
    public void setAddress(String p_address) {
        address = p_address;
        getDEBUG_LOGGER().finest("Set Student address: " + address);
    }

    @Override
    public void setDateOfBirth(LocalDate p_dateOfBirth) {
        dateOfBirth = p_dateOfBirth;
        getDEBUG_LOGGER().finest("Set Student DOB: " + dateOfBirth.toString());
    }

    @Override
    public void setListOfCourses(ArrayList<Course> p_listOfCourses) {
        listOfCourses = p_listOfCourses;
        for(int i = 0; i < listOfCourses.size(); i++){
            getDEBUG_LOGGER().finest("Set Course: " + listOfCourses.get(i).toString());
        }
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setGPA(float p_gpa) {
        gpa = p_gpa;
        getDEBUG_LOGGER().finest("Set Student GPA: " + Float.toString(gpa));
    }

    @Override
    public void setDateOfGraduation(LocalDate p_dateOfGraduation) {
        dateOfGraduation = p_dateOfGraduation;
        getDEBUG_LOGGER().finest("Set Student Date Of Graduation: " + dateOfGraduation.toString());
    }

    @Override
    public float getGPA() {
        return gpa;
    }

    @Override
    public LocalDate getDateOfGraduation() {
        return dateOfGraduation;
    }

    @Override
    public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }

}
    
   