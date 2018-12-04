
package interfaces;
import datamodels.Classroom;

/**
 * Interface for Course
 * @author nastassiashauchenka
 */
public interface ICourse {
    public void setCourseID (String p_courseID);
    public void setCourseName (String p_courseName);
    public void setClassroom (Classroom p_classroom);
    public String getCourseID ();
    public String getCourseName ();
    public Classroom getClassroom ();
}
