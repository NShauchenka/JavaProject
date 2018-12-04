
package datamodels;

import static controllers.Application.getAUDIT_LOGGER;
import static controllers.Application.getDEBUG_LOGGER;
import interfaces.ICourse;

public class Course implements ICourse {
    
    private String courseID;
    private String courseName;
    private Classroom classroom;

    public Course(){
        getAUDIT_LOGGER().info("Created new Course: ");
    }
    
    public void setCourseID(String p_courseID) {
        courseID = p_courseID;
        getDEBUG_LOGGER().finest("Set Course ID: " + courseID);
    }

    public void setCourseName(String p_courseName) {
        courseName = p_courseName;
        getDEBUG_LOGGER().finest("Set Course Name: " + courseName);
    }

   
    public void setClassroom(Classroom p_classroom) {
        classroom = p_classroom;
        getDEBUG_LOGGER().finest("Set Classroom For Course: " + classroom.toString());
    }

    
    public String getCourseID() {
        return courseID;
    }

    
    public String getCourseName() {
        return courseName;
    }

    
    public Classroom getClassroom() {
        return classroom;
    }
    
    @Override
    public String toString() {
        return "Course{courseID=" +courseID + ", courseName=" +courseName + ", classroom=" + classroom.toString() + "}";
    }
}
