/*
 * Listens for events on the input form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed" - this method contains all the logic to process the data
 * on the form, as well as several other events
 */
package controllers;

import com.sun.media.sound.InvalidDataException;
import datamodels.Classroom;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import datacontainers.ClassroomDC;
import exceptionhandlers.ErrorPopup;
import utilities.io.ClassroomIO;
import utilities.io.ClassroomJSONiO;
import view.inputforms.ClassroomInputForm;

public class InputClassroomFormController implements ActionListener {

    // The Classroom data model is passed in via the constructor
    private ClassroomDC dataModel;

    // File location
    String fileLocation;
    
    // The form is created here and this constructor object gets passed to it
    private ClassroomInputForm form;

    // Constructor 
    public InputClassroomFormController(ClassroomDC dataModel, String fileLocation) {

        // Store the passed in data model
        this.dataModel = dataModel;

        this.fileLocation = fileLocation;
        
        // create the form
        form = new ClassroomInputForm(this);

        // make the form visible
        this.form.setVisible(true);
    }

    /**
     * Implements actionPerformed method of the ActionListener interface
     * @param event
     */
    public void actionPerformed(java.awt.event.ActionEvent event) {

        // TO-DO - implement the controller logic
        
        if (event.getActionCommand().equals("Save")) {
            this.saveData();
        } else if (event.getActionCommand().equals("Clear")) {
            this.clearForm();
        } else if (event.getActionCommand().equals("Close")) {
            this.closeForm();
        }
    }

    /**
     * Private save method If an error is thrown, handle it immediately.
     */
    private void saveData() {
        
        try {
            

        // Create a new classroom object
        Classroom aClassroom = new Classroom();

        // Retrieve the values from the form
        String roomNumber = form.getRoomNumberTextfield().getText();
        
        if (isEmpty(roomNumber)) {
                throw new InvalidDataException("Missing Data!");
            }

            if (!isValid(roomNumber)) {
                throw new InvalidDataException("Invalid Data!");
            }

        // The value must come from the drop down list of room types
        // Step 1 - Retrieve the data model associated with the combo list box
        ComboBoxModel datamodel = this.form.getRoomTypeCombobox().getModel();
        // Step 2 - Retrieve the selected item from the data model, notice
        // it is stored as type Object, we need to convert it in the next step
        Object selectedItem = datamodel.getSelectedItem();
        // Step 3 - Convert (Cast) the selected item to a string
        String roomType = (String) selectedItem;
        // Step 4 - Use the Classroom setters to set the values
        aClassroom.setRoomNumber(roomNumber);
        aClassroom.setRoomType(roomType);

        // Add the new classroom to the list in ClassroomDC
        dataModel.getListOfClassrooms().add(aClassroom);
        
        ClassroomIO.writeXMLFile(fileLocation, dataModel);
        
        ClassroomJSONiO.writeJSONFile(fileLocation, dataModel);
        
        } catch (InvalidDataException exception) {
            // Invalid data was found, 
            // No classroom will be saved,
            // create an error popup
            ErrorPopup error = new ErrorPopup(form, exception);
        }
    }

    /**
     * Private method to clearForm the data
     */
    private void clearForm() {
        form.getRoomNumberTextfield().setText("");
        form.getRoomTypeCombobox().setSelectedIndex(0);
    }

    /**
     * Private method to close the form
     */
    private void closeForm() {
        this.form.dispose();
    }

    // Getters used in the controller
    public ClassroomDC getDC() {
        return dataModel;
    }

    public ClassroomInputForm getForm() {
        return form;
    }

    private boolean isEmpty(String data) {
        if (data.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
     private boolean isValid(String data) {

        if (data.matches("^[a-zA-Z]{2}[0-9]{3}$")) {
            return true;
        } else {
            return false;
        }
    }
}

