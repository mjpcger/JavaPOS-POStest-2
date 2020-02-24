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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javafx.scene.text.Text;
import jpos.*;

import jpos.events.*;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ScaleController extends CommonController implements Initializable, ErrorListener {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;

	@FXML
	public TextField tareWeight;
	@FXML
	public TextField unitPrice;
	@FXML
	public TextField displayText_data;
	@FXML
	public TextField readWeight_weightData;
	@FXML
	public TextField salesPrice;
	@FXML
	public TextField readWeight_timeout;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Label lastStatusLabel;
	@FXML
	public Text lastStatus;
	@FXML
	@RequiredState(JposState.OPENEDNOTENABLED)
	public CheckBox statusNotify;
	@FXML
	public ComboBox<Boolean> zeroValid;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new Scale();
		((Scale)service).addStatusUpdateListener(this);
		((Scale)service).addDataListener(this);
		((Scale)service).addErrorListener(this);
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("Scale");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((Scale) service).setDeviceEnabled(true);
				if (tareWeight.getText().equals("")){
					tareWeight.setText(Integer.toString(((Scale)service).getTareWeight()));
				}
				if (unitPrice.getText().equals("")){
					unitPrice.setText(Long.toString(((Scale)service).getUnitPrice()));
				}
			} else {
				((Scale) service).setDeviceEnabled(false);
				lastStatus.setText("");
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
		RequiredStateChecker.invokeThis(this, service);
		setupGuiObjects();
	}

	@Override
	public void handleClose(ActionEvent e) {
		super.handleClose(e);
		tareWeight.setText("");
		unitPrice.setText("");
		salesPrice.setText("");
		asyncMode.setSelected(false);
		lastStatus.setText("");
	}

	@Override
	public void handleClaim(ActionEvent e) {
		super.handleClaim(e);
		try {
			if (!((Scale) service).getCapStatusUpdate()) {
				statusNotify.setDisable(true);
			}
		} catch (JposException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void handleRelease(ActionEvent e) {
		super.handleRelease(e);
		try {
			if (!((Scale) service).getCapStatusUpdate()) {
				statusNotify.setDisable(true);
			}
			lastStatus.setText("");
		} catch (JposException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void handleOpen(ActionEvent e) {
		super.handleOpen(e);
		try {
			if (((Scale) service).getCapStatusUpdate()) {
				setUpStatusNotify();
			}
			else {
				statusNotify.setDisable(true);
			}
		} catch (JposException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper scm = new ScaleConstantMapper();
			String msg = DeviceProperties.getProperties(service, scm);
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
			((Scale) service).retrieveStatistics(stats);
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
	public void handleAsyncMode(ActionEvent e) {
		try {
			((Scale) service).setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetStatusNotify(ActionEvent e) {
		try {
			((Scale) service).setStatusNotify(statusNotify.isSelected() ?
					ScaleConst.SCAL_SN_ENABLED : ScaleConst.SCAL_SN_DISABLED);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
			statusNotify.setSelected(!statusNotify.isSelected());
		}
	}

	@FXML
	public void handleSetTareWeight(ActionEvent e) {
		if (tareWeight.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale) service).setTareWeight(Integer.parseInt(tareWeight.getText()));
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
	public void handleSetUnitPrice(ActionEvent e) {
		if (unitPrice.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale) service).setUnitPrice(Long.parseLong(unitPrice.getText()));
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
	public void handleSetZeroValid(ActionEvent e) {
		try {
			((Scale) service).setZeroValid(zeroValid.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleDisplayText(ActionEvent e) {
		if (displayText_data.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale) service).displayText(displayText_data.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleReadWeight(ActionEvent e) {
		if (readWeight_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			int[] weightData = new int[1];
			try {
				((Scale) service).readWeight(weightData, Integer.parseInt(readWeight_timeout.getText()));
				readWeight_weightData.setText("" + weightData[0]);
				if (asyncMode.selectedProperty().getValue()){
					setStatusLabel();
				}
				else {
					salesPrice.setText("" + ((Scale) service).getSalesPrice());
				}
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleZeroScale(ActionEvent e) {
		try {
			((Scale) service).zeroScale();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * Set up ComboBoxes
	 */

	private void setUpStatusNotify() {
		ScaleConstantMapper mapper = new ScaleConstantMapper();
		if ("true".equals(DeviceProperties.getPropertyValue(service, mapper, "getCapStatusUpdate"))) {
			String current = DeviceProperties.getPropertyValue(service, mapper, "getStatusNotify");
			statusNotify.setSelected(ScaleConstantMapper.SCAL_SN_ENABLED.getConstant().equals(current));
			if (!ScaleConstantMapper.SCAL_SN_ENABLED.getConstant().equals(current)) {
				lastStatusLabel.setDisable(true);
			}
		} else {
			lastStatusLabel.setDisable(true);
			statusNotify.setDisable(true);
		}
	}

	private void setUpZeroValid() {
		zeroValid.getItems().clear();
		zeroValid.getItems().add(true);
		zeroValid.getItems().add(false);
		zeroValid.getSelectionModel().select(
				"true".equals(DeviceProperties.getPropertyValue(service, new CommonConstantMapper(), "getZeroValid")));
	}

	@Override
	public void setupGuiObjects() {
		super.setupGuiObjects();
		setUpSalesPrice();
		setUpStatusNotify();
		setUpZeroValid();
	}

	private void setUpSalesPrice() {
		salesPrice.setText(DeviceProperties.getPropertyValue(service, new ScaleConstantMapper(), "getSalesPrice"));
		salesPrice.setEditable(false);
	}

	@Override
	public void dataOccurred(DataEvent e) {
		super.dataOccurred(e);
		readWeight_weightData.setText("" + e.getStatus());
		try {
			salesPrice.setText("" + ((Scale) service).getSalesPrice());
		} catch (JposException ex) {
			ex.printStackTrace();
		}
		setStatusLabel();
	}

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent e) {
		super.statusUpdateOccurred(e);
		switch (e.getStatus()) {
			case ScaleConst.SCAL_SUE_STABLE_WEIGHT:
				lastStatus.setText("STABLE_WEIGHT");
				break;
			case ScaleConst.SCAL_SUE_WEIGHT_UNSTABLE:
				lastStatus.setText("WEIGHT_UNSTABLE");
				break;
			case ScaleConst.SCAL_SUE_WEIGHT_ZERO:
				lastStatus.setText("WEIGHT_ZERO");
				break;
			// case ScaleConst.SCAL_SUE_WEIGHT_UNDERWEIGHT: Constant missing in JavaPOS implementation.
			case ScaleConst.SCAL_SUE_WEIGHT_OVERWEIGHT:
				lastStatus.setText("WEIGHT_OVERWEIGHT");
				break;
			case ScaleConst.SCAL_SUE_NOT_READY:
				lastStatus.setText("NOT_READY");
				break;
			case ScaleConst.SCAL_SUE_WEIGHT_UNDER_ZERO:
				lastStatus.setText("WEIGHT_UNDER_ZERO");
				break;
		}
	}

	@Override
	public void errorOccurred(ErrorEvent e) {
		setStatusLabel();
		JOptionPane.showMessageDialog(null, "Asynchronous operation failed: " + e.getErrorCode() + "/" + e.getErrorCodeExtended());
		try {
			((Scale)service).clearInput();
		} catch (JposException ex) {
			ex.printStackTrace();
		}
		setStatusLabel();
	}
}