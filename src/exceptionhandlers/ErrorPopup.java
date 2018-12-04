package exceptionhandlers;

import static controllers.Application.getDEBUG_LOGGER;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorPopup {

    /**
     * Constructor takes the following data:
     * 
     * @param badFormData - the form that has bad data
     * @param exception   - the exception that was thrown
     * 
     * Creates a popup window that displays the error
     */
    public ErrorPopup(JFrame badFormData, Exception exception) {
        getDEBUG_LOGGER().log(Level.FINEST, "Got Exception: {0}", exception.getMessage());
        JOptionPane.showMessageDialog(badFormData,
                exception.getMessage(),
                "Invalid Data",
                JOptionPane.WARNING_MESSAGE);
    }
}
