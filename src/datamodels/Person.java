
package datamodels;

import interfaces.IPerson;
import java.time.LocalDate;

public abstract class Person implements IPerson {
    
    String name;
    String address;
    LocalDate dateOfBirth;
    
    
    @Override
    public abstract String toString ();
    

    
}
