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
import datacontainers.StudentDC;
import datamodels.Classroom;
import datamodels.Course;
import datamodels.Faculty;
import datamodels.Student;
import interfaces.ICourse;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author nastassiashauchenka
 */
public class StudentIO {
      /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods
     */
    private StudentIO() {
    }

    /**
     * Writes out the classroom data in XML format containing all classrooms in
     * the classroom data container
     */
    public static void writeXMLFile(String fileLocation, StudentDC studentDataContainer) {

        // get a document builder factory
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            // get a document builder from the factory
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            // create an instance of the document model
            Document doc = docBuilder.newDocument();

            // create the root element <list_of_classrooms> and append to document
            Element root = doc.createElement("list_of_students");
            doc.appendChild(root);

            // Loop through the array list of classrooms and create the classroom elements of the xml file
            for (Student student : studentDataContainer.getListOfStudents()) {

                Element studentElement = doc.createElement("student");
              
                Element studentIdElement = doc.createElement("student_Id");
                Text studentIdText = doc.createTextNode(String.valueOf(student.getStudentID()));
                studentIdElement.appendChild(studentIdText);
                studentElement.appendChild(studentIdElement);

                Element studentNameElement = doc.createElement("student_Name");
                Text studentNameText = doc.createTextNode(student.getName());
                studentNameElement.appendChild(studentNameText);
                studentElement.appendChild(studentNameElement);
                
                Element studentAddressElement = doc.createElement("student_Address");
                Text studentAddressText = doc.createTextNode(student.getAddress());
                studentAddressElement.appendChild(studentAddressText);
                studentElement.appendChild(studentAddressElement);
                
                Element studentDOBElement = doc.createElement("student_DOB");
                Text studentDOBText = doc.createTextNode(student.getDateOfBirth().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                studentDOBElement.appendChild(studentDOBText);
                studentElement.appendChild(studentDOBElement);
                
                Element studentGPAElement = doc.createElement("student_GPA");
                Text studentGPAText = doc.createTextNode(String.valueOf(student.getGPA()));
                studentGPAElement.appendChild(studentGPAText);
                studentElement.appendChild(studentGPAElement);
                
                Element studentDateOfGraduationElement = doc.createElement("student_DateOfGraduation");
                Text studentDateOfGraduationText = doc.createTextNode(student.getDateOfGraduation().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                studentDateOfGraduationElement.appendChild(studentDateOfGraduationText);
                studentElement.appendChild(studentDateOfGraduationElement);
                
                for (ICourse course : student.getListOfCourses()) {

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

                    studentElement.appendChild(courseElement);
                }
   
                root.appendChild(studentElement);
            }

            // use default xml formatting in the file
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true);

            // open the output stream
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File(fileLocation + "student.xml")), format);

            // write out the object
            serializer.serialize(doc);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        }
    } 
    public static ArrayList<Student> readXMLFile(String fileLocation) {

        ArrayList<Student> listOfStudents = new ArrayList<>();

        try {

            // Get the factory instance
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            //Using factory, get an instance of document builder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //parse using builder to get document representation of the XML file
            Document xmlDocument = documentBuilder.parse(fileLocation + "student.xml");

            //get the root elememt (list_of_classrooms)
            Element list_of_students = xmlDocument.getDocumentElement();

            //retrieve the list of classrooms from the root of the document
            NodeList studentsList = list_of_students.getElementsByTagName("student");

            //loop through the list of classrooms and create classroom objects            
            for (int i = 0; i < studentsList.getLength(); i++) {

                //get a classroom element from the list
                Element studentElement = (Element) studentsList.item(i);

                //get the data for the course, we retrieve node lists for convenience
                //but we will only have one of each so we will use the first element in 
                // each list
                NodeList studentIDList = studentElement.getElementsByTagName("student_Id");
                NodeList studentNameList = studentElement.getElementsByTagName("student_Name");
                NodeList studentAddressList = studentElement.getElementsByTagName("student_Address");
                NodeList studentDOBList = studentElement.getElementsByTagName("student_DOB");
                NodeList studentGPAList = studentElement.getElementsByTagName("student_GPA");
                NodeList studentDOGList = studentElement.getElementsByTagName("student_DateOfGraduation");
                Student newstudent = new Student();
                int studentID = Integer.valueOf(studentIDList.item(0).getTextContent());
                newstudent.setStudentID(studentID);
                String studentName = studentNameList.item(0).getTextContent();
                newstudent.setName(studentName);
                String studentAddress = studentAddressList.item(0).getTextContent();
                newstudent.setAddress(studentAddress);
                LocalDate studentDOB = LocalDate.parse(studentDOBList.item(0).getTextContent(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                newstudent.setDateOfBirth(studentDOB);
                float studentGPA = Float.valueOf(studentGPAList.item(0).getTextContent());
                newstudent.setGPA(studentGPA);
                LocalDate studentDOG = LocalDate.parse(studentDOGList.item(0).getTextContent(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                newstudent.setDateOfGraduation(studentDOG);               
                NodeList studentCourseList = studentElement.getElementsByTagName("course");
                ArrayList<Course> newlistOfCourses = new ArrayList<>();
                for (int j = 0; j < studentCourseList.getLength(); j++) {
                    Element courseElement = (Element) studentCourseList.item(j);
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
                newstudent.setListOfCourses(newlistOfCourses);
                
                //add the classroom to the data model arraylist
                listOfStudents.add(newstudent);
            }
        } // if wrong file name is entered, let Main Menu handle it
        catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        } finally {
            return listOfStudents;
        }
    }        
}
