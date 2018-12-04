
package interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import datamodels.Course;
/**
   Interface for Student
 * @author nastassiashauchenka
 */
public interface IStudent {
    public void setGPA (float p_gpa);
    public void setDateOfGraduation (LocalDate p_dateOfGraduation);
    public void setStudentID (int p_studentID);
    public void setListOfCourses (ArrayList<Course> listOfCourses);
    public float getGPA ();
    public LocalDate getDateOfGraduation ();
    public int getStudentID ();
    public ArrayList<Course> getListOfCourses ();
}
