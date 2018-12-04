/*
 *  This Class contains methods to write out the classroom objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import static controllers.Application.getDEBUG_LOGGER;
import datacontainers.ClassroomDC;
import datamodels.Classroom;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ClassroomIO {

    /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods
     */
    private ClassroomIO() {
    }

    /**
     * Writes out the classroom data in XML format containing all classrooms in
     * the classroom data container
     */
    public static void writeXMLFile(String fileLocation, ClassroomDC classroomDataContainer) {

        // get a document builder factory
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            // get a document builder from the factory
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            // create an instance of the document model
            Document doc = docBuilder.newDocument();

            // create the root element <list_of_classrooms> and append to document
            Element root = doc.createElement("list_of_classrooms");
            doc.appendChild(root);

            // Loop through the array list of classrooms and create the classroom elements of the xml file
            for (Classroom classroom : classroomDataContainer.getListOfClassrooms()) {

                Element classroomElement = doc.createElement("classroom");

                Element roomNumberElement = doc.createElement("room_number");
                Text roomNumberText = doc.createTextNode(classroom.getRoomNumber());
                roomNumberElement.appendChild(roomNumberText);
                classroomElement.appendChild(roomNumberElement);

                Element roomtypeElement = doc.createElement("room_type");
                Text roomtypetText = doc.createTextNode(classroom.getRoomType().toString());
                roomtypeElement.appendChild(roomtypetText);
                classroomElement.appendChild(roomtypeElement);
                
                Element capacityElement = doc.createElement("room_capacity");
                Text capacityText = doc.createTextNode(String.valueOf(classroom.getCapacity()));
                capacityElement.appendChild(capacityText);
                classroomElement.appendChild(capacityElement);

                root.appendChild(classroomElement);

            }

            // use default xml formatting in the file
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true);

            // open the output stream
            XMLSerializer serializer = new XMLSerializer(
            new FileOutputStream(new File(fileLocation + "classroom.xml")), format);

            // write out the object
            serializer.serialize(doc);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        }
    }
    
    /**
     * Reads an XML formatted file of classrooms and returns an array list of
     * classrooms
     */
    public static ArrayList<Classroom> readXMLFile(String fileLocation) {

        ArrayList<Classroom> listOfClassrooms = new ArrayList<>();

        try {

            // Get the factory instance
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            //Using factory, get an instance of document builder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //parse using builder to get document representation of the XML file
            Document xmlDocument = documentBuilder.parse(fileLocation + "classroom.xml");

            //get the root elememt (list_of_classrooms)
            Element list_of_classrooms = xmlDocument.getDocumentElement();

            //retrieve the list of classrooms from the root of the document
            NodeList classroomList = list_of_classrooms.getElementsByTagName("classroom");

            //loop through the list of classrooms and create classroom objects            
            for (int i = 0; i < classroomList.getLength(); i++) {

                //get a classroom element from the list
                Element classroomElement = (Element) classroomList.item(i);

                //get the data for the classroom, we retrieve node lists for convenience
                //but we will only have one of each so we will use the first element in 
                // each list
                NodeList roomNumberList = classroomElement.getElementsByTagName("room_number");
                NodeList roomTypeList = classroomElement.getElementsByTagName("room_type");

                //create a classroom
                Classroom newclassroom = new Classroom();

                //retrieve the first element from the roomnumber list and get its content (text value)
                String roomnumber = roomNumberList.item(0).getTextContent();

                //set the value of room number in the classroom
                newclassroom.setRoomNumber(roomnumber);

                //retrieve the first element from the roomtype list and get its content (text value)
                String roomtype = roomTypeList.item(0).getTextContent();

                //set the value of type in the classroom object
                newclassroom.setRoomType(roomtype);                
                
                //add the classroom to the data model arraylist
                listOfClassrooms.add(newclassroom);
            }
        } // if wrong file name is entered, let Main Menu handle it
        catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        } finally {
            return listOfClassrooms;
        }
    }    
}
