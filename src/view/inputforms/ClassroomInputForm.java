package view.inputforms;

import controllers.InputClassroomFormController;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ClassroomInputForm extends javax.swing.JFrame {

    // Form components  
    /**
     * use default constructors to create the form components and then use
     * setters to to set the values
     */
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel roomNumberLabel;
    private javax.swing.JTextField roomNumberTextfield;
    private javax.swing.JComboBox roomTypeCombobox;
    private javax.swing.JLabel roomTypeLabel;
    private javax.swing.JButton saveButton;

    /**
     * Constructor
     */
    public ClassroomInputForm(InputClassroomFormController controller) {

        // Instantiate the components
        roomNumberLabel = new javax.swing.JLabel();
        roomTypeLabel = new javax.swing.JLabel();
        roomNumberTextfield = new javax.swing.JTextField();
        roomTypeCombobox = new javax.swing.JComboBox();
        saveButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        // Link the controller to the buttons on the form
        this.clearButton.addActionListener(controller);
        this.saveButton.addActionListener(controller);
        this.closeButton.addActionListener(controller);

        /**
         * set close function to dispose of the form
         */
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        /**
         * set the form layout to null, we'll place the components manually
         * using x,y layout positions
         */
        getContentPane().setLayout(null);

        /**
         * set the field label
         */
        roomNumberLabel.setText("Room Number:");
        /**
         * add to form
         */
        getContentPane().add(roomNumberLabel);
        roomNumberLabel.setBounds(30, 20, 120, 14);

        /**
         * set the field label
         */
        roomTypeLabel.setText("Room Type:");
        
        /**
         * add to form
         */
        getContentPane().add(roomTypeLabel);
        roomTypeLabel.setBounds(30, 50, 100, 14);

        /**
         * add input field to form
         */
        getContentPane().add(roomNumberTextfield);
        roomNumberTextfield.setBounds(120, 20, 190, 20);

        /**
         * Create the array of values for the data model 
         */
        String[] datamodelValues = {"Lab", "Classroom", "Lecture Hall"};
        /**
         * Create the data model for the combo box
         */
        DefaultComboBoxModel comboboxDataModel = new DefaultComboBoxModel(datamodelValues);
        
        /**
         * Link the data model to the combo box
         */
        roomTypeCombobox.setModel(comboboxDataModel);
        
        /**
         * Add the combo box to the form
         */
        getContentPane().add(roomTypeCombobox);
        roomTypeCombobox.setBounds(120, 50, 190, 20);

        /**
         * set the button label
         */
        saveButton.setText("Save");
        /**
         * add button to form
         */
        getContentPane().add(saveButton);
        saveButton.setBounds(40, 80, 100, 23);

        /**
         * set the button label
         */
        clearButton.setText("Clear");
        /**
         * add button to form
         */
        getContentPane().add(clearButton);
        clearButton.setBounds(150, 80, 90, 23);

        /**
         * set the button label
         */
        closeButton.setText("Close");
        /**
         * add button to form
         */
        getContentPane().add(closeButton);
        closeButton.setBounds(250, 80, 90, 23);

        // Set Size after creating
        this.setSize(400, 150);
    }

    // accessor methods for controller class to use
    public JTextField getRoomNumberTextfield() {
        return roomNumberTextfield;
    }

    public JComboBox getRoomTypeCombobox() {
        return roomTypeCombobox;
    }

}

