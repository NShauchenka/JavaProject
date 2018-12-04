/*
 * The InputCourseController class contains the following:
 *
 * 1) an instance of the classroom data model which is used to populate the 
 *    list of classrooms on the form. (passed in via the constructor)
 * 2) an instance of the offered course data model which is used to store any 
 *    offered course objects created (passed in via the constructor)
 * 3) an instance of the offered course input form. (created here)
 *
 * Listens for events on the input form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed" - this method contains all the logic to process the data
 * on the form, as well as several other events
 */
package controllers;

import com.sun.media.sound.InvalidDataException;
import datacontainers.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datacontainers.ClassroomDC;
import datacontainers.CourseDC;
import datamodels.Classroom;
import datamodels.Course;
import exceptionhandlers.ErrorPopup;
import utilities.io.CourseIO;
import utilities.io.CourseJSONiO;
import view.inputforms.CourseInputForm;

public class InputCourseController implements ActionListener {

    // The data datacontainers needed for this form are passed in via the constructor
    CourseDC CourseDC;
    ClassroomDC ClassroomDC;

    // File location
    String fileLocation;
    
    // The form is created here
    private CourseInputForm form;

    /**
     * Constructor
     *
     * @param CourseDC
     * @param ClassroomDC
     */
    public InputCourseController(CourseDC CourseDC,
            ClassroomDC ClassroomDC, String fileLocation) {

        // Store the passed in data datacontainers
        this.CourseDC = CourseDC;
        this.ClassroomDC = ClassroomDC;
        this.fileLocation = fileLocation;

        // Create the form
        form = new CourseInputForm(this);

        // make the form visible
        this.form.setVisible(true);
    }

    InputCourseController(CourseDC courseDao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * actionPerformed method implemented from the ActionListener interface
     *
     * This method figures out which button was clicked based on the text of the
     * button. The button click event is passed in via the ActionEvent object.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // TO-DO
         if (event.getActionCommand().equals("Save")) {
            this.saveData();
        } else if (event.getActionCommand().equals("Clear")) {
            this.clearForm();
        } else if (event.getActionCommand().equals("Close")) {
            this.closeForm();
        }
    }

    /**
     * Private method called from the actionPerformed method to save the daa
     */
    private void saveData() {
        
        try {

        // Create a new Course object
        Course newCourse = new Course();

        // Retrieve the selected classroom from the drop down list 
        // The objects in the list are stored as generic objects and have to be
        // converted to classroom objects
        
        Classroom classroom = new Classroom();
        
        if(this.ClassroomDC.getListOfClassrooms().size() > 0){
            
            if(this.form.getListOfClassrooms().getSelectedValue() == null)
            {
                throw new InvalidDataException("Please select classroom!");
            }
            else 
            {
                classroom = (Classroom) this.form.getListOfClassrooms().getSelectedValue();
            }
        }
        else
        {
            classroom.setRoomNumber("No Data");
        }


        // Store the classroom
        newCourse.setClassroom(classroom);

        // Retrieve the course id from the form
        String courseid = this.form.getCourseIdField().getText();
        
        if (isEmpty(courseid)) {
                throw new InvalidDataException("Please, enter CourseID!");
            }

            if (!isValid(courseid)) {
                throw new InvalidDataException("Invalid Data!");
            }

        // Set the course id in the object
        newCourse.setCourseID(courseid);

        // Retrieve the course ();
        // Set the course number in the objectname
        String coursename = this.form.getCourseNameField().getText();
        // Setthe course name in the object
        newCourse.setCourseName(coursename);

        // Store the object in the application data model list of courses
        this.CourseDC.getListOfCourses().add(newCourse);
        
        CourseIO.writeXMLFile(fileLocation, CourseDC);        

        CourseJSONiO.writeJSONFile(fileLocation, CourseDC);
        
        } catch (InvalidDataException exception) {
            // Invalid data was found, 
            // No classroom will be saved,
            // create an error popup
            ErrorPopup error = new ErrorPopup(form, exception);
        }

    }

    /**
     * Private method to clear the data
     */
    private void clearForm() {
        this.form.getCourseIdField().setText("");
        this.form.getCourseNameField().setText("");
        this.form.getListOfClassrooms().setSelectedIndex(0);
    }

    /**
     * Private method to close the form
     */
    private void closeForm() {
        this.form.dispose();
    }

    public ClassroomDC getClassroomDC() {
        return ClassroomDC;
    }
    
    private boolean isEmpty(String data) {
        if (data.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
     private boolean isValid(String data) {

        if (data.matches("^[a-zA-Z]{4}[0-9]{3}$")) {
            return true;
        } else {
            return false;
        }
    }

}

