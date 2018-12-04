/*
 *  This Class contains methods to write out the classroom objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import static controllers.Application.getDEBUG_LOGGER;
import datacontainers.ClassroomDC;
import datacontainers.CourseDC;
import datamodels.Classroom;
import datamodels.Course;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class CourseIO {

    /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods
     */
    private CourseIO() {
    }

    /**
     * Writes out the classroom data in XML format containing all classrooms in
     * the classroom data container
     */
    public static void writeXMLFile(String fileLocation, CourseDC courseDataContainer) {

        // get a document builder factory
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            // get a document builder from the factory
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            // create an instance of the document model
            Document doc = docBuilder.newDocument();

            // create the root element <list_of_classrooms> and append to document
            Element root = doc.createElement("list_of_cources");
            doc.appendChild(root);

            // Loop through the array list of classrooms and create the classroom elements of the xml file
            for (Course course : courseDataContainer.getListOfCourses()) {

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

                root.appendChild(courseElement);

            }

            // use default xml formatting in the file
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true);

            // open the output stream
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File(fileLocation + "course.xml")), format);

            // write out the object
            serializer.serialize(doc);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        }
    }  
    public static ArrayList<Course> readXMLFile(String fileLocation) {

        ArrayList<Course> listOfCourses = new ArrayList<>();

        try {

            // Get the factory instance
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            //Using factory, get an instance of document builder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //parse using builder to get document representation of the XML file
            Document xmlDocument = documentBuilder.parse(fileLocation + "course.xml");

            //get the root elememt (list_of_classrooms)
            Element list_of_courses = xmlDocument.getDocumentElement();

            //retrieve the list of classrooms from the root of the document
            NodeList courseList = list_of_courses.getElementsByTagName("course");

            //loop through the list of classrooms and create classroom objects            
            for (int i = 0; i < courseList.getLength(); i++) {

                //get a classroom element from the list
                Element courseElement = (Element) courseList.item(i);

                //get the data for the course, we retrieve node lists for convenience
                //but we will only have one of each so we will use the first element in 
                // each list
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
                
                //int classroomCapacity = Integer.valueOf(classroomCapacityList.item(0).getTextContent());

                //newclassroom.setCapacity(classroomCapacity);
                
                newcourse.setClassroom(newclassroom);
                
                //add the classroom to the data model arraylist
                listOfCourses.add(newcourse);
            }
        } // if wrong file name is entered, let Main Menu handle it
        catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        } finally {
            return listOfCourses;
        }
    }    
}
