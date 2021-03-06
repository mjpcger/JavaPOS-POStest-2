package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.POSKeyboard;
import jpos.POSKeyboardConst;
import jpos.events.DataEvent;
import jpos.events.DataListener;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class POSKeyboardController extends CommonController implements Initializable, DataListener {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Text keyText;
	@FXML
	@RequiredState(JposState.OPENED)
	public ComboBox<String> eventTypes;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new POSKeyboard();
		((POSKeyboard) service).addStatusUpdateListener(this);
		((POSKeyboard) service).addDataListener(this);
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("POSKeyboard");
		
	}
	
	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */
	
	@Override
	public void dataOccurred(DataEvent e) {
		super.dataOccurred(e);
		try {
			String type = ((POSKeyboard) service).getPOSKeyEventType() == POSKeyboardConst.KBD_ET_DOWN ? " Pressed "
					: " Released ";
			keyText.setText("POS key " + Integer.toString(((POSKeyboard) service).getPOSKeyData()) + type);
			if (getDeviceState(service) == JposState.ENABLED)
				((POSKeyboard)service).setDataEventEnabled(true);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, "Exception in getKeyPosition(): " + jpe.getMessage(),
					"Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((POSKeyboard) service).setDeviceEnabled(true);
				((POSKeyboard)service).setDataEventEnabled(true);
				keyText.setText("Press a key");
			} else {
				((POSKeyboard) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
		RequiredStateChecker.invokeThis(this, service);
		setupGuiObjects();
	}

	/**
	 *  Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper pkcm = new POSKeyboardConstantMapper();
			String msg = DeviceProperties.getProperties(service, pkcm);
			JTextArea jta = new JTextArea(msg);
			@SuppressWarnings("serial")
			JScrollPane jsp = new JScrollPane(jta) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(460, 390);
				}
			};
			JOptionPane.showMessageDialog(null, jsp, "Information", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	/**
	 *  Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((POSKeyboard) service).retrieveStatistics(stats);
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

			JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe.getMessage());
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			JOptionPane.showMessageDialog(null, saxe.getMessage());
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}
		statistics = "";
	}

	@FXML
	public void handleSetEventTypes(ActionEvent e) {
		try {
			((POSKeyboard)service).setEventTypes(
					POSKeyboardConstantMapper.getConstantNumberFromString(eventTypes.getValue()));
		} catch (JposException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void setupGuiObjects() {
		POSKeyboardConstantMapper mapper = new POSKeyboardConstantMapper();
		super.setupGuiObjects();
		eventTypes.getItems().clear();
		String current = DeviceProperties.getPropertyValue(service, mapper, "getEventTypes");
		eventTypes.getItems().add(POSKeyboardConstantMapper.KBD_ET_DOWN.getConstant());
		if ("true".equals(DeviceProperties.getPropertyValue(service, mapper, "getCapKeyUp"))) {
			eventTypes.getItems().add(POSKeyboardConstantMapper.KBD_ET_DOWN.getConstant());
		}
		if (current != null)
			eventTypes.getSelectionModel().select(current);
	}
}