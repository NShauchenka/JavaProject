/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static controllers.Application.getDEBUG_LOGGER;
import datacontainers.FacultyDC;
import datamodels.Faculty;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author nastassiashauchenka
 */
public class FacultyJSONiO {
        /**
     * Writes out the classroom data in JSON format containing all classrooms in
     * the classroom data container
     *
     */
    public static void writeJSONFile(String fileLocation, FacultyDC facultyDataContainer) {

        PrintWriter jsonFile = null;

        try {
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "faculty.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(facultyDataContainer.getListOfFaculties(), jsonFile);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        } finally {
            // Flush the output stream and close the file
            jsonFile.flush();
            jsonFile.close();
        }
    }  
    public static ArrayList<Faculty> readJSONFile(String fileLocation) {
        
        ArrayList<Faculty> listOfFaculties = new ArrayList<>();
        try {
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "faculty.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Faculty[] facultyArray = gson.fromJson(jsonFile, Faculty[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < facultyArray.length; i++) {
                listOfFaculties.add(facultyArray[i]);
            }
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            getDEBUG_LOGGER().finest("Got Exception: " + exp.getMessage());
        } finally {
            return listOfFaculties;
        }
    }

}
