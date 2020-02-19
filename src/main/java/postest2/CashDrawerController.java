package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.CashDrawer;
import jpos.CashDrawerConst;
import jpos.JposException;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CashDrawerController extends SharableController implements Initializable, StatusUpdateListener {
	
	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;
	
	@FXML
	public TextArea textAreaActionLog;
	@FXML
	public Button buttonOpenCash;
	@FXML
	public Button buttonGetDrawer;
	@FXML
	public Button buttonWaitForDrawer;

	// WaitForDrawerClose
	@FXML
	public TextField waitForDrawerClose_beepTimeout;
	@FXML
	public TextField waitForDrawerClose_beepFrequency;
	@FXML
	public TextField waitForDrawerClose_beepDuration;
	@FXML
	public TextField waitForDrawerClose_beepDelay;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		setUpLogicalNameComboBox("CashDrawer");
		service = new CashDrawer();
		((CashDrawer) service).addStatusUpdateListener(this);
		RequiredStateChecker.invokeThis(this, service);
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((CashDrawer) service).setDeviceEnabled(true);
			} else {
				((CashDrawer) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
		RequiredStateChecker.invokeThis(this, service);
		setupGuiObjects();
	}

	@FXML
	public void handleOpenCash(ActionEvent e) {
		try {
			((CashDrawer) service).openDrawer();
		} catch (JposException je) {
			je.printStackTrace();
			JOptionPane.showMessageDialog(null, "Exception in openDrawer: " + je.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleGetDrawer(ActionEvent e) {
		try {
			if (((CashDrawer) service).getDrawerOpened()) {
				textAreaActionLog.appendText("Cash drawer is open.\n");
			} else {
				textAreaActionLog.appendText("Cash drawer is closed.\n");
			}
		} catch (JposException je) {
			je.printStackTrace();
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
	}

	@FXML
	public void handleWaitForDrawer(ActionEvent e) {
		try {
			int beepTimeout = Integer.parseInt(waitForDrawerClose_beepTimeout.getText());
			int beepFrequency = Integer.parseInt(waitForDrawerClose_beepFrequency.getText());
			int beepDuration = Integer.parseInt(waitForDrawerClose_beepDuration.getText());
			int beepDelay = Integer.parseInt(waitForDrawerClose_beepDelay.getText());
			((CashDrawer) service).waitForDrawerClose(beepTimeout, beepFrequency, beepDuration, beepDelay);
		} catch (JposException je) {
			je.printStackTrace();
			JOptionPane.showMessageDialog(null, je.getMessage());
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
			JOptionPane.showMessageDialog(null, ne.getMessage());
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String msg = DeviceProperties.getProperties(service, null);
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
			((CashDrawer) service).retrieveStatistics(stats);
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

	private class ActionLogAdder extends TextFieldAdder {
		ActionLogAdder(String message) {
			super(message, textAreaActionLog);
		}
	}

	@Override
	public String getSUEMessage(int status) {
		String message = super.getSUEMessage(status);
		if (message == null) {
			switch (status) {
				case CashDrawerConst.CASH_SUE_DRAWERCLOSED:
					return "Cash drawer closed";
				case CashDrawerConst.CASH_SUE_DRAWEROPEN:
					return "Cash drawer open";
				default:
					return "Unknown status: " + status;
			}
		}
		return message;
	}

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent sue) {
		super.statusUpdateOccurred(sue);
		String msg = getSUEMessage(sue.getStatus());
		if (msg != null) {
			Platform.runLater(new ActionLogAdder("Status Update Event: " + msg + "\n"));
		}
	}

	@Override
	public void setupGuiObjects() {
		super.setupGuiObjects();
		if (waitForDrawerClose_beepTimeout.getText().equals("")) {
			waitForDrawerClose_beepTimeout.setText("100");
		}
		if (waitForDrawerClose_beepFrequency.getText().equals("")) {
			waitForDrawerClose_beepFrequency.setText("500");
		}
		if (waitForDrawerClose_beepDuration.getText().equals("")) {
			waitForDrawerClose_beepDuration.setText("100");
		}
		if (waitForDrawerClose_beepDelay.getText().equals("")) {
			waitForDrawerClose_beepDelay.setText("200");
		}
	}
}
