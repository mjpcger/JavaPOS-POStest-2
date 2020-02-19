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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposConst;
import jpos.JposException;
import jpos.Lights;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LightsController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	public TextField switchOn_blinkOnCycle;
	@FXML
	public TextField switchOn_blinkOffCycle;

	@FXML
	public ComboBox<Integer> switchOn_lightNumber;
	@FXML
	public ComboBox<String> switchOn_color;
	@FXML
	public ComboBox<String> switchOn_alarm;
	@FXML
	public ComboBox<Integer> switchOff_lightNumber;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new Lights();
		((Lights) service).addStatusUpdateListener(this);
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("Lights");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((Lights) service).setDeviceEnabled(true);
			} else {
				((Lights) service).setDeviceEnabled(false);
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
			IMapWrapper lcm = new LightsConstantMapper();
			String msg = DeviceProperties.getProperties(service, lcm);
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
			((Lights) service).retrieveStatistics(stats);
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
	public void handleSwitchOn(ActionEvent e) {
		if (switchOn_blinkOffCycle.getText().isEmpty() || switchOn_blinkOnCycle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((Lights) service).switchOn(switchOn_lightNumber.getSelectionModel().getSelectedItem(), Integer
						.parseInt(switchOn_blinkOnCycle.getText()), Integer.parseInt(switchOn_blinkOnCycle.getText()),
						LightsConstantMapper.getConstantNumberFromString(switchOn_color.getSelectionModel()
								.getSelectedItem()), LightsConstantMapper.getConstantNumberFromString(switchOn_alarm
								.getSelectionModel().getSelectedItem()));
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
	public void handleSwitchOff(ActionEvent e) {
		try {
			((Lights) service).switchOff(switchOff_lightNumber.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * SetUpComboBoxes
	 */

	private void setUpSwitchOnLightNumber() {
		int count = 0;
		int previous = switchOn_lightNumber.getValue();
		try {
			count = ((Lights) service).getMaxLights();
		} catch (JposException e) {
			if (service.getState() != JposConst.JPOS_S_CLOSED) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
		switchOn_lightNumber.getItems().clear();
		for (int i = 1; i <= count; i++) {
			switchOn_lightNumber.getItems().add(i);
		}
		if (count > 0)
			switchOn_lightNumber.setValue(previous > 0 && previous <= count ? previous : 1);
	}

	private void setUpSwitchOnColor() {
		String previous = switchOn_color.getValue();
		switchOn_color.getItems().clear();
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_PRIMARY.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM1.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM2.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM3.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM4.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM5.getConstant());
		switchOn_color.setValue(previous == null || previous.equals("")
				? LightsConstantMapper.LGT_COLOR_PRIMARY.getConstant()
				: previous);
	}

	private void setUpSwitchOnAlarm() {
		String previous = switchOn_alarm.getValue();
		switchOn_alarm.getItems().clear();
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_NOALARM.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_SLOW.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_MEDIUM.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_FAST.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_CUSTOM1.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_CUSTOM2.getConstant());
		switchOn_alarm.setValue(previous == null || previous.equals("")
				? LightsConstantMapper.LGT_ALARM_NOALARM.getConstant()
				: previous);
	}

	private void setUpSwitchOffLightNumber() {
		int count = 0;
		int previous = switchOff_lightNumber.getValue();
		try {
			count = ((Lights) service).getMaxLights();
		} catch (JposException e) {
			if (service.getState() != JposConst.JPOS_S_CLOSED) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
		switchOff_lightNumber.getItems().clear();
		for (int i = 1; i <= count; i++) {
			switchOff_lightNumber.getItems().add(i);
		}
		if (count > 0)
			switchOff_lightNumber.setValue(previous > 0 && previous <= count ? previous : 1);
	}

	@Override
	public void setupGuiObjects() {
		super.setupGuiObjects();
		setUpSwitchOffLightNumber();
		setUpSwitchOnAlarm();
		setUpSwitchOnColor();
		setUpSwitchOnLightNumber();
	}

}