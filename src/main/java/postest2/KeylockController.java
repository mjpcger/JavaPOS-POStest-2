package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javafx.scene.text.Text;
import jpos.JposException;
import jpos.Keylock;

import jpos.KeylockConst;
import jpos.events.StatusUpdateEvent;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class KeylockController extends SharableController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	public ComboBox<String> waitForKeylockChange_keyPosition;
	@FXML
	public TextField waitForKeylockChange_timeout;
	@FXML
	public Button waitForKeylockChange;
	@FXML
	public Text keyPosition;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new Keylock();
		DeviceProperties.setConversion(service, DeviceProperties.ByteConversion.Hexadecimal);
		((Keylock) service).addStatusUpdateListener(this);
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("Keylock");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((Keylock) service).setDeviceEnabled(true);
			} else {
				((Keylock) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
		setupGuiObjects();
		RequiredStateChecker.invokeThis(this, service);
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper kcm = new KeylockConstantMapper();
			String msg = DeviceProperties.getProperties(service, kcm);
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
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((Keylock) service).retrieveStatistics(stats);
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
	public void handleWaitForKeylockChange(ActionEvent e) {

		if (waitForKeylockChange_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			waitForKeylockChange.setDisable(true);
			try {
				((Keylock) service).waitForKeylockChange(KeylockConstantMapper.getConstantNumberFromString(
						waitForKeylockChange_keyPosition.getSelectionModel().getSelectedItem()), 
						Integer.parseInt(waitForKeylockChange_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
			waitForKeylockChange.setDisable(false);
		}
	}
	
	/*
	 * Set up ComboBoxes
	 */

	class KeyPositionCodeMapper extends ErrorCodeMapper {
		KeyPositionCodeMapper() {
			Mappings = new Object[]{
					KeylockConst.LOCK_KP_ANY, KeylockConstantMapper.LOCK_KP_ANY.getConstant(),
					KeylockConst.LOCK_KP_LOCK, KeylockConstantMapper.LOCK_KP_LOCK.getConstant(),
					KeylockConst.LOCK_KP_NORM, KeylockConstantMapper.LOCK_KP_NORM.getConstant(),
					KeylockConst.LOCK_KP_SUPR, KeylockConstantMapper.LOCK_KP_SUPR.getConstant()
			};
		}
	}

	class EKeyPositionCodeMapper extends KeyPositionCodeMapper {
		EKeyPositionCodeMapper() {
			Mappings[1] = KeylockConstantMapper.LOCK_KP_ELECTRONIC.getConstant();
		}
	}

	private void setUpWaitForKeylockChangeKeyPosition(){
		String current = waitForKeylockChange_keyPosition.getItems().size() > 0
				? waitForKeylockChange_keyPosition.getValue() : null;
		waitForKeylockChange_keyPosition.getItems().clear();
		try {
			KeyPositionCodeMapper mapper = ((Keylock)service).getCapKeylockType() == KeylockConst.LOCK_KT_STANDARD
					? new KeyPositionCodeMapper()
					: new EKeyPositionCodeMapper();
			for (int i = 0, limit = ((Keylock) service).getPositionCount(); i <= limit; i++) {
				waitForKeylockChange_keyPosition.getItems().add(mapper.getName(i));
			}
		} catch (JposException e) {
			if (getDeviceState(service) != JposState.CLOSED) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
		if (waitForKeylockChange_keyPosition.getItems().contains(current))
			waitForKeylockChange_keyPosition.getSelectionModel().select(current);
		else if (waitForKeylockChange_keyPosition.getItems().size() > 0)
			waitForKeylockChange_keyPosition.getSelectionModel().select(0);
	}

	@Override
	public void setupGuiObjects(){
		super.setupGuiObjects();
		setUpWaitForKeylockChangeKeyPosition();
		setKeyPosition();
	}

	private void setKeyPosition() {
        try {
            if (((Keylock) service).getCapKeylockType() == KeylockConst.LOCK_KT_ELECTRONIC) {
                byte[] ekeyval = ((Keylock)service).getElectronicKeyValue();
                String ekeystr = "";
                boolean ispresent = false;
                for (byte b : ekeyval) {
                    ekeystr += String.format("%02x", b & 0xff);
                    ispresent = ispresent || (b != 0);
                }
                keyPosition.setText(ispresent ? ekeystr : "");
            } else {
                keyPosition.setText(new KeyPositionCodeMapper().getName(((Keylock)service).getKeyPosition()));
            }
        } catch (JposException e) {
            if (getDeviceState(service) == JposState.ENABLED) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                e.printStackTrace();
            }
            keyPosition.setText("");
        }
    }

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent ev) {
		super.statusUpdateOccurred(ev);
		setKeyPosition();
	}
}