
package datamodels;

import static controllers.Application.getAUDIT_LOGGER;
import static controllers.Application.getDEBUG_LOGGER;
import interfaces.ICourse;
import interfaces.IFaculty;
import java.time.LocalDate;
import java.util.ArrayList;

public class Faculty extends Person implements IFaculty {
    
    private LocalDate dateOfHire;
    private double salary;
    private String status;
    private ArrayList<Course> listOfCourses  = new ArrayList<Course>();

    public Faculty(){
        getAUDIT_LOGGER().info("Created new Faculty: ");
    }
    @Override
    public void setListOfCourses(ArrayList<Course> p_listOfCourses) {
        listOfCourses = p_listOfCourses;
        for(int i = 0; i < listOfCourses.size(); i++){
            getDEBUG_LOGGER().finest("Set Course: " + listOfCourses.get(i).toString());
        }
    }    
    
    @Override
    public void setDateOfHire(LocalDate p_dateOfHire) {
        dateOfHire = p_dateOfHire;
        getDEBUG_LOGGER().finest("Set Date of Hire: " + dateOfHire.toString());
    }

    
    @Override
    public void setSalary(double p_salary) {
        salary = p_salary;
        getDEBUG_LOGGER().finest("Set Salary: " + Double.toString(salary));
    }

    
    @Override
    public void setStatus(String p_status) {
        status = p_status;
        getDEBUG_LOGGER().finest("Set Status: " + status);
    }

    
    @Override
    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

   
    @Override
    public double getSalary() {
        return salary;
    }

    
    @Override
    public String getStatus() {
        return status;
    }

    
    @Override
    public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }

    @Override
    public void setName(String p_name) {
        name = p_name;
    }

    @Override
    public void setAddress(String p_address) {
        address = p_address;
    }

    @Override
    public void setDateOfBirth(LocalDate p_dateOfBirth) {
        dateOfBirth = p_dateOfBirth;
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
    public String toString() {
        return "Faculty{dateOfHire=" + dateOfHire + ", salary=" + salary + ", status=" + status + ", listOfCourses=[" + listOfCourses.get(0).toString()  + "]}";
}

   
}
