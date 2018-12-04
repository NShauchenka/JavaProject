
package testclasses;

import interfaces.IClassroom;
import interfaces.ICourse;
import interfaces.IFaculty;
import interfaces.IPerson;
import interfaces.IStudent;

/**
 * @author nastassiashauchenka
 */
public class TestInterfaceDeclaration {
    
    public static void main(String[] args) {
        // Create a classroom interface
        IClassroom classroom;
        
        // Create a course interface
        ICourse course;
        
        // Create a Faculty interface
        IFaculty faculty;
        
        // Create a Student interface
        IStudent newStudent;
        
        // Create a Person interface
        IPerson newPerson;
        
        System.out.println("All interfaces declared correctly!");
    }

}
