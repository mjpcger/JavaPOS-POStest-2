package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.SignatureCapture;

import jpos.events.DataEvent;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SignatureCaptureController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	public TextField beginCapture_formName;

	@FXML
	public CheckBox realTimeDataEnabled;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new SignatureCapture();
		((SignatureCapture) service).addStatusUpdateListener(this);
		((SignatureCapture) service).addDataListener(this);
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("SignatureCapture");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((SignatureCapture) service).setDeviceEnabled(true);
				((SignatureCapture) service).setDataEventEnabled(true);
			} else {
				((SignatureCapture) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
		RequiredStateChecker.invokeThis(this, service);
		setupGuiObjects();
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
//			String msg = DeviceProperties.getProperties(service, SignatureCaptureController.class);
			String msg = "";
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
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((SignatureCapture) service).retrieveStatistics(stats);
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
	public void handleSetRealTimeDataEnabled(ActionEvent e) {
		try {
			((SignatureCapture) service).setRealTimeDataEnabled(realTimeDataEnabled.isSelected());
		} catch (JposException e1) {
			if (getDeviceState(service) != JposState.CLOSED) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
			realTimeDataEnabled.setSelected(!realTimeDataEnabled.isSelected());
		}
	}

	@FXML
	public void handleBeginCapture(ActionEvent e) {
		if (beginCapture_formName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified!");
		} else {
			try {
				((SignatureCapture) service).beginCapture(beginCapture_formName.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleEndCapture(ActionEvent e) {
		try {
			((SignatureCapture) service).endCapture();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * Set up ComboBoxes
	 */

	private void setUpRealTimeDataEnabled() {
		realTimeDataEnabled.setSelected("true".equals(DeviceProperties.getPropertyValue(service, new CommonConstantMapper(), "getRealTimeDataEnabled")));
	}

	@Override
	public void setupGuiObjects() {
		super.setupGuiObjects();
		setUpRealTimeDataEnabled();
	}

	@Override
	public void dataOccurred(DataEvent ev) {
		super.dataOccurred(ev);
		if (getDeviceState(service) == JposState.ENABLED) {
			try {
				((SignatureCapture)service).setDataEventEnabled(true);
			} catch (JposException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
