
package interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import datamodels.Course;

/**
 * Interface for Faculty
 * @author nastassiashauchenka
 */
public interface IFaculty {
    public void setDateOfHire (LocalDate p_dateOfHire);
    public void setSalary (double p_salary);
    public void setStatus (String p_status);
    public LocalDate getDateOfHire ();
    public double getSalary ();
    public String getStatus ();
    public ArrayList<Course> getListOfCourses ();
    public void setListOfCourses (ArrayList<Course> listOfCourses);
}
