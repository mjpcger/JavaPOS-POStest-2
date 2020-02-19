package postest2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController implements Initializable, DataListener, StatusUpdateListener {

    /* ************************************************************************
     * ************************ Action Handler ********************************
     * ************************************************************************
     */

    // Common
    @FXML
    public ComboBox<String> logicalName;
    @FXML
    public Button buttonOpen;
    @FXML
    @RequiredState(JposState.OPENED)
    public Button buttonClaim;
    @FXML
    @RequiredState(JposState.CLAIMED)
    public Button buttonRelease;
    @FXML
    @RequiredState(JposState.ENABLED)
    public Button buttonStatistics;
    @FXML
    @RequiredState(JposState.OPENED)
    public Button buttonClose;
    @FXML
    @RequiredState(JposState.ENABLED)
    public Button buttonFirmware;
    @FXML
    @RequiredState(JposState.CLOSED)
    public Button buttonOCE;
    @FXML
    @RequiredState(JposState.ENABLED)
    public Button buttonInfo;
    @FXML
    public Text statusLabel;
    @FXML
    public Text powerLabel;
    @FXML
    @RequiredState(JposState.ENABLED)
    public CheckBox freezeEvents;
    @FXML
    @RequiredState(JposState.OPENED)
    public CheckBox dataEventEnabled;
    @FXML
    @RequiredState(JposState.OPENEDNOTENABLED)
    public CheckBox powerNotify;

    // DirectIO
    @FXML
    public TextField directIO_command;
    @FXML
    public TextField directIO_data;
    @FXML
    public TextField directIO_object;
    @FXML
    public RadioButton directIO_datatypeString;
    @FXML
    public RadioButton directIO_datatypeByteArray;

    // Group for the radiobuttons
    @FXML
    public final ToggleGroup directIO_datatypeGroup = new ToggleGroup();

    BaseJposControl service;

    static Map<String, BaseJposControl> Services = new HashMap<>();

    static String statistics = "";

    @FXML
    public void handleSetLogicalName(ActionEvent e) {
        if (logicalName.getValue() != null) {
            if (Services.containsValue(service)) {
                BaseJposControl newservice = Services.get(logicalName.getValue());
                if (newservice != service) {
                    try {
                        if (getDeviceState(service) == JposState.ENABLED)
                            service.setFreezeEvents(true);
                    } catch (JposException ex) {
                        ex.printStackTrace();
                    }
                    if (newservice == null)
                        initialize(null, null);
                    else
                        service = newservice;
                }
            } else {
                BaseJposControl newservice = Services.get(logicalName.getValue());
                if (newservice != null) {
                    service = newservice;
                }
            }
            RequiredStateChecker.invokeThis(this, service);
            setupGuiObjects();
        }
    }

    public void setupGuiObjects() {
        setStatusLabel();
        setFreezeEvents();
        setPowerNotify();
        setPowerLabel();
        setDataEventEnabled();
    }

    private void setDataEventEnabled() {
        if (dataEventEnabled != null) {
            Method getDataEventEnabled = getMethod(service, "getDataEventEnabled");
            try {
                dataEventEnabled.setSelected(service.getState() != JposConst.JPOS_S_CLOSED && getDataEventEnabled != null &&
                        (boolean) getDataEventEnabled.invoke(service));
            } catch (Exception e) {
                dataEventEnabled.setSelected(false);
            }
        }
    }

    private void setPowerNotify() {
        Method getCapPowerReporting = getMethod(service, "getCapPowerReporting");
        Method getPowerNotify = getMethod(service, "getPowerNotify");
        try {
            powerNotify.setSelected(service.getState() != JposConst.JPOS_S_CLOSED && getCapPowerReporting != null &&
                    getPowerNotify != null && (int) getCapPowerReporting.invoke(service) != JposConst.JPOS_PR_NONE &&
                    (int) getPowerNotify.invoke(service) == JposConst.JPOS_PN_ENABLED);
        } catch (Exception e) {
            powerNotify.setSelected(false);
        }
    }

    private void setFreezeEvents() {
        try {
            freezeEvents.setSelected(service.getState() != JposConst.JPOS_S_CLOSED && service.getFreezeEvents());
        } catch (JposException e) {
            freezeEvents.setSelected(false);
        }
    }

    @FXML
    public void handleOpen(ActionEvent e) {
        directIO_datatypeByteArray.setToggleGroup(directIO_datatypeGroup);
        directIO_datatypeString.setToggleGroup(directIO_datatypeGroup);
        directIO_datatypeByteArray.setSelected(true);

        try {
            if (logicalName.getValue() != null && !logicalName.getValue().isEmpty()) {
                service.open(logicalName.getValue());
                Services.put(logicalName.getValue(), service);
                RequiredStateChecker.invokeThis(this, service);
                setupGuiObjects();
            } else {
                JOptionPane.showMessageDialog(null, "Choose a device!", "Logical name is empty",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (JposException je) {
            je.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to open \"" + logicalName.getSelectionModel().getSelectedItem()
                    + "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void handleClose(ActionEvent e) {
        try {
            service.close();
            Services.remove(service);
        } catch (JposException je) {
            je.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to close \""
                            + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(),
                    "Failed", JOptionPane.ERROR_MESSAGE);
        }
        RequiredStateChecker.invokeThis(this, service);
        setupGuiObjects();
    }

    @FXML
    abstract public void handleDeviceEnable(ActionEvent e);

    @FXML
    public void handleClaim(ActionEvent e) {
        try {
            service.claim(0);
            RequiredStateChecker.invokeThis(this, service);
        } catch (JposException je) {
            je.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to claim \""
                            + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(),
                    "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void handleRelease(ActionEvent e) {
        try {
            service.release();
        } catch (JposException je) {
            je.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to release \""
                            + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(),
                    "Failed", JOptionPane.ERROR_MESSAGE);
        }
        RequiredStateChecker.invokeThis(this, service);
        setupGuiObjects();
    }

    public void handleInfo(ActionEvent e) {

    }

    public void handleStatistics(ActionEvent e) {

    }

    // Method to parse the String XML and print the data for the
    // handleStatistics function
    public static void printStatistics(Node e, String tab) {
        if (e.getNodeType() == Node.TEXT_NODE) {
            statistics += tab + e.getNodeValue() + "\n";
            return;
        }

        if (!(e.getNodeName().equals("Name") || e.getNodeName().equals("Value") || e.getNodeName().equals("UPOSStat")
                || e.getNodeName().equals("Event") || e.getNodeName().equals("Equipment") || e.getNodeName().equals(
                "Parameter")))
            statistics += tab + e.getNodeName();

        if (e.getNodeValue() != null) {
            statistics += tab + " " + e.getNodeValue();
        }

        NodeList childs = e.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            printStatistics(childs.item(i), " ");
        }
    }

    @FXML
    public void handleFirmware(ActionEvent e) {
        try {
            FirmwareUpdateDlg dlg = new FirmwareUpdateDlg(service);
            dlg.setVisible(true);
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void handleFreezeEvents(ActionEvent e) {
        try {
            service.setFreezeEvents(freezeEvents.selectedProperty().getValue());
        } catch (JposException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    @FXML
    public void handleDataEventEnabled(ActionEvent e) {
        Method setDataEventEnabled = getMethod(service, "setDataEventEnabled");
        try {
            if (setDataEventEnabled != null) {
                setDataEventEnabled.invoke(service, dataEventEnabled.selectedProperty().getValue());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    @FXML
    public void handlePowerNotify(ActionEvent e) {
        try {
            Method setPowerNotify = getMethod(service, "setPowerNotify");
            Method getCapPowerReporting = getMethod(service, "getCapPowerReporting");
            Method getPowerNotify = getMethod(service, "getPowerNotify");
            if (setPowerNotify != null && getCapPowerReporting != null && getPowerNotify != null) {
                int capPowerReporting = (int) getCapPowerReporting.invoke(service);
                int notify = powerNotify.selectedProperty().getValue() ? JposConst.JPOS_PN_ENABLED : JposConst.JPOS_PN_DISABLED;
                if (capPowerReporting != JposConst.JPOS_PR_NONE && notify != (int) getPowerNotify.invoke(service))
                    setPowerNotify.invoke(service, notify);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    Method getMethod(Object object, String name) {
        try {
            Method methods[] = Class.forName(object.getClass().getName()).getMethods();
            for (Method method : methods) {
                if (method.getName().equals(name)) {
                    return method;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void handleBrowseDirectIOData(ActionEvent e) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose DirectIOData");
        File f = chooser.showOpenDialog(null);
        if (f != null) {
            directIO_data.setText(f.getAbsolutePath());
        }
    }

    @FXML
    public void handleBrowseDirectIOObject(ActionEvent e) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose DirectIOData");
        File f = chooser.showOpenDialog(null);
        if (f != null) {
            directIO_object.setText(f.getAbsolutePath());
        }
    }

    @FXML
    public void handleDirectIO(ActionEvent e) {
        if (directIO_command.getText().isEmpty() || directIO_data.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "One of the numeric parameters is not specified!");
        } else {
            try {
                File fileData = new File(directIO_data.getText());
                int[] dataArrInt = null;
                if(fileData.exists()){
                    // Reads content from File
                    dataArrInt = readIntsFromFile(directIO_data.getText());
                } else {
                    String[] dataArrString = directIO_data.getText().split(",");
                    dataArrInt = new int[dataArrString.length];
                    for (int i = 0; i < dataArrString.length; i++) {
                        dataArrInt[i] = Integer.parseInt(dataArrString[i]);
                    }
                }
                Object object = null;

                File fileObject = new File(directIO_object.getText());
                if(fileObject.exists()){
                    if(directIO_datatypeByteArray.isSelected()){
                        object = readBytesFromFile(directIO_object.getText());
                    } else if(directIO_datatypeString.isSelected()){
                        object = convertBytesToString(readBytesFromFile(directIO_object.getText()));
                    }
                } else {
                    if(directIO_datatypeByteArray.isSelected()){
                        String[] objArrString = directIO_object.getText().split(",");
                        byte[] objectArr = new byte[objArrString.length];
                        for (int i = 0; i < objArrString.length; i++) {
                            objectArr[i] = Byte.parseByte(objArrString[i]);
                        }
                        object = objectArr;
                    } else if(directIO_datatypeString.isSelected()){
                        object = directIO_object.getText();
                    }
                }

                // Execute DirectIO
                service.directIO(Integer.parseInt(directIO_command.getText()), dataArrInt, object);

            } catch (JposException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                e1.printStackTrace();
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                e1.printStackTrace();
            }
        }
    }

    class StateCodeMapper extends ErrorCodeMapper {
        StateCodeMapper() {
            super();
            Mappings = new Object[]{
                    JposConst.JPOS_S_CLOSED, "JPOS_S_CLOSED",
                    JposConst.JPOS_S_IDLE, "JPOS_S_IDLE",
                    JposConst.JPOS_S_BUSY, "JPOS_S_BUSY",
                    JposConst.JPOS_S_ERROR, "JPOS_S_ERROR"
            };
        }
    }

    /**
     * Set StatusLabel corresponding to the Device Status
     */
    void setStatusLabel() {
        statusLabel.setText(new StateCodeMapper().getName(service.getState()));
    }

    class PowerStateCodeMapper extends ErrorCodeMapper {
        PowerStateCodeMapper() {
            super();
            Mappings = new Object[]{
                    JposConst.JPOS_PS_UNKNOWN, "JPOS_PS_UNKNOWN",
                    JposConst.JPOS_PS_ONLINE, "JPOS_PS_ONLINE",
                    JposConst.JPOS_PS_OFF, "JPOS_PS_OFF",
                    JposConst.JPOS_PS_OFFLINE, "JPOS_PS_OFFLINE",
                    JposConst.JPOS_PS_OFF_OFFLINE, "JPOS_PS_OFF_OFFLINE"
            };
        }
    }

    /**
     * Set PowerState label corresponding to the Power status
     */
    public void setPowerLabel() {
        Method getPowerState = getMethod(service, "getPowerState");
        int powerState;
        try {
            powerState = (int) getPowerState.invoke(service);
        } catch (Exception e) {
            powerState = JposConst.JPOS_PS_UNKNOWN;
        }
        powerLabel.setText(new PowerStateCodeMapper().getName(powerState));
    }

    protected void setUpLogicalNameComboBox(String devCategory) {
        if (!LogicalNameGetter.getLogicalNamesByCategory(devCategory).isEmpty()) {
            logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(devCategory));
        }
    }

    /**
     * Read the given binary file, and return its contents as a byte array.
     *
     */
    protected static byte[] readBytesFromFile(String aInputFileName) {
        File file = new File(aInputFileName);
        byte[] result = new byte[(int) file.length()];
        try {
            InputStream input = null;
            try {
                int totalBytesRead = 0;
                input = new BufferedInputStream(new FileInputStream(file));
                while (totalBytesRead < result.length) {
                    int bytesRemaining = result.length - totalBytesRead;
                    // input.read() returns -1, 0, or more :
                    int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
                    if (bytesRead > 0) {
                        totalBytesRead = totalBytesRead + bytesRead;
                    }
                }
            } finally {
                input.close();
            }
        } catch (FileNotFoundException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();

        }
        return result;
    }

    /**
     * Read the given binary file, and return its contents as a byte array.
     *
     */
    protected static int[] readIntsFromFile(String aInputFileName) {
        File file = new File(aInputFileName);
        int[] result = new int[(int) file.length()];
        try {
            InputStream input = null;
            try {
                input = new BufferedInputStream(new FileInputStream(file));
                int readInt;
                int num= 0;
                while ((readInt = input.read()) != -1) {
                    result[num] = readInt;
                    num ++;
                }
            } finally {
                input.close();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();

        }
        return result;
    }

    /**
     * Converty a byte[] to a String
     *
     */
    protected static String convertBytesToString(byte[] bytesFromFile) {
        String ret = "";

        for (int i = 0; i < bytesFromFile.length; i++) {
            if (i != 0) {
                ret += ",";
            }
            ret += (int) bytesFromFile[i];
        }

        return ret;
    }

    /**
     * Write a byte array to the given file. Writing binary data is
     * significantly simpler than reading it.
     */
    protected static void writeBytesToFile(byte[] aInput, String aOutputFileName) {
        try {
            OutputStream output = null;
            try {
                output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
                output.write(aInput);
            } finally {
                output.close();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Sets the tooltips for the common buttons (Open, Claim, Release, Close, ..)
     */
    protected void setUpTooltips() {
        buttonClaim.setTooltip(new Tooltip("Claims the Device"));
        buttonClose.setTooltip(new Tooltip("Closes the connection to the Device"));
        buttonFirmware.setTooltip(new Tooltip("Update or view the Firmware Version of the Device"));
        buttonInfo.setTooltip(new Tooltip("Shows Information about the Device"));
        buttonOCE.setTooltip(new Tooltip("Open, Claims and Enables the Device"));
        buttonOpen.setTooltip(new Tooltip("Opens the Device"));
        buttonRelease.setTooltip(new Tooltip("Releases the Device"));
        buttonStatistics.setTooltip(new Tooltip("View, reset or update Device Statistics"));
    }

    /**
     * Gets the current DeviceState
     *
     * @param service
     * @return
     * @throws JposException
     */
    protected static JposState getDeviceState(BaseJposControl service) {
        JposState deviceState = null;
        try {
            if (!service.getClaimed()) {
                deviceState = JposState.OPENED;
            }
            if (service.getClaimed()) {
                deviceState = JposState.CLAIMED;
            }

            if (service.getDeviceEnabled()) {
                deviceState = JposState.ENABLED;
            }
        } catch (JposException e) {
            if (e.getErrorCode() == JposConst.JPOS_E_CLOSED) {
                deviceState = JposState.CLOSED;
            }
        }
        return deviceState;

    }

    @Override
    public void dataOccurred(DataEvent dataEvent) {
        if (dataEventEnabled instanceof CheckBox)
            dataEventEnabled.setSelected(false);
    }

    @Override
    public void statusUpdateOccurred(StatusUpdateEvent sue) {
        setPowerLabel();
    }

    public String getSUEMessage(int status) {
        switch (status) {
            case JposConst.JPOS_SUE_POWER_ONLINE:
                return "Device online";
            case JposConst.JPOS_SUE_POWER_OFF:
                return "Device off";
            case JposConst.JPOS_SUE_POWER_OFFLINE:
                return "Device offline";
            case JposConst.JPOS_SUE_POWER_OFF_OFFLINE:
                return "Device off or offline";
            case JposConst.JPOS_SUE_UF_COMPLETE:
                return "Firmware update complete, device OK";
            case JposConst.JPOS_SUE_UF_FAILED_DEV_OK:
                return "Firmware update failed, device OK";
            case JposConst.JPOS_SUE_UF_FAILED_DEV_UNRECOVERABLE:
                return "Firmware update failed, device needs service";
            case JposConst.JPOS_SUE_UF_FAILED_DEV_NEEDS_FIRMWARE:
                return "Firmware update failed, device needs update";
            case JposConst.JPOS_SUE_UF_FAILED_DEV_UNKNOWN:
                return "Firmware updatefailed, device state unknown";
            case JposConst.JPOS_SUE_UF_COMPLETE_DEV_NOT_RESTORED:
                return "Firmware update complete, device reset";
            default:
                if (status >= JposConst.JPOS_SUE_UF_PROGRESS && status < JposConst.JPOS_SUE_UF_COMPLETE) {
                    return "Updating firmware (" + (status - JposConst.JPOS_SUE_UF_PROGRESS) + "%)";
                }
        }
        return null;
    }

    public class TextFieldAdder implements Runnable {
        public TextFieldAdder(String message, TextArea area) {
            Message = message;
            TextField = area;
        }
        private TextArea TextField;
        private String Message;
        @Override
        public void run() {
            TextField.appendText(Message);
        }
    }
}
