/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.io;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import static controllers.Application.getDEBUG_LOGGER;
import datacontainers.CourseDC;
import datacontainers.FacultyDC;
import datamodels.Classroom;
import datamodels.Course;
import datamodels.Faculty;
import interfaces.ICourse;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author nastassiashauchenka
 */
public class FacultyIO {
     /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods
     */
    private FacultyIO() {
    }

    /**
     * Writes out the classroom data in XML format containing all classrooms in
     * the classroom data container
     */
    public static void writeXMLFile(String fileLocation, FacultyDC facultyDataContainer) {

        // get a document builder factory
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            // get a document builder from the factory
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            // create an instance of the document model
            Document doc = docBuilder.newDocument();

            // create the root element <list_of_classrooms> and append to document
            Element root = doc.createElement("list_of_faculties");
            doc.appendChild(root);

            // Loop through the array list of classrooms and create the classroom elements of the xml file
            for (Faculty faculty : facultyDataContainer.getListOfFaculties()) {

                Element facultyElement = doc.createElement("faculty");
              
                Element fucultyNameElement = doc.createElement("faculty_Name");
                Text facultyNameText = doc.createTextNode(faculty.getName());
                fucultyNameElement.appendChild(facultyNameText);
                facultyElement.appendChild(fucultyNameElement);

                Element fucultyAddressElement = doc.createElement("faculty_Address");
                Text facultyAddressText = doc.createTextNode(faculty.getAddress());
                fucultyAddressElement.appendChild(facultyAddressText);
                facultyElement.appendChild(fucultyAddressElement);
                
                Element facultyDOBElement = doc.createElement("faculty_DOB");
                Text facultyDOBText = doc.createTextNode(faculty.getDateOfBirth().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                facultyDOBElement.appendChild(facultyDOBText);
                facultyElement.appendChild(facultyDOBElement);

                Element facultySalaryElement = doc.createElement("faculty_Salary");
                Text facultySalaryText = doc.createTextNode(String.valueOf(faculty.getSalary()));
                facultySalaryElement.appendChild(facultySalaryText);
                facultyElement.appendChild(facultySalaryElement);
                
                Element facultyDateOfHireElement = doc.createElement("faculty_DateOfHire");
                Text facultyDateOfHireText = doc.createTextNode(faculty.getDateOfHire().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                facultyDateOfHireElement.appendChild(facultyDateOfHireText);
                facultyElement.appendChild(facultyDateOfHireElement);
                
                Element fucultyStatusElement = doc.createElement("faculty_Status");
                Text facultyStatusText = doc.createTextNode(faculty.getStatus());
                fucultyStatusElement.appendChild(facultyStatusText);
                facultyElement.appendChild(fucultyStatusElement);
                
                for (ICourse course : faculty.getListOfCourses()) {

                    Element courseElement = doc.createElement("course");

                    Element courseIdElement = doc.createElement("course_Id");
                    Text courseIdText = doc.createTextNode(course.getCourseID());
                    courseIdElement.appendChild(courseIdText);
                    courseElement.appendChild(courseIdElement);

                    Element courseNameElement = doc.createElement("course_Name");
                    Text courseNameText = doc.createTextNode(course.getCourseName());
                    courseNameElement.appendChild(courseNameText);
                    courseElement.appendChild(courseNameElement);

                    Element classroomElement = doc.createElement("classroom");

                    Element roomNumberElement = doc.createElement("room_number");
                    Text roomNumberText = doc.createTextNode(course.getClassroom().getRoomNumber());
                    roomNumberElement.appendChild(roomNumberText);
                    classroomElement.appendChild(roomNumberElement);

                    Element roomtypeElement = doc.createElement("room_type");
                    Text roomtypetText = doc.createTextNode(course.getClassroom().getRoomType().toString());
                    roomtypeElement.appendChild(roomtypetText);
                    classroomElement.appendChild(roomtypeElement);

                    Element capacityElement = doc.createElement("room_capacity");
                    Text capacityText = doc.createTextNode(String.valueOf(course.getClassroom().getCapacity()));
                    roomNumberElement.appendChild(capacityText);
                    classroomElement.appendChild(capacityElement);

                    courseElement.appendChild(classroomElement);

                    facultyElement.appendChild(courseElement);
                }
 
                root.appendChild(facultyElement);

            }

            // use default xml formatting in the file
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true);

            // open the output stream
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File(fileLocation + "faculty.xml")), format);

