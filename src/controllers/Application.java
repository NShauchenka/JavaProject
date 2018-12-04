/*
 * This is the main entry into the application. It creates a menu controller object
 * and the controller object creates the forms and the data models as needed
 */
package controllers;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Application {

    private static final Logger DEBUG_LOGGER = Logger.getLogger(Application.class.getName()+"Debug");

    private static FileHandler DEBUG_FILEHANDLER;

    private static final Logger AUDIT_LOGGER = Logger.getLogger(Application.class.getName()+"Audit");

    private static FileHandler AUDIT_FILEHANDLER;

    public static void main(String[] args) {
        
        String fileLocation = args[0];
        //String fileLocation = "/Users/nastassiashauchenka/Desktop/Shauchenka-INFO3010/";
        
        try {
            DEBUG_FILEHANDLER = new FileHandler(fileLocation+"debug.log");

            AUDIT_FILEHANDLER = new FileHandler(fileLocation+"audit.log");
            
            SimpleFormatter formatter = new SimpleFormatter();
            
            AUDIT_FILEHANDLER.setFormatter(formatter);
            
            DEBUG_FILEHANDLER.setFormatter(formatter);
            
            DEBUG_LOGGER.addHandler(DEBUG_FILEHANDLER);
            
            AUDIT_LOGGER.addHandler(AUDIT_FILEHANDLER);
            
            DEBUG_LOGGER.setLevel(Level.FINEST);
            
            AUDIT_LOGGER.setLevel(Level.INFO);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
   
        // Create main menu controller, the controller creates the menu form
        MainMenuController controller = new MainMenuController(fileLocation);

        // Retrieve the main menu form from the controller and make it visible
        controller.getMainMenu().setVisible(true);
    }
    public static Logger getDEBUG_LOGGER() {
        return DEBUG_LOGGER;
    }

    public static Logger getAUDIT_LOGGER() {
        return AUDIT_LOGGER;
    }

}
