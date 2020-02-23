package postest2;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javafx.scene.text.Text;
import jpos.CAT;
import jpos.CATConst;
import jpos.JposConst;
import jpos.JposException;

import jpos.events.*;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CATController extends CommonController implements Initializable, ErrorListener, OutputCompleteListener,
		DirectIOListener {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;

	@FXML
	@RequiredState(JposState.ENABLED)
	public TextArea events;

	@FXML
	@RequiredState(JposState.ENABLED)
	public Text directIOEventsTitle;

	@FXML
	public ComboBox<String> paymentMedia;
	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox trainingMode;
	@FXML
	public ComboBox<String> accessDailyLog_type;

	@FXML
	public TextField additionalSecurityInformation;
	@FXML
	public TextField accessDailyLog_sequenceNumber;
	@FXML
	public TextField accessDailyLog_timeout;
	@FXML

	public TextField authorize_sequenceNumber;
	@FXML
	public TextField authorize_amount;
	@FXML
	public TextField authorize_taxOthers;
	@FXML
	public TextField authorize_timeout;
	@FXML
	public TextField cashDeposit_sequenceNumber;
	@FXML
	public TextField cashDeposit_amount;
	@FXML
	public TextField cashDeposit_timeout;
	@FXML
	public TextField checkCard_sequenceNumber;
	@FXML
	public TextField checkCard_timeout;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new CAT();
		((CAT) service).addStatusUpdateListener(this);
		((CAT) service).addErrorListener(this);
		((CAT) service).addOutputCompleteListener(this);
		((CAT) service).addDirectIOListener(this);
		setUpLogicalNameComboBox("CAT");
		RequiredStateChecker.invokeThis(this, service);
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper ccm = new CATConstantMapper();
			String msg = DeviceProperties.getProperties(service, ccm);
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
		String[] stats = new String[]{"", "U_", "M_"};
		try {
			((CAT) service).retrieveStatistics(stats);
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
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((CAT) service).setDeviceEnabled(true);
			} else {
				((CAT) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
		RequiredStateChecker.invokeThis(this, service);
		setupGuiObjects();
	}

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			((CAT) service).setAsyncMode(asyncMode.isSelected());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	private void preparePaymentMediaAndAdditionalSecurityInformation() throws JposException {
		((CAT) service).setAdditionalSecurityInformation(additionalSecurityInformation.getText());
		((CAT) service).setPaymentMedia(CATConstantMapper.getConstantNumberFromString(paymentMedia
				.getSelectionModel().getSelectedItem()));
	}

	private void setAdditionalSecurityInformation() {
		try {
			additionalSecurityInformation.setText(((CAT) service).getAdditionalSecurityInformation());
		} catch (JposException e) {
			additionalSecurityInformation.setText("");
		}
	}

	@FXML
	public void handleSetTrainingMode(ActionEvent e) {
		try {
			((CAT) service).setTrainingMode(trainingMode.isSelected());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
			trainingMode.setSelected(!trainingMode.isSelected());
		}
	}

	@FXML
	public void handleAccessDailyLog(ActionEvent e) {
		if (accessDailyLog_sequenceNumber.getText().isEmpty() || accessDailyLog_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).accessDailyLog(Integer.parseInt(accessDailyLog_sequenceNumber.getText()),
						CATConstantMapper.getConstantNumberFromString(accessDailyLog_type.getSelectionModel()
								.getSelectedItem()), Integer.parseInt(accessDailyLog_timeout.getText()));
				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}

			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeCompletion(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				int sequencno = Integer.parseInt(authorize_sequenceNumber.getText());
				int timeout = Integer.parseInt(authorize_timeout.getText());
				long amount = authorize_amount.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_amount.getText()) * 10000 :
						Long.parseLong(authorize_amount.getText());
				long add = authorize_taxOthers.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_taxOthers.getText()) * 10000 :
						Long.parseLong(authorize_taxOthers.getText());
				preparePaymentMediaAndAdditionalSecurityInformation();
				((CAT) service).authorizeCompletion(sequencno, amount, add, timeout);
				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizePreSales(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				int sequencno = Integer.parseInt(authorize_sequenceNumber.getText());
				int timeout = Integer.parseInt(authorize_timeout.getText());
				long amount = authorize_amount.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_amount.getText()) * 10000 :
						Long.parseLong(authorize_amount.getText());
				long add = authorize_taxOthers.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_taxOthers.getText()) * 10000 :
						Long.parseLong(authorize_taxOthers.getText());
				preparePaymentMediaAndAdditionalSecurityInformation();
				((CAT) service).authorizePreSales(sequencno, amount, add, timeout);
				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeVoid(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				int sequencno = Integer.parseInt(authorize_sequenceNumber.getText());
				int timeout = Integer.parseInt(authorize_timeout.getText());
				long amount = authorize_amount.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_amount.getText()) * 10000 :
						Long.parseLong(authorize_amount.getText());
				long add = authorize_taxOthers.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_taxOthers.getText()) * 10000 :
						Long.parseLong(authorize_taxOthers.getText());
				preparePaymentMediaAndAdditionalSecurityInformation();
				((CAT) service).authorizeVoid(sequencno, amount, add, timeout);
				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeSales(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				int sequencno = Integer.parseInt(authorize_sequenceNumber.getText());
				int timeout = Integer.parseInt(authorize_timeout.getText());
				long amount = authorize_amount.getText().indexOf('.') >= 0 ?
						(long) (Double.parseDouble(authorize_amount.getText()) * 10000 + 0.5) :
						Long.parseLong(authorize_amount.getText());
				long add = authorize_taxOthers.getText().indexOf('.') >= 0 ?
						(long) (Double.parseDouble(authorize_taxOthers.getText()) * 10000 + 0.5) :
						Long.parseLong(authorize_taxOthers.getText());
				preparePaymentMediaAndAdditionalSecurityInformation();
				((CAT) service).authorizeSales(sequencno, amount, add, timeout);
				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeRefund(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				int sequencno = Integer.parseInt(authorize_sequenceNumber.getText());
				int timeout = Integer.parseInt(authorize_timeout.getText());
				long amount = authorize_amount.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_amount.getText()) * 10000 :
						Long.parseLong(authorize_amount.getText());
				long add = authorize_taxOthers.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_taxOthers.getText()) * 10000 :
						Long.parseLong(authorize_taxOthers.getText());
				preparePaymentMediaAndAdditionalSecurityInformation();
				((CAT) service).authorizeRefund(sequencno, amount, add, timeout);
				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeVoidPreSales(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				int sequencno = Integer.parseInt(authorize_sequenceNumber.getText());
				int timeout = Integer.parseInt(authorize_timeout.getText());
				long amount = authorize_amount.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_amount.getText()) * 10000 :
						Long.parseLong(authorize_amount.getText());
				long add = authorize_taxOthers.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(authorize_taxOthers.getText()) * 10000 :
						Long.parseLong(authorize_taxOthers.getText());
				preparePaymentMediaAndAdditionalSecurityInformation();
				((CAT) service).authorizeVoidPreSales(sequencno, amount, add, timeout);
				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleCashDeposit(ActionEvent e) {
		if (cashDeposit_amount.getText().isEmpty() || cashDeposit_sequenceNumber.getText().isEmpty()
				|| cashDeposit_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				long amount = cashDeposit_amount.getText().indexOf('.') >= 0 ?
						(long) Double.parseDouble(cashDeposit_amount.getText()) * 10000 :
						Long.parseLong(cashDeposit_amount.getText());
				((CAT) service).cashDeposit(Integer.parseInt(cashDeposit_sequenceNumber.getText()),
						amount, Integer.parseInt(cashDeposit_timeout.getText()));
				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleCheckCard(ActionEvent e) {
		if (checkCard_sequenceNumber.getText().isEmpty() || checkCard_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).checkCard(Integer.parseInt(checkCard_sequenceNumber.getText()),
						Integer.parseInt(checkCard_timeout.getText()));

				if (asyncMode.isSelected()) {
					setStatusLabel();
				} else {
					updateWritableProperties();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleLockTerminal(ActionEvent e) {
		try {
			((CAT) service).lockTerminal();

			if (asyncMode.isSelected()) {
				setStatusLabel();
			} else {
				updateWritableProperties();
			}
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleUnlockTerminal(ActionEvent e) {
		try {
			((CAT) service).unlockTerminal();
			if (asyncMode.isSelected()) {
				setStatusLabel();
			} else {
				updateWritableProperties();
			}
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * Set up ComboBoxes
	 */
	private void setUpPaymentMedia() {
		paymentMedia.getItems().clear();
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_NONDEFINE.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_CREDIT.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_DEBIT.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_ELECTRONIC_MONEY.getConstant());
		setPaymentMedia();
	}

	private void setUpAccessDailyLogType() {
		accessDailyLog_type.getItems().clear();
		accessDailyLog_type.getItems().add(CATConstantMapper.CAT_DL_REPORTING.getConstant());
		accessDailyLog_type.getItems().add(CATConstantMapper.CAT_DL_SETTLEMENT.getConstant());
		accessDailyLog_type.setValue(CATConstantMapper.CAT_DL_REPORTING.getConstant());
	}

	private void setPaymentMedia() {
		paymentMedia.setValue(DeviceProperties.getPropertyValue(service, new CATConstantMapper(), "getPaymentMedia"));
	}

	private void setAsyncMode() {
		try {
			asyncMode.setSelected(((CAT) service).getAsyncMode());
		} catch (JposException e) {
			asyncMode.setSelected(false);
		}
	}

	private void setTrainingMode() {
		try {
			trainingMode.setSelected(((CAT) service).getTrainingMode());
		} catch (JposException e) {
			trainingMode.setSelected(false);
		}
	}

	@Override
	public void setupGuiObjects() {
		super.setupGuiObjects();
		setUpPaymentMedia();
		setUpAccessDailyLogType();
		setPaymentMedia();
		setAdditionalSecurityInformation();
		setTrainingMode();
		setAsyncMode();
	}

	@Override
	public void errorOccurred(ErrorEvent e) {
		String text = "CAT Error " + CommonErrorCodeMapper.Mapper.getName(e.getErrorCode());
		if (e.getErrorCode() == JposConst.JPOS_E_EXTENDED)
			text = text + " / " + CATErrorCodeMapper.Mapper.getName(e.getErrorCodeExtended());
		setStatusLabel();
		int doit = JOptionPane.showOptionDialog(null, "Error from CAT:\n" + text + "\nClear error?", "CAT error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		if (doit == JOptionPane.YES_OPTION) {
			appendText("\n" + text + ": Operation failed.");
			e.setErrorResponse(JposConst.JPOS_ER_CLEAR);
			statusLabel.setText("JPOS_S_OK");
			updateWritableProperties();
		}
		else {
			appendText("\n" + text + ": Retry operation.");
			statusLabel.setText("JPOS_S_BUSY");
		}
	}

	@Override
	public void outputCompleteOccurred(OutputCompleteEvent ev) {
		Platform.runLater(new Runnable() {
			public void run() {
				updateWritableProperties();
			}
		});
		setStatusLabel();
		appendText("\nOperation " + ev.getOutputID() + " Completed");
	}

	private void updateWritableProperties() {
		setAdditionalSecurityInformation();
		setPaymentMedia();
	}

	@Override
	public void directIOOccurred(DirectIOEvent ev) {
		String text = null;
		String[] lines = ev.getObject().toString().split("\n");
		for (String line : lines) {
			text = text == null ?
					String.format("\n%d - %d - [%s", ev.getEventNumber(), ev.getData(), line) :
					text + "\n        " + line;
		}
		appendText(text + "]");
	}

	private void appendText(String s) {
		Platform.runLater(new TextFieldAdder(s, events));
	}
}
