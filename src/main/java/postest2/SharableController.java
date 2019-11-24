package postest2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import javax.swing.JOptionPane;

import jpos.JposException;

public abstract class SharableController extends BaseController implements Initializable {

    /* ************************************************************************
     * *************** Action Handler For Exclusive Use Devices ***************
     * ************************************************************************
     */

    // Common
    @FXML
    @RequiredState(JposState.OPENED)
    public CheckBox deviceEnabled;

    @FXML
    public void handleRelease(ActionEvent e) {
        try {
            service.release();
            if (deviceEnabled.isSelected() && service.getDeviceEnabled() == false) {
                deviceEnabled.setSelected(false);
            }

            RequiredStateChecker.invokeThis(this, service);
        } catch (JposException je) {
            je.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to release \""
                            + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(),
                    "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void handleClose(ActionEvent e) {
        try {
            service.close();
            if (!deviceEnabled.isDisable()) {
                deviceEnabled.setSelected(false);
            }

            if (dataEventEnabled != null && !dataEventEnabled.isDisable()) {
                dataEventEnabled.setSelected(false);
            }

            RequiredStateChecker.invokeThis(this, service);
            setStatusLabel();
        } catch (JposException je) {
            je.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to close \""
                            + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(),
                    "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void handleOCE(ActionEvent e) {
        try {
            if(getDeviceState(service) == JposState.CLOSED){
                handleOpen(e);
            }
        } catch (JposException e1) {
            e1.printStackTrace();
        }
    }
}
