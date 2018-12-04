/*
 * Listens for events on the menu form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 */
package controllers;

import static controllers.Application.getDEBUG_LOGGER;
import datacontainers.ClassroomDC;
import datacontainers.CourseDC;
import datacontainers.FacultyDC;
import datacontainers.StudentDC;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import utilities.io.ClassroomIO;
import utilities.io.CourseIO;
import utilities.io.FacultyIO;
import utilities.io.StudentIO;
import utilities.io.ClassroomJSONiO;
import utilities.io.CourseJSONiO;
import utilities.io.FacultyJSONiO;
import utilities.io.StudentJSONiO;
import view.MainMenu;

public class MainMenuController implements ActionListener {

    // File location
    String fileLocation;
    
    /**
     * Constructor
     * @param fileLocation 
     */
    public MainMenuController(String fileLocation) {
        this.fileLocation = fileLocation;
    }
    
    // The data models are instantiated here and passed to the 
    // constructors for the controllers
    ClassroomDC classroomDao = new ClassroomDC();
    CourseDC courseDao = new CourseDC();
    FacultyDC facultyDao = new FacultyDC();
    StudentDC studentDao = new StudentDC();
    CourseDC offeredCourseDao = new CourseDC();
   
    // The main menu form gets created here. Notice it takes this controller object
    // as an argument to the constructor
    private MainMenu mainMenu = new MainMenu(this);

    /**
     * The ActionListener interface contains a single method, actionPerformed
     */
    public void actionPerformed(java.awt.event.ActionEvent event) {

        //  Figure out which button was clicked
        String menuItemClicked = event.getActionCommand();

        // create the controller which will open the correct form (refer to the controller constructor
        // methods do determine which Dao classes need to be passed to the controller constructors)
        if (menuItemClicked.equals("Add Classroom")) {
            InputClassroomFormController inputController = new InputClassroomFormController(classroomDao, fileLocation);
        } else if (menuItemClicked.equals("List Classrooms")) {
            ReportClassroomController reportController = new ReportClassroomController(classroomDao);
        } else if (menuItemClicked.equals("Add Course")) {
            InputCourseController inputController = new InputCourseController(courseDao, classroomDao, fileLocation);
        } else if (menuItemClicked.equals("List Courses")) {
            ReportCourseController reportController = new ReportCourseController(courseDao);
        } else if (menuItemClicked.equals("Add Faculty")) {
            InputFacultyFormController inputController = new InputFacultyFormController(facultyDao, offeredCourseDao, fileLocation);
        } else if (menuItemClicked.equals("List Faculty")) {
            ReportFacultyController reportController = new ReportFacultyController(facultyDao);
        } else if (menuItemClicked.equals("Add Student")) {
            InputStudentFormController inputController = new InputStudentFormController(studentDao, courseDao, fileLocation);
        } else if (menuItemClicked.equals("List Students")) {
            ReportStudentController reportController = new ReportStudentController(studentDao);
        } else if (menuItemClicked.equals("Exit")) {
            System.exit(0);
        }else if (menuItemClicked.equals("Load Data")) {
            classroomDao.setListOfClassrooms(ClassroomIO.readXMLFile(fileLocation));
            courseDao.setListOfCourses(CourseIO.readXMLFile(fileLocation));
            facultyDao.setListOfFaculties(FacultyIO.readXMLFile(fileLocation));
            studentDao.setListOfStudents(StudentIO.readXMLFile(fileLocation));
            classroomDao.setListOfClassrooms(ClassroomJSONiO.readJSONFile(fileLocation));
            courseDao.setListOfCourses(CourseJSONiO.readJSONFile(fileLocation));
            facultyDao.setListOfFaculties(FacultyJSONiO.readJSONFile(fileLocation));
            studentDao.setListOfStudents(StudentJSONiO.readJSONFile(fileLocation));
        } else if (menuItemClicked.equals("Log Warning")) {
            getDEBUG_LOGGER().setLevel(Level.WARNING);
        } else if (menuItemClicked.equals("Log Info")) {
            getDEBUG_LOGGER().setLevel(Level.INFO);
        } else if (menuItemClicked.equals("Log Severe")) {
            getDEBUG_LOGGER().setLevel(Level.SEVERE);
        } else if (menuItemClicked.equals("Log All")) {
            getDEBUG_LOGGER().setLevel(Level.ALL);
        }      
        
    }

    // Getter used in the Application.java class
    public MainMenu getMainMenu() {
        return mainMenu;
    }
}