            // write out the object
            serializer.serialize(doc);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        }
    } 
    
    public static ArrayList<Faculty> readXMLFile(String fileLocation) {

        ArrayList<Faculty> listOfFaculties = new ArrayList<>();

        try {

            // Get the factory instance
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            //Using factory, get an instance of document builder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //parse using builder to get document representation of the XML file
            Document xmlDocument = documentBuilder.parse(fileLocation + "faculty.xml");

            //get the root elememt (list_of_classrooms)
            Element list_of_faculties = xmlDocument.getDocumentElement();

            //retrieve the list of classrooms from the root of the document
            NodeList facultiesList = list_of_faculties.getElementsByTagName("faculty");

            //loop through the list of classrooms and create classroom objects            
            for (int i = 0; i < facultiesList.getLength(); i++) {

                //get a classroom element from the list
                Element facultyElement = (Element) facultiesList.item(i);

                //get the data for the course, we retrieve node lists for convenience
                //but we will only have one of each so we will use the first element in 
                // each list
                NodeList facultyNameList = facultyElement.getElementsByTagName("faculty_Name");
                NodeList facultyAddressList = facultyElement.getElementsByTagName("faculty_Address");
                NodeList facultyDOBList = facultyElement.getElementsByTagName("faculty_DOB");
                NodeList facultySalaryList = facultyElement.getElementsByTagName("faculty_Salary");
                NodeList facultyDateOfHireList = facultyElement.getElementsByTagName("faculty_DateOfHire");
                NodeList facultyStatusList = facultyElement.getElementsByTagName("faculty_Status");
                Faculty newfaculty = new Faculty();
                String facultyName = facultyNameList.item(0).getTextContent();
                newfaculty.setName(facultyName);
                String facultyAddress = facultyAddressList.item(0).getTextContent();
                newfaculty.setAddress(facultyAddress);
                LocalDate facultyDOB = LocalDate.parse(facultyDOBList.item(0).getTextContent(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                newfaculty.setDateOfBirth(facultyDOB);
                double facultySalary = Double.valueOf(facultySalaryList.item(0).getTextContent());
                newfaculty.setSalary(facultySalary);
                LocalDate facultyDOH = LocalDate.parse(facultyDateOfHireList.item(0).getTextContent(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                newfaculty.setDateOfHire(facultyDOH);
                String facultyStatus = facultyStatusList.item(0).getTextContent();
                newfaculty.setStatus(facultyStatus);               
                NodeList facultyCourseList = facultyElement.getElementsByTagName("course");
                ArrayList<Course> newlistOfCourses = new ArrayList<>();
                for (int j = 0; j < facultyCourseList.getLength(); j++) {
                    Element courseElement = (Element) facultyCourseList.item(j);
                    NodeList courseIdList = courseElement.getElementsByTagName("course_Id");
                    NodeList courseNameList = courseElement.getElementsByTagName("course_Name");
                    NodeList courseClassroomList = courseElement.getElementsByTagName("classroom");
                    Element courseClassroom = (Element)courseClassroomList.item((0));
                    NodeList classroomNumberList = courseClassroom.getElementsByTagName("room_number");
                    NodeList classroomTypeList = courseClassroom.getElementsByTagName("room_type");
                    NodeList classroomCapacityList = courseClassroom.getElementsByTagName("room_capacity");

                    //create a classroom
                    Course newcourse = new Course();

                    //retrieve the first element from the roomnumber list and get its content (text value)
                    String coursId = courseIdList.item(0).getTextContent();

                    //set the value of room number in the classroom
                    newcourse.setCourseID(coursId);

                    //retrieve the first element from the roomtype list and get its content (text value)
                    String coursName = courseNameList.item(0).getTextContent();

                    //set the value of type in the classroom object
                    newcourse.setCourseName(coursName);                

                    Classroom newclassroom = new Classroom();
                
                    String classroomNumber = classroomNumberList.item(0).getTextContent();

                    newclassroom.setRoomNumber(classroomNumber);

                    String classroomType = classroomTypeList.item(0).getTextContent();

                    newclassroom.setRoomType(classroomType);

                    int classroomCapacity = Integer.valueOf(classroomCapacityList.item(0).getTextContent());

                    newclassroom.setCapacity(classroomCapacity);

                    newcourse.setClassroom(newclassroom);

                    //add the classroom to the data model arraylist
                    newlistOfCourses.add(newcourse);
                    
                }
                newfaculty.setListOfCourses(newlistOfCourses);
                
                //add the classroom to the data model arraylist
                listOfFaculties.add(newfaculty);
            }
        } // if wrong file name is entered, let Main Menu handle it
        catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        } finally {
            return listOfFaculties;
        }
    }        
}
