package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.FiscalPrinter;
import jpos.FiscalPrinterConst;
import jpos.JposConst;
import jpos.JposException;

import jpos.events.*;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class FiscalPrinterController extends CommonController implements Initializable,
		ErrorListener {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionTab;
	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;

	@FXML
	@RequiredState(JposState.OPENEDNOTENABLED)
	public CheckBox fixedCurrencyFactor;

	@FXML
	public Text coverLabel;
	@FXML
	public Text paperLabel;
	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox flagWhenIdle;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox duplicateReceipt;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox checkTotal;

	@FXML
	@RequiredState(JposState.ENABLED)
	public Button beginTraining;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Button endTraining;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Button clearError;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Button clearOutput;
	
	// General Printing Settings Tab
	@FXML
	public ComboBox<String> numHeaderLine;
	@FXML
	public TextField headerLine;
	@FXML
	public ComboBox<String> numTrailerLine;
	@FXML
	public TextField trailerLine;
	@FXML
	public TextField vatID;
	@FXML
	public TextField vatValue;
	@FXML
	public TextField additionalHeader;
	@FXML
	public TextField additionalTrailer;
	@FXML
	public TextField date;
	@FXML
	public TextField changeDue;
	@FXML
	public Text textNumHeaderLines;
	@FXML
	public Text textNumTrailerLines;
	@FXML
	public CheckBox doubleWidthHeader;
	@FXML
	public CheckBox doubleWidthTrailer;
	@FXML
	public ComboBox<String> cbSetCurrency;
	@FXML
	public TextField itemName;

	// Fiscal Receipt Tab
	@FXML
	public CheckBox voidSuffix;
	@FXML
	public CheckBox printHeader;
	@FXML
	public ComboBox<String> adjustmentType;
	@FXML
	public ComboBox<String> receiptType;
	@FXML
	public ComboBox<String> receiptStation;
	@FXML
	public ComboBox<String> messageType;
	@FXML
	public ComboBox<String> cbVatID;
	@FXML
	public TextField description;
	@FXML
	public TextField preLine;
	@FXML
	public TextField postLine;
	@FXML
	public TextField priceAmount;
	@FXML
	public TextField quantity;
	@FXML
	public TextField adjustment;
	@FXML
	public TextField vatInfo;
	@FXML
	public TextField unitPrice;
	@FXML
	public TextField unitName;
	@FXML
	public TextField specialTax;
	@FXML
	public TextField specialTaxName;

	// Fiscal Document Tab
	@FXML
	public TextField documentAmount;
	@FXML
	public TextArea documentText;

	// Fiscal Report Tab
	@FXML
	public ComboBox<String> reportType;
	@FXML
	public TextField reportFrom;
	@FXML
	public TextField reportTo;

	// Non Fiscal Printing Tab
	@FXML
	public ComboBox<String> station;
	@FXML
	public TextArea data;
	@FXML
	public TextField DocumentType;
	@FXML
	public TextField LineNumber;
	@FXML
	public TextField Data;

	// Fiscal Printer Status Tab
	@FXML
	public ComboBox<String> dataItem;
	@FXML
	public ComboBox<String> optArgs;
	@FXML
	public ComboBox<String> DateType;
	@FXML
	public ComboBox<String> cbStatusVatID;
	@FXML
	public ComboBox<String> itemTotalizer;
	@FXML
	public ComboBox<String> totalizerType;
	@FXML
	public TextField cbOptArgs;
	@FXML
	public Text vatRate;
	@FXML
	public TextArea output;
	@FXML
	public TextField fiscalID;
	@FXML
	public TextField POSID;
	@FXML
	public TextField cashierID;

	// Variables used for fiscal receipt printing
	private long amountFactorDecimal = 1;
	private int quantityFactorDecimal = 1;
	private long currencySignificanceFactor = 10000;
	private boolean HasVatTable;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new FiscalPrinter();
		((FiscalPrinter) service).addStatusUpdateListener(this);
		((FiscalPrinter) service).addErrorListener(this);
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("FiscalPrinter");

		// Workaround due to widespread error concerning type Currency:
		// Currency - in Java - is ALWAYS implemented as long with implicit four decimals, while many
		// fiscal printer implementations assume Currency as a long with implicit AmountFactorDecimal
		// decimals, while the UPOS specification specifies AmountFactorDecimals as the number of
		// digits that are used by the fiscal printer for calculations.
		// Default should be the interpretation that corresponds to the UPOS specification: Currency
		// has always four implicit digits.
		fixedCurrencyFactor.setSelected(true);
		cbOptArgs.setText("0");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((FiscalPrinter) service).setDeviceEnabled(true);
				if (((FiscalPrinter)service).getPrinterState() != FiscalPrinterConst.FPTR_PS_MONITOR) {
					((FiscalPrinter)service).resetPrinter();
				}

				if (fixedCurrencyFactor.isSelected()) {
					currencySignificanceFactor = amountFactorDecimal = 10000;

					int adp = ((FiscalPrinter) service).getAmountDecimalPlace();
					if (adp > 0) {
						for (int i = 1; i <= adp && currencySignificanceFactor > 1; i++)
							currencySignificanceFactor = currencySignificanceFactor / 10;
					}
				}
				else {
					currencySignificanceFactor = 1;
					amountFactorDecimal = 1;

					int adp = ((FiscalPrinter) service).getAmountDecimalPlace();
					if (adp > 0) {
						for (int i = 1; i <= adp; i++)
							amountFactorDecimal = amountFactorDecimal * 10;
					}
				}
				quantityFactorDecimal = 1;

				int qdp = ((FiscalPrinter) service).getQuantityDecimalPlaces();
				if (qdp > 0) {
					for (int i = 1; i <= qdp; i++)
						quantityFactorDecimal = quantityFactorDecimal * 10;
				}

				setUpReceiptTypes();
				setUpReceiptStations();
				setUpMessageTypes();
				setUpLineNumbers();
				setUpVatIDs();
				setUpCoverState();
				setUpPaperState();
				setUpCurrency();
				setUpAdjustmentType();
				setUpReportType();
				setUpStation();
				setUpDateType();
				setUpDataItem();
				groupTotalizers();
				setUpItemTotalizer();
			} else {
				((FiscalPrinter) service).setDeviceEnabled(false);
				paperLabel.setText("");
				coverLabel.setText("");
				vatRate.setText("");
			}
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
		RequiredStateChecker.invokeThis(this, service);
	}

	private void setUpLineNumbers() throws JposException {
		setUpLineNumbers(numHeaderLine, ((FiscalPrinter)service).getNumHeaderLines());
		setUpLineNumbers(numTrailerLine, ((FiscalPrinter)service).getNumTrailerLines());
	}

	private void setUpLineNumbers(ComboBox<String> box, int maxline) {
		box.getItems().clear();
		for (int i = 1; i <= maxline; i++) {
			box.getItems().add(String.valueOf(i));
		}
	}

	private void setUpVatIDs() throws JposException {
		cbStatusVatID.getItems().clear();
		cbVatID.getItems().clear();
		cbVatID.getItems().add("");
		cbStatusVatID.getItems().add("");
		if (HasVatTable = ((FiscalPrinter) service).getCapHasVatTable()) {
			int numVatRates = ((FiscalPrinter) service).getNumVatRates();
			int optargs;
			int remainingVatRates = numVatRates;
			try {
				optargs = Integer.parseInt(cbOptArgs.getText());
			}
			catch (Exception oae) {
				optargs = 0;
			}
			for (int i = 0; remainingVatRates > 0 && i < 0xffff; i++) {
				try {
					((FiscalPrinter) service).getVatEntry(i, optargs, new int[1]);
					cbStatusVatID.getItems().add(String.valueOf(i));
					cbVatID.getItems().add(String.valueOf(i));
					remainingVatRates--;
				} catch (JposException gvex) {}
			}
			if (remainingVatRates > 0) {
				if (numVatRates > remainingVatRates)
					JOptionPane.showMessageDialog(null, "Could not retrieve " + numVatRates + " VAT IDs");
				else if (optargs != 0)
					JOptionPane.showMessageDialog(null, "No VAT ID found (negative VAT IDs and VAT IDs " +
							"> 0xffff not supported by POSTest2");
				else
					JOptionPane.showMessageDialog(null, "No VAT ID found, set optArgs for getVATEntry to\n" +
							"correct vendor specific value and try again");
			}
		}
		cbVatID.getSelectionModel().select(0);
		cbStatusVatID.getSelectionModel().select(0);
	}

	private void setUpCoverState() throws JposException {
		if (((FiscalPrinter)service).getCapCoverSensor()) {
			if (((FiscalPrinter)service).getCoverOpen()) {
				coverLabel.setText("Open");
			}
			else {
				coverLabel.setText("Closed");
			}
		}
		else
			coverLabel.setText("Unknown");
	}

	private void setUpDateType() throws JposException {
		DateType.getItems().clear();
		DateType.getItems().add(FiscalPrinterConstantMapper.FPTR_DT_CONF.getConstant());
		DateType.getItems().add(FiscalPrinterConstantMapper.FPTR_DT_EOD.getConstant());
		DateType.getItems().add(FiscalPrinterConstantMapper.FPTR_DT_RESET.getConstant());
		DateType.getItems().add(FiscalPrinterConstantMapper.FPTR_DT_RTC.getConstant());
		DateType.getItems().add(FiscalPrinterConstantMapper.FPTR_DT_VAT.getConstant());
		DateType.getItems().add(FiscalPrinterConstantMapper.FPTR_DT_START.getConstant());
		switch (((FiscalPrinter)service).getDateType()) {
			case FiscalPrinterConst.FPTR_DT_CONF:
				DateType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_DT_CONF.getConstant());
				break;
			case FiscalPrinterConst.FPTR_DT_EOD:
				DateType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_DT_EOD.getConstant());
				break;
			case FiscalPrinterConst.FPTR_DT_RESET:
				DateType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_DT_RESET.getConstant());
				break;
			case FiscalPrinterConst.FPTR_DT_VAT:
				DateType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_DT_VAT.getConstant());
				break;
			case FiscalPrinterConst.FPTR_DT_RTC:
				DateType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_DT_RTC.getConstant());
				break;
			case FiscalPrinterConst.FPTR_DT_START:
				DateType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_DT_START.getConstant());
				break;
		}
	}

	private void setUpPaperState() throws JposException {
		String recstate = "";
		if (((FiscalPrinter)service).getCapRecPresent()) {
			if (((FiscalPrinter)service).getCapRecEmptySensor() && (((FiscalPrinter)service).getRecEmpty())) {
				recstate = "Empty";
			}
			else if (((FiscalPrinter)service).getCapRecNearEndSensor() && (((FiscalPrinter)service).getRecNearEnd())) {
				recstate = "Near End";
			}
			else if (((FiscalPrinter)service).getCapRecNearEndSensor() || (((FiscalPrinter)service).getCapRecEmptySensor())) {
				recstate = "Ok";
			}
		}
		String jrnstate = "";
		if (((FiscalPrinter)service).getCapJrnPresent()) {
			if (((FiscalPrinter)service).getCapJrnEmptySensor() && (((FiscalPrinter)service).getJrnEmpty())) {
				jrnstate = "Empty";
			}
			else if (((FiscalPrinter)service).getCapJrnNearEndSensor() && (((FiscalPrinter)service).getJrnNearEnd())) {
				jrnstate = "Near End";
			}
			else if (((FiscalPrinter)service).getCapJrnNearEndSensor() || (((FiscalPrinter)service).getCapJrnEmptySensor())) {
				jrnstate = "Ok";
			}
		}
		String slpstate = "";
		if (((FiscalPrinter)service).getCapSlpPresent()) {
			if (((FiscalPrinter)service).getCapSlpEmptySensor() && (((FiscalPrinter)service).getSlpEmpty())) {
				slpstate = "Empty";
			}
			else if (((FiscalPrinter)service).getCapSlpNearEndSensor() && (((FiscalPrinter)service).getSlpNearEnd())) {
				slpstate = "Near End";
			}
			else if (((FiscalPrinter)service).getCapSlpNearEndSensor() || (((FiscalPrinter)service).getCapSlpEmptySensor())) {
				slpstate = "Ok";
			}
		}
		if (recstate.equals("Empty") || slpstate.equals("Empty") || jrnstate.equals("Empty"))
			paperLabel.setText("Empty");
		else if (recstate.equals("Near End") || slpstate.equals("Near End") || jrnstate.equals("Near End"))
			paperLabel.setText("Near End");
		else if (recstate.equals("Ok") || slpstate.equals("Ok") || jrnstate.equals("Ok"))
			paperLabel.setText("Ok");
		else
			paperLabel.setText("Unknown");
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		try {
			if(getDeviceState(service) == JposState.CLAIMED){
				deviceEnabled.setSelected(true);
				handleDeviceEnable(e);
			}
		} catch (JposException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper fpcm = new FiscalPrinterConstantMapper();
			String msg = DeviceProperties.getProperties(service, fpcm);
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
			((FiscalPrinter) service).retrieveStatistics(stats);
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
		} catch (ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, pce.getMessage());
			pce.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
	}

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			((FiscalPrinter) service).setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleDuplicateReceipt(ActionEvent e) {
		try {
			((FiscalPrinter) service).setDuplicateReceipt(duplicateReceipt.isSelected());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleCheckTotal(ActionEvent e) {
		try {
			((FiscalPrinter) service).setCheckTotal(checkTotal.isSelected());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleFlagWhenIdle(ActionEvent e) {
		try {
			((FiscalPrinter) service).setFlagWhenIdle(flagWhenIdle.selectedProperty().getValue());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleBeginTraining() {
		try {
			((FiscalPrinter) service).beginTraining();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleClearOutput() {
		try {
			((FiscalPrinter) service).clearOutput();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleClearError() {
		try {
			((FiscalPrinter) service).clearError();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndTraining() {
		try {
			((FiscalPrinter) service).endTraining();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/* ********** General Print Settings - Methods ********** */
	@FXML
	public void handleSetHeaderLine(ActionEvent e) {
		if (headerLine.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setHeaderLine(headerLine.getText().length(), headerLine.getText(),
						doubleWidthHeader.isSelected());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetTrailerLine(ActionEvent e) {
		if (trailerLine.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setTrailerLine(trailerLine.getText().length(), trailerLine.getText(),
						doubleWidthTrailer.isSelected());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetVatValue(ActionEvent e) {
		if (vatValue.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setVatValue(Integer.parseInt(vatID.getText()), vatValue.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleGetVATEntry(ActionEvent actionEvent) {
		String field = "vatID";
		try {
			int vatid = Integer.parseInt(vatID.getText());
			field = "optArgs";
			int optargs = Integer.parseInt(cbOptArgs.getText());
			int[] vatrate = new int[]{0};
			((FiscalPrinter)service).getVatEntry(vatid, optargs, vatrate);
			vatRate.setText(Double.toString((double)vatrate[0] / 10000.));
		}
		catch (JposException gvee) {
			JOptionPane.showMessageDialog(null, gvee.getMessage());
			gvee.printStackTrace();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, field + " must be numeric");
		}
	}

	@FXML
	public void handleSetVatTable(ActionEvent e) {
		try {
			((FiscalPrinter) service).setVatTable();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleSetAdditionalHeader(ActionEvent e) {
		if (additionalHeader.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setAdditionalHeader(additionalHeader.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetAdditionalTrailer(ActionEvent e) {
		if (additionalTrailer.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setAdditionalTrailer(additionalTrailer.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetDate(ActionEvent e) {
		if (date.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setDate(date.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetChangeDue(ActionEvent e) {
		if (changeDue.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setChangeDue(changeDue.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetCurrency(ActionEvent e) {
		try {
			((FiscalPrinter) service).setCurrency(FiscalPrinterConstantMapper.getConstantNumberFromString(cbSetCurrency
					.getSelectionModel().getSelectedItem()));
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleBeginItemList(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginItemList(Integer.parseInt(vatID.getText()));
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleVerifyItem(ActionEvent e) {
		if (itemName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).verifyItem(itemName.getText(), Integer.parseInt(vatID.getText()));
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleEndItemList(ActionEvent e) {
		try {
			((FiscalPrinter) service).endItemList();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/* ********** Fiscal Receipt - Methods ********** */
	@FXML
	public void handleBeginFiscalReceipt(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginFiscalReceipt(printHeader.isSelected());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndFiscalReceipt(ActionEvent e) {
		try {
			((FiscalPrinter) service).endFiscalReceipt(printHeader.isSelected());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
		clearFields();
	}

	// Prints a message.
	@FXML
	public void handlePrintRecMessage(ActionEvent e) {
		setPrePostLines();
		try {
			((FiscalPrinter) service).printRecMessage(description.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
		clearFields();
	}

	// Indicates a part of the receipt's total to not be paid.
	@FXML
	public void handlePrintRecNotPaid(ActionEvent e) {
		setPrePostLines();
		long lAmount = 0;
		if (!priceAmount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecNotPaid(description.getText(), lAmount);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
		clearFields();
	}

	/**
	 * Prints a cash-in or cash-out receipt amount.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecCash(ActionEvent e) {
		setPrePostLines();
		long lAmount = 0;
		if (!priceAmount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecCash(lAmount);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
		clearFields();
	}

	/**
	 * Prints a receipt item for a sold item
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecItem(ActionEvent e) {
		setPrePostLines();
		if (voidSuffix.isSelected()) {
			handlePrintRecItemVoid(e);
			return;
		}
		long lPrice = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		long lUnitPrice = 0;
		if (!unitPrice.getText().isEmpty())
			lUnitPrice = (long) (Double.parseDouble(unitPrice.getText()) * amountFactorDecimal);

		int iQuantity = 0;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = getVatInfo();

		try {
			((FiscalPrinter) service).printRecItem(description.getText(), lPrice, iQuantity, iVatInfo, lUnitPrice,
					unitName.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	private int getVatInfo() {
		if (vatInfo.getText().length() > 0) {
			if (HasVatTable)
				return Integer.parseInt(vatInfo.getText());
			return (int) (Double.parseDouble(vatInfo.getText()) * amountFactorDecimal);
		}
		return Integer.parseInt(cbVatID.getSelectionModel().getSelectedItem());
	}

	/**
	 * Cancels one or more items that has been added to the receipt and prints a
	 * void description
	 */
	@FXML
	public void handlePrintRecItemVoid(ActionEvent e) {
		long lPrice = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		long lUnitPrice = 0;
		if (!unitPrice.getText().isEmpty())
			lUnitPrice = (long) (Double.parseDouble(unitPrice.getText()) * amountFactorDecimal);

		int iQuantity = 0;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = getVatInfo();

		try {
			((FiscalPrinter) service).printRecItemVoid(description.getText(), lPrice, iQuantity, iVatInfo, lUnitPrice,
					unitName.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Applies and prints an adjustment (discount or surcharge) to the last
	 * receipt item sold.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecItemAdjustment(ActionEvent e) {
		setPrePostLines();
		if (voidSuffix.isSelected()) {
			handlePrintRecItemAdjustmentVoid(e);
			return;
		}
		long lAmount = 0;
		if (adjustmentType.getSelectionModel().getSelectedItem().equals(
				FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())
				|| adjustmentType.getSelectionModel().getSelectedItem().equals(
						FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * 10000);
		} else if (!adjustment.getText().isEmpty()) {
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * amountFactorDecimal);
		}

		int iVatInfo = getVatInfo();

		try {
			((FiscalPrinter) service).printRecItemAdjustment(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()), description
					.getText(), lAmount, iVatInfo);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Cancels an adjustment that has been added to the fiscal receipt before
	 * and prints a cancellation line.
	 * 
	 * @param e
	 */

	@FXML
	public void handlePrintRecItemAdjustmentVoid(ActionEvent e) {
		long lAmount = 0;
		if (adjustmentType.getSelectionModel().getSelectedItem().equals(
				FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())
				|| adjustmentType.getSelectionModel().getSelectedItem().equals(
						FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * 10000);
		} else if (!adjustment.getText().isEmpty()) {
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * amountFactorDecimal);
		}

		int iVatInfo = getVatInfo();

		try {
			((FiscalPrinter) service).printRecItemAdjustmentVoid(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()), description
					.getText(), lAmount, iVatInfo);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Prints a receipt fuel item
	 */
	@FXML
	public void handlePrintRecItemFuel(ActionEvent e) {
		setPrePostLines();
		if (voidSuffix.isSelected()) {
			handlePrintRecItemFuelVoid(e);
			return;
		}
		long lPrice = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		int iQuantity = 0;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = getVatInfo();

		long iUnitPrice = 0;
		if (!unitPrice.getText().isEmpty())
			iUnitPrice = ((long) (Double.parseDouble(unitPrice.getText()))) * amountFactorDecimal;

		long lSpecialTax = 0;
		if (!specialTax.getText().isEmpty())
			lSpecialTax = (long) (Double.parseDouble(specialTax.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecItemFuel(description.getText(), lPrice, iQuantity, iVatInfo, iUnitPrice,
					unitName.getText(), lSpecialTax, specialTaxName.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Called to void a fuel item
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecItemFuelVoid(ActionEvent e) {
		long lPrice = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		int iVatInfo = getVatInfo();

		long lSpecialTax = 0;
		if (!specialTax.getText().isEmpty())
			lSpecialTax = (long) (Double.parseDouble(specialTax.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecItemFuelVoid(description.getText(), lPrice, iVatInfo, lSpecialTax);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Process one or more item refunds.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecItemRefund(ActionEvent e) {
		setPrePostLines();
		if (voidSuffix.isSelected()) {
			handlePrintRecItemRefundVoid(e);
			return;
		}
		long lAmount = 0;
		if (!priceAmount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		int iQuantity = 0;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = getVatInfo();

		long lUnitAmount = 0;
		if (!unitPrice.getText().isEmpty())
			lUnitAmount = (long) (Double.parseDouble(unitPrice.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecItemRefund(description.getText(), lAmount, iQuantity, iVatInfo,
					lUnitAmount, unitName.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Processes a void of one or more item refunds.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecItemRefundVoid(ActionEvent e) {
		long lAmount = 0;
		if (!priceAmount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		int iQuantity = 0;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = getVatInfo();

		long lUnitAmount = 0;
		if (!unitPrice.getText().isEmpty())
			lUnitAmount = (long) (Double.parseDouble(unitPrice.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecItemRefundVoid(description.getText(), lAmount, iQuantity, iVatInfo,
					lUnitAmount, unitName.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Called to give an adjustment(discount or surcharge) for a package of some
	 * item booked before.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecPackageAdjustment(ActionEvent e) {
		setPrePostLines();
		if (voidSuffix.isSelected()) {
			handlePrintRecPackageAdjustmentVoid(e);
			return;
		}
		try {
			((FiscalPrinter) service).printRecPackageAdjustment(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()), description
					.getText(), adjustment.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Called to void the adjustment(discount or surcharge) for a package of
	 * some items.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecPackageAdjustmentVoid(ActionEvent e) {
		try {
			((FiscalPrinter) service).printRecPackageAdjustVoid(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()), adjustment
					.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Processes a refund.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecRefund(ActionEvent e) {
		setPrePostLines();
		if (voidSuffix.isSelected()) {
			handlePrintRecRefundVoid(e);
			return;
		}
		long lAmount = 0;
		if (!priceAmount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		int iVatInfo = getVatInfo();

		try {
			((FiscalPrinter) service).printRecRefund(description.getText(), lAmount, iVatInfo);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Called to process a void of a refund.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecRefundVoid(ActionEvent e) {
		long lAmount = 0;
		if (!priceAmount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		int iVatInfo = getVatInfo();

		try {
			((FiscalPrinter) service).printRecRefundVoid(description.getText(), lAmount, iVatInfo);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Checks and prints the current receipt subtotal.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecSubtotal(ActionEvent e) {
		setPrePostLines();
		long lAmount = 0;
		if (!priceAmount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecSubtotal(lAmount);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Applies and prints an adjustment (discount or surcharge) to the current
	 * receipt subtotal.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecSubtotalAdjustment(ActionEvent e) {
		setPrePostLines();
		if (voidSuffix.isSelected()) {
			handlePrintRecSubtotalAdjustmentVoid(e);
			return;
		}
		long lAmount = 0;
		if (adjustmentType.getSelectionModel().getSelectedItem().equals(
				FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())
				|| adjustmentType.getSelectionModel().getSelectedItem().equals(
						FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * 10000);
		} else if (!adjustment.getText().isEmpty()) {
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * amountFactorDecimal);
		}

		try {
			((FiscalPrinter) service).printRecSubtotalAdjustment(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()), description
					.getText(), lAmount);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Called to void the adjustment for a package os some items.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecSubtotalAdjustmentVoid(ActionEvent e) {
		long lAmount = 0;
		if (adjustmentType.getSelectionModel().getSelectedItem().equals(
				FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())
				|| adjustmentType.getSelectionModel().getSelectedItem().equals(
						FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * 10000);
		} else if (!adjustment.getText().isEmpty()) {
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * amountFactorDecimal);
		}

		try {
			((FiscalPrinter) service).printRecSubtotalAdjustVoid(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()), lAmount);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Cancels the current receipt.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecVoid(ActionEvent e) {
		setPrePostLines();
		try {
			((FiscalPrinter) service).printRecVoid(description.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Cancels an item that has been added to the receipt and prints a void
	 * description.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecVoidItem(ActionEvent e) {
		setPrePostLines();
		long lPrice = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		long lAmount = 0;
		if (!adjustment.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * amountFactorDecimal);

		int iQuantity = 1;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = getVatInfo();

		try {
			((FiscalPrinter) service).printRecVoidItem(description.getText(), lPrice, iQuantity,
					FiscalPrinterConstantMapper.getConstantNumberFromString(adjustmentType.getSelectionModel()
							.getSelectedItem()), lAmount, iVatInfo);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Prints the customer tax identification
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecTaxId(ActionEvent e) {
		setPrePostLines();
		try {
			((FiscalPrinter) service).printRecTaxID(description.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/**
	 * Prints the current receipt total
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintRecTotal(ActionEvent e) {
		setPrePostLines();
		long lTotal = (long) (Double.parseDouble(priceAmount.getText()) * amountFactorDecimal);

		long lAmount = 0;
		if (!adjustment.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(adjustment.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecTotal(lTotal, lAmount, description.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}

		clearFields();
	}

	/* ********** Fiscal Document - Methods ********** */
	@FXML
	public void handleBeginFiscalDocument(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginFiscalDocument(Integer.parseInt(documentAmount.getText()));
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndFiscalDocument(ActionEvent e) {
		try {
			((FiscalPrinter) service).endFiscalDocument();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleBeginInsertion(ActionEvent e) {
		try {
			// if 0 method begins insertion and then return the appropriate
			// status immediately.
			((FiscalPrinter) service).beginInsertion(0);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndInsertion(ActionEvent e) {
		try {
			((FiscalPrinter) service).endInsertion();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleBeginRemoval(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginRemoval(0);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndRemoval(ActionEvent e) {
		try {
			((FiscalPrinter) service).endRemoval();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 * Prints a line of fiscal text to the slip station.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintFiscalDocumentLine(ActionEvent e) {
		try {
			((FiscalPrinter) service).printFiscalDocumentLine(documentText.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/* ********** Fiscal Report - Methods ********** */
	/**
	 * Prints a report of the fiscal.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintReport(ActionEvent e) {
		try {
			((FiscalPrinter) service).printReport(FiscalPrinterConstantMapper.getConstantNumberFromString(reportType
					.getSelectionModel().getSelectedItem()), reportFrom.getText(), reportTo.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 * Prints a report of all the daily fiscal activities on the receipt. No
	 * data will be written to the fiscal EPROM.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintXReport(ActionEvent e) {
		try {
			((FiscalPrinter) service).printXReport();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 * Prints a report of all the daily fiscal activities on the receipt. Data
	 * will be written to the fiscal EPROM.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintZReport(ActionEvent e) {
		try {
			((FiscalPrinter) service).printZReport();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 * Prints a report of totals for a range of dates on the receipt.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintPeriodicReport(ActionEvent e) {
		try {
			((FiscalPrinter) service).printPeriodicTotalsReport(reportFrom.getText(), reportTo.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/* ********** Non Fiscal Print - Methods ********** */
	@FXML
	public void handleBeginFixedOutput(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginFixedOutput(FiscalPrinterConstantMapper.getConstantNumberFromString(station
					.getSelectionModel().getSelectedItem()), Integer.parseInt(DocumentType.getText()));
		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handlePrintFixedOutput(ActionEvent e) {
		try {
			((FiscalPrinter) service).printFixedOutput(Integer.parseInt(DocumentType.getText()),
					Integer.parseInt(LineNumber.getText()), Data.getText());
		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndFixedOutput(ActionEvent e) {
		try {
			((FiscalPrinter) service).endFixedOutput();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleBeginNonFiscal(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginNonFiscal();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 * Prints data on the Fiscal Printer station.
	 * 
	 * @param e
	 */
	@FXML
	public void handlePrintNormal(ActionEvent e) {
		try {
			((FiscalPrinter) service).printNormal(FiscalPrinterConstantMapper.getConstantNumberFromString(station
					.getSelectionModel().getSelectedItem()), data.getText());
		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndNonFiscal(ActionEvent e) {
		try {
			((FiscalPrinter) service).endNonFiscal();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/* ********** Fiscal Printer Status - Methods ********** */
	@FXML
	public void handleGetData(ActionEvent e) {
		try {
			int dataitem = FiscalPrinterConstantMapper.getConstantNumberFromString(dataItem.getSelectionModel()
					.getSelectedItem());
			int[] optargs;
			try {
				optargs = new int[]{FiscalPrinterConstantMapper.getConstantNumberFromString(optArgs.getSelectionModel()
						.getSelectedItem())};
			}
			catch (NullPointerException e1) {
				optargs = new int[]{0};
			}
			String[] data = new String[]{""};
			((FiscalPrinter)service).getData(dataitem, optargs, data);
			output.setText(data[0]);
		}
		catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 * Forces the Fiscal Printer to return to Monitor state. It also cancels and
	 * closes any operations.
	 * 
	 * @param e
	 */
	@FXML
	public void handleResetPrinter(ActionEvent e) {
		try {
			((FiscalPrinter) service).resetPrinter();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetTotalizer(ActionEvent e) {
		try {
			String[] data = { "" };
			String vatIdString = cbStatusVatID.getSelectionModel().getSelectedItem();
			((FiscalPrinter) service).getTotalizer(
					vatIdString.length() > 0 ? FiscalPrinterConstantMapper.getConstantNumberFromString(vatIdString) : 0,
					FiscalPrinterConstantMapper.getConstantNumberFromString(itemTotalizer.getSelectionModel()
							.getSelectedItem()), data);
			output.setText(data[0]);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetTrainingMode(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getTrainingModeActive());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetErrorInfo(ActionEvent e) {
		try {
			output.setText("error level: " + ((FiscalPrinter) service).getErrorLevel() + "\nerror out id: "
					+ ((FiscalPrinter) service).getErrorOutID() + "\nerror state: "
					+ ((FiscalPrinter) service).getErrorState() + "\nerror station: "
					+ ((FiscalPrinter) service).getErrorStation() + "\nerror: "
					+ ((FiscalPrinter) service).getErrorString());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetOutputID(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getOutputID());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetPrinterStatus(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getPrinterState());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetDayOpened(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getDayOpened());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetRemFisMem(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getRemainingFiscalMemory());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleClearFields(ActionEvent e) {
		directIO_command.clear();
		directIO_data.clear();
		directIO_object.clear();
	}

	// ///////////////////////////////////////
	private void clearFields() {
		description.clear();
		priceAmount.clear();
		quantity.clear();
		adjustment.clear();
		vatInfo.clear();
		unitPrice.clear();
		unitName.clear();
		specialTax.clear();
		specialTaxName.clear();
		voidSuffix.setSelected(false);
		try {
			preLine.setText(((FiscalPrinter)service).getPreLine());
		} catch (JposException e) {
			preLine.clear();
		}
		try {
			postLine.setText(((FiscalPrinter)service).getPostLine());
		} catch (JposException e) {
			postLine.clear();
		}
		handleBusyState();
	}

	private void setPrePostLines() {
		String text = preLine.getText();
		if (text != null && !text.equals("")) {
			try {
				((FiscalPrinter)service).setPreLine(text);
			} catch (JposException e) {
				e.printStackTrace();
			}
		}
		text = postLine.getText();
		if (text != null && !text.equals("")) {
			try {
				((FiscalPrinter)service).setPostLine(text);
			} catch (JposException e) {
				e.printStackTrace();
			}
		}
	}

	private void setUpReceiptStations() {
		boolean supported;
		int current;
		try {
			supported = ((FiscalPrinter)service).getCapFiscalReceiptStation() && ((FiscalPrinter)service).getCapSlpPresent();
			current = ((FiscalPrinter) service).getFiscalReceiptStation();
		} catch (JposException e) {
			supported = false;
			current = FiscalPrinterConst.FPTR_RS_RECEIPT;
		}
		receiptStation.getItems().clear();
		addEntry(receiptStation, FiscalPrinterConstantMapper.FPTR_RS_RECEIPT, current);
		if (supported ) {
			addEntry(receiptStation, FiscalPrinterConstantMapper.FPTR_RS_SLIP, current);
		}
	}

	private void setUpReceiptTypes() {
		int current;
		boolean supported;
		try {
			supported = ((FiscalPrinter)service).getCapFiscalReceiptType();
			current = ((FiscalPrinter) service).getFiscalReceiptType();
		} catch (JposException e) {
			supported = false;
			current = FiscalPrinterConst.FPTR_RT_SALES;
		}
		receiptType.getItems().clear();
		if (supported) {
			addEntry(receiptType, FiscalPrinterConstantMapper.FPTR_RT_CASH_IN, current);
			addEntry(receiptType, FiscalPrinterConstantMapper.FPTR_RT_CASH_OUT, current);
			addEntry(receiptType, FiscalPrinterConstantMapper.FPTR_RT_GENERIC, current);
			addEntry(receiptType, FiscalPrinterConstantMapper.FPTR_RT_SALES, current);
			addEntry(receiptType, FiscalPrinterConstantMapper.FPTR_RT_SERVICE, current);
			addEntry(receiptType, FiscalPrinterConstantMapper.FPTR_RT_SIMPLE_INVOICE, current);
			addEntry(receiptType, FiscalPrinterConstantMapper.FPTR_RT_REFUND, current);
		}
		else {
			addEntry(receiptType, FiscalPrinterConstantMapper.FPTR_RT_SALES, current);
		}
	}

	private void addEntry(ComboBox<String> box, ConstantConverter cvt, int current) {
		box.getItems().add(cvt.getConstant());
		if (current == cvt.getContantNumber())
			box.getSelectionModel().select(cvt.getConstant());
	}

	private void setUpMessageTypes() {
		int current;
		try {
			current = ((FiscalPrinter)service).getMessageType();
		} catch (JposException e) {
			current = FiscalPrinterConst.FPTR_MT_FREE_TEXT;
		}
		messageType.getItems().clear();
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_ADVANCE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_ADVANCE_PAID, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_AMOUNT_TO_BE_PAID, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_AMOUNT_TO_BE_PAID_BACK, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CARD, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CARD_NUMBER, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CARD_TYPE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CASH, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CASH_REGISTER_NUMBER, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CASHIER, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CHANGE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CHEQUE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CLIENT_NUMBER, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CLIENT_SIGNATURE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_COUNTER_STATE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CREDIT_CARD, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CURRENCY, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_CURRENCY_VALUE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_DEPOSIT, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_DEPOSIT_RETURNED, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_DOT_LINE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_DRIVER_NUMB, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_EMPTY_LINE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_FREE_TEXT, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_FREE_TEXT_WITH_DAY_LIMIT, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_GIVEN_DISCOUNT, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_LOCAL_CREDIT, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_MILEAGE_KM, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_NOTE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_PAID, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_PAY_IN, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_POINT_GRANTED, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_POINTS_BONUS, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_POINTS_RECEIPT, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_POINTS_TOTAL, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_PROFITED, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_RATE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_REGISTER_NUMB, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_SHIFT_NUMBER, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_STATE_OF_AN_ACCOUNT, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_SUBSCRIPTION, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_TABLE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_THANK_YOU_FOR_LOYALTY, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_TRANSACTION_NUMB, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_VALID_TO, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_VOUCHER, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_VOUCHER_PAID, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_VOUCHER_VALUE, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_WITH_DISCOUNT, current);
		addEntry(messageType, FiscalPrinterConstantMapper.FPTR_MT_WITHOUT_UPLIFT, current);
	}

	private void setUpAdjustmentType() {
		adjustmentType.getItems().clear();
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_DISCOUNT.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_SURCHARGE.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_COUPON_AMOUNT_DISCOUNT.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT.getConstant());
		adjustmentType.setValue(FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_DISCOUNT.getConstant());
	}

	private void setUpCurrency() {
		cbSetCurrency.getItems().clear();
		cbSetCurrency.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_EUR.getConstant());
		cbSetCurrency.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_AC_EUR.getConstant());
	}

	private void setUpReportType() {
		reportType.getItems().clear();
		reportType.getItems().add(FiscalPrinterConstantMapper.FPTR_RT_ORDINAL.getConstant());
		reportType.getItems().add(FiscalPrinterConstantMapper.FPTR_RT_DATE.getConstant());
		reportType.getItems().add(FiscalPrinterConstantMapper.FPTR_RT_EOD_ORDINAL.getConstant());
		reportType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_RT_ORDINAL.getConstant());
	}

	private void setUpStation() {
		station.getItems().clear();
		try {
			boolean hasNonFiscal = ((FiscalPrinter)service).getCapNonFiscalMode();
			if (((FiscalPrinter)service).getCapFixedOutput() || hasNonFiscal) {
				if (hasNonFiscal && ((FiscalPrinter)service).getCapJrnPresent()) {
					station.getItems().add(FiscalPrinterConstantMapper.FPTR_S_JOURNAL.getConstant());
				}
				station.getItems().add(FiscalPrinterConstantMapper.FPTR_S_RECEIPT.getConstant());
				if (((FiscalPrinter)service).getCapSlpPresent()) {
					station.getItems().add(FiscalPrinterConstantMapper.FPTR_S_SLIP.getConstant());
				}
				station.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_S_RECEIPT.getConstant());
			}
		} catch (JposException e) {
			e.printStackTrace();
		}
	}

	private void setUpItemTotalizer() {
		itemTotalizer.getItems().clear();
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_GROSS.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_NET.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_ITEM.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_ITEM_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_NOT_PAID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_REFUND.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_REFUND_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_VAT.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_VAT_CATEGORY.getConstant());
	}

	private void setUpDataItem() {
		dataItem.getItems().clear();
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_CURRENT_TOTAL.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_DAILY_TOTAL.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_RECEIPT_NUMBER.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_REFUND.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NOT_PAID.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_MID_VOID.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_Z_REPORT.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_GRAND_TOTAL.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_PRINTER_ID.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_FIRMWARE.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_RESTART.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_REFUND_VOID.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NUMB_CONFIG_BLOCK.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NUMB_CURRENCY_BLOCK.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NUMB_HDR_BLOCK.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NUMB_RESET_BLOCK.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NUMB_VAT_BLOCK.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_FISCAL_DOC.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_FISCAL_DOC_VOID.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_FISCAL_REC.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_FISCAL_REC_VOID.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_DOC.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_DOC_VOID.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_REC.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_SIMP_INVOICE.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_TENDER.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_LINECOUNT.getConstant());
		dataItem.getItems().add(FiscalPrinterConstantMapper.FPTR_GD_DESCRIPTION_LENGTH.getConstant());
		optArgs.getItems().clear();
	}

	@FXML
	public void handleDataItem(ActionEvent actionEvent) {
		optArgs.getItems().clear();
		switch (FiscalPrinterConstantMapper.getConstantNumberFromString(dataItem.getSelectionModel()
				.getSelectedItem())) {
			case FiscalPrinterConst.FPTR_GD_TENDER:
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_CASH.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_CHEQUE.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_CHITTY.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_COUPON.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_CURRENCY.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_DRIVEN_OFF.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_EFT_IMPRINTER.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_EFT_TERMINAL.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_TERMINAL_IMPRINTER.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_FREE_GIFT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_GIRO.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_HOME.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_IMPRINTER_WITH_ISSUER.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_LOCAL_ACCOUNT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_LOCAL_ACCOUNT_CARD.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_PAY_CARD.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_PAY_CARD_MANUAL.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_PREPAY.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_PUMP_TEST.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_SHORT_CREDIT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_STAFF.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_PDL_VOUCHER.getConstant());
				break;
			case FiscalPrinterConst.FPTR_GD_LINECOUNT:
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_ITEM.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_ITEM_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_DISCOUNT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_DISCOUNT_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_SURCHARGE.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_SURCHARGE_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_REFUND.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_REFUND_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_DISCOUNT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_DISCOUNT_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_SURCHARGE.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_SURCHARGE_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_COMMENT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_LC_TOTAL.getConstant());
				break;
			case FiscalPrinterConst.FPTR_GD_DESCRIPTION_LENGTH:
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_ITEM.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_ITEM_ADJUSTMENT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_ITEM_FUEL.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_ITEM_FUEL_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_NOT_PAID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_PACKAGE_ADJUSTMENT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_REFUND.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_REFUND_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_SUBTOTAL_ADJUSTMENT.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_TOTAL.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_VOID.getConstant());
				optArgs.getItems().add(FiscalPrinterConstantMapper.FPTR_DL_VOID_ITEM.getConstant());
		}
	}

	private void groupTotalizers() {
		totalizerType.getItems().clear();
		totalizerType.getItems().add(FiscalPrinterConstantMapper.FPTR_TT_DAY.getConstant());
		try {
			if (((FiscalPrinter)service).getCapTotalizerType()) {
				totalizerType.getItems().add(FiscalPrinterConstantMapper.FPTR_TT_RECEIPT.getConstant());
				totalizerType.getItems().add(FiscalPrinterConstantMapper.FPTR_TT_GRAND.getConstant());
				totalizerType.getItems().add(FiscalPrinterConstantMapper.FPTR_TT_DOCUMENT.getConstant());
				switch (((FiscalPrinter)service).getTotalizerType()) {
					case FiscalPrinterConst.FPTR_TT_DOCUMENT:
						totalizerType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_TT_DOCUMENT.getConstant());
						break;
					case FiscalPrinterConst.FPTR_TT_DAY:
						totalizerType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_TT_DAY.getConstant());
						break;
					case FiscalPrinterConst.FPTR_TT_RECEIPT:
						totalizerType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_TT_RECEIPT.getConstant());
						break;
					case FiscalPrinterConst.FPTR_TT_GRAND:
						totalizerType.getSelectionModel().select(FiscalPrinterConstantMapper.FPTR_TT_GRAND.getConstant());
						break;
				}
			}
			else
				totalizerType.getSelectionModel().select(0);
		} catch (JposException e) {
			e.printStackTrace();
		}
	}

	public void handleSetTotalizerType(ActionEvent e) {
		try {
			if (((FiscalPrinter)service).getCapTotalizerType()) {
				((FiscalPrinter) service).setTotalizerType(FiscalPrinterConstantMapper.getConstantNumberFromString(
						totalizerType.getSelectionModel().getSelectedItem()));
			}
		} catch (JposException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent sue) {
		super.statusUpdateOccurred(sue);
		try {
			setUpCoverState();
			setUpPaperState();
			setStatusLabel();
		} catch (JposException e) {
			e.printStackTrace();
		}
	}

	public void handleGetDate(ActionEvent actionEvent) {
		int value = FiscalPrinterConstantMapper.getConstantNumberFromString(DateType.getSelectionModel()
				.getSelectedItem());
		try {
			((FiscalPrinter)service).setDateType(value);
			String[] date = new String[]{""};
			((FiscalPrinter)service).getDate(date);
			output.setText(date[0]);
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	public void handleSetStoreFiscalID(ActionEvent actionEvent) {
		try {
			((FiscalPrinter)service).setStoreFiscalID(fiscalID.getText());
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public void handleSetPOSID(ActionEvent actionEvent) {
		try {
			((FiscalPrinter)service).setPOSID(POSID.getText(), cashierID.getText());
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public void handleSetReceiptType(ActionEvent actionEvent) {
		try {
			String value = receiptType.getSelectionModel().getSelectedItem();
			if (value != null && ((FiscalPrinter)service).getCapFiscalReceiptType()) {
				((FiscalPrinter) service).setFiscalReceiptType(FiscalPrinterConstantMapper.getConstantNumberFromString(
						value
				));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
			setUpReceiptTypes();
		}
	}

	public void handleSetFiscalReceiptStation(ActionEvent actionEvent) {
		try {
			String value = receiptStation.getSelectionModel().getSelectedItem();
			if (value != null && ((FiscalPrinter)service).getCapFiscalReceiptStation()) {
				((FiscalPrinter)service).setFiscalReceiptStation(FiscalPrinterConstantMapper.getConstantNumberFromString(
						value
				));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
			setUpReceiptStations();
		}
	}

	public void handleSetMessageType(ActionEvent actionEvent) {
		try {
			String value = messageType.getSelectionModel().getSelectedItem();
			if (value != null) {
				((FiscalPrinter) service).setMessageType(FiscalPrinterConstantMapper.getConstantNumberFromString(value));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
			setUpMessageTypes();
		}
	}

	private void handleBusyState() {
		try {
			setStatusLabel();
			if (service.getState() != JposConst.JPOS_S_IDLE) {
				((FiscalPrinter)service).setFlagWhenIdle(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void errorOccurred(ErrorEvent errorEvent) {
		setStatusLabel();
		int doit = JposConst.JPOS_S_BUSY;
		try {
			String errortext = ((FiscalPrinter)service).getErrorString();
			doit = JOptionPane.showOptionDialog(null, "Error from printer:\n" + errortext + "\nClear error?", "Printer error",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		} catch (JposException e) {
			e.printStackTrace();
			doit =JOptionPane.YES_OPTION;
		}
		if (doit == JOptionPane.YES_OPTION) {
			errorEvent.setErrorResponse(JposConst.JPOS_ER_CLEAR);
		}
		else
			statusLabel.setText("JPOS_S_BUSY");
	}
}
