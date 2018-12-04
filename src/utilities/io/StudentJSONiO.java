/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static controllers.Application.getDEBUG_LOGGER;
import datacontainers.StudentDC;
import datamodels.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author nastassiashauchenka
 */
public class StudentJSONiO {
        /**
     * Writes out the classroom data in JSON format containing all classrooms in
     * the classroom data container
     *
     */
    public static void writeJSONFile(String fileLocation, StudentDC studentDataContainer) {

        PrintWriter jsonFile = null;

        try {
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "student.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(studentDataContainer.getListOfStudents(), jsonFile);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        } finally {
            // Flush the output stream and close the file
            jsonFile.flush();
            jsonFile.close();
        }
    } 
    public static ArrayList<Student> readJSONFile(String fileLocation) {
        
        ArrayList<Student> listOfStudents = new ArrayList<>();
        try {
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "student.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Student[] studentArray = gson.fromJson(jsonFile, Student[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < studentArray.length; i++) {
                listOfStudents.add(studentArray[i]);
            }
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        } finally {
            return listOfStudents;
        }
    }

}
