package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.MSR;

import jpos.MSRConst;
import jpos.events.DataEvent;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MSRController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;
	
	@FXML
	@RequiredState(JposState.CLAIMED)
	public TabPane notEnabledTab;

	@FXML
	public TextField writeCardType;
	@FXML
	public TextField authenticateDevice_response;
	@FXML
	public TextField deauthenticateDevice_response;
	@FXML
	public TextField retrieveCardProperty_name;
	@FXML
	public TextField retrieveCardProperty_value;
	@FXML
	public TextField retrieveDeviceAuthenticationData_challenge;
	@FXML
	public TextField updateKey_key;
	@FXML
	public TextField updateKey_keyName;
	@FXML
	public TextField writeTracks_addData;
	@FXML
	public TextField writeTracks_timeout;

	@FXML
	public ComboBox<String> showData;
	@FXML
	public ComboBox<String> dataEncryptionAlgorithm;
	@FXML
	public ComboBox<Boolean> decodeData;
	@FXML
	public ComboBox<String> errorReportingType;
	@FXML
	public ComboBox<Boolean> parseDecodeData;
	@FXML
	public ComboBox<String> tracksToWrite;
	@FXML
	public ComboBox<String> tracksToRead;
	@FXML
	public ComboBox<Boolean> transmitSentinels;
	@FXML
	public ListView<String> writeTracks_data;
	@FXML
	public ListView<String> trackData;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		setUpShowData();
		service = new MSR();
		((MSR) service).addStatusUpdateListener(this);
		((MSR) service).addDataListener(this);
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("MSR");
	}

	private void setUpShowData() {
		String current = showData.getValue();
		showData.getItems().clear();
		showData.getItems().add("Hexadecimal");
		showData.getItems().add("ASCII");
		showData.getItems().add("Decoded");
		showData.getItems().add("Length");
		showData.getSelectionModel().select(current == null ? "ASCII" : current);
	}

	@FXML
	public void handleShowData(ActionEvent actionEvent) {
		String conversion = showData.getValue();
		if ("Hexadecimal".equals(conversion)) {
			DeviceProperties.setConversion(service, DeviceProperties.ByteConversion.Hexadecimal);
		} else if ("ASCII".equals(conversion)) {
			DeviceProperties.setConversion(service, DeviceProperties.ByteConversion.Ascii);
		} else if ("Length".equals(conversion)) {
			DeviceProperties.setConversion(service, DeviceProperties.ByteConversion.Length);
		} else if ("Decoded".equals(conversion)) {
			DeviceProperties.setConversion(service, DeviceProperties.ByteConversion.Decoded);
		}
		setUpTrackData();
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((MSR) service).setDeviceEnabled(true);
			} else {
				((MSR) service).setDeviceEnabled(false);
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
			IMapWrapper msrcm = new MSRConstantMapper();
			String msg = DeviceProperties.getProperties(service, msrcm);
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
			((MSR) service).retrieveStatistics(stats);
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
			e1.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
	}

	@FXML
	public void handleSetDataEncryptionAlgorithm(ActionEvent e) {
		try {
			((MSR)service).setDataEncryptionAlgorithm(MSRConstantMapper.getConstantNumberFromString(dataEncryptionAlgorithm.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetDecodeData(ActionEvent e) {
		try {
			((MSR)service).setDecodeData(decodeData.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
		setUpTrackData();
	}

	@FXML
	public void handleSetErrorReportingType(ActionEvent e) {
		try {
			((MSR)service).setErrorReportingType(MSRConstantMapper.getConstantNumberFromString(errorReportingType.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetParseDecodeData(ActionEvent e) {
		try {
			((MSR)service).setParseDecodeData(parseDecodeData.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
		setUpTrackData();
	}

	@FXML
	public void handleSetTracksToRead(ActionEvent e) {
		try {
			((MSR)service).setTracksToRead(MSRConstantMapper.getConstantNumberFromString(tracksToRead.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
		setUpTrackData();
	}

	@FXML
	public void handleSetTracksToWrite(ActionEvent e) {
		try {
			((MSR)service).setTracksToWrite(MSRConstantMapper.getConstantNumberFromString(tracksToWrite.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTransmitSentinels(ActionEvent e) {
		try {
			((MSR)service).setTransmitSentinels(transmitSentinels.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
		setUpTrackData();
	}

	@FXML
	public void handleSetWriteCardType(ActionEvent e) {
		if(writeCardType.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).setWriteCardType(writeCardType.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBrowseAuthenticateDeviceResponse(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose File");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			authenticateDevice_response.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleAuthenticatedevice(ActionEvent e) {
		if(authenticateDevice_response.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).authenticateDevice(readBytesFromFile(authenticateDevice_response.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBrowseDeauthenticateDeviceResponse(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose File");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			deauthenticateDevice_response.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleDeauthenticateDevice(ActionEvent e) {
		if(deauthenticateDevice_response.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).deauthenticateDevice(readBytesFromFile(deauthenticateDevice_response.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleRetrieveCardProperty(ActionEvent e) {
		if(retrieveCardProperty_name.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			String[] value = new String[1];
			try {
				((MSR)service).retrieveCardProperty(retrieveCardProperty_name.getText(), value);
				retrieveCardProperty_value.setText(value[0]);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBrowseRetrieveDeviceAuthenticationDataChallenge(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose File");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			retrieveDeviceAuthenticationData_challenge.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleRetrieveDeviceAuthenticationData(ActionEvent e) {
		if(retrieveDeviceAuthenticationData_challenge.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).retrieveDeviceAuthenticationData(readBytesFromFile(retrieveDeviceAuthenticationData_challenge.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleUpdateKey(ActionEvent e) {
		if(updateKey_key.getText().isEmpty() || updateKey_keyName.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).updateKey(updateKey_key.getText(), updateKey_keyName.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBrowseWriteTracksAddData(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose File");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			writeTracks_addData.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleWriteTracks(ActionEvent e) {
		if(writeTracks_timeout.getText().isEmpty() || writeTracks_data.getItems().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			byte[][] data = new byte[writeTracks_data.getItems().size()][];
			for(int i = 0; i < data.length; i++){
				data[i] = readBytesFromFile(writeTracks_data.getItems().get(i));
			}
			try {
				((MSR)service).writeTracks(data, Integer.parseInt(writeTracks_timeout.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleWriteTracksAddData(ActionEvent e) {
		if(!writeTracks_addData.getText().isEmpty()){
			writeTracks_data.getItems().add(writeTracks_addData.getText());
		}
	}

	@FXML
	public void handleDataEventEnable(ActionEvent actionEvent) {
		try {
			((MSR) service).setDataEventEnabled(dataEventEnabled.isSelected());
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
			dataEventEnabled.setSelected(!dataEventEnabled.isSelected());
		}
	}

	/*
	 * Set Up ComboBoxes
	 */
	
	private void setUpDataEncryptionAlgorithm(){
		dataEncryptionAlgorithm.getItems().clear();
		dataEncryptionAlgorithm.getItems().add(MSRConstantMapper.MSR_DE_3DEA_DUKPT.getConstant());
		dataEncryptionAlgorithm.getItems().add(MSRConstantMapper.MSR_DE_NONE.getConstant());
		String current = DeviceProperties.getPropertyValue(service, new MSRConstantMapper(), "getDataEncryptionAlgorithm");
		dataEncryptionAlgorithm.getSelectionModel().select(current);
		
	}
	
	private void setUpDecodeData(){
		boolean current = false;
		try {
			current = ((MSR)service).getDecodeData();
		} catch (JposException e) {
			if (getDeviceState(service) != JposState.CLOSED) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
		decodeData.getItems().clear();
		decodeData.getItems().add(true);
		decodeData.getItems().add(false);
		decodeData.getSelectionModel().select(current);
	}
	
	private void setUpErrorReportingType(){
		errorReportingType.getItems().clear();
		errorReportingType.getItems().add(MSRConstantMapper.MSR_ERT_CARD.getConstant());
		errorReportingType.getItems().add(MSRConstantMapper.MSR_ERT_TRACK.getConstant());
		String current = DeviceProperties.getPropertyValue(service, new MSRConstantMapper(), "getErrorReportingType");
		errorReportingType.getSelectionModel().select(current);
	}
	
	private void setUpParseDecodeData(){
		boolean current = false;
		try {
			current = ((MSR)service).getParseDecodeData();
		} catch (JposException e) {
			if (getDeviceState(service) != JposState.CLOSED) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
		parseDecodeData.getItems().clear();
		parseDecodeData.getItems().add(true);
		parseDecodeData.getItems().add(false);
		parseDecodeData.getSelectionModel().select(current);
	}
	
	private void setUpTracksToWrite(){
		String current = DeviceProperties.getPropertyValue(service, new MSRConstantMapper(), "getTracksToWrite");
		int capwrite = 0;
		try {
			capwrite = ((MSR)service).getCapWritableTracks();
		} catch (JposException e) {
			if (getDeviceState(service) == JposState.ENABLED) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
		tracksToWrite.getItems().clear();
		if ((capwrite & MSRConst.MSR_TR_NONE) == MSRConst.MSR_TR_NONE)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_NONE.getConstant());
		if ((capwrite & MSRConst.MSR_TR_1) == MSRConst.MSR_TR_1)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1.getConstant());
		if ((capwrite & MSRConst.MSR_TR_1_2) == MSRConst.MSR_TR_1_2)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_2.getConstant());
		if ((capwrite & MSRConst.MSR_TR_1_2_3) == MSRConst.MSR_TR_1_2_3)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_2_3.getConstant());
		if ((capwrite & MSRConst.MSR_TR_1_2_3_4) == MSRConst.MSR_TR_1_2_3_4)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_2_3_4.getConstant());
		if ((capwrite & MSRConst.MSR_TR_1_2_4) == MSRConst.MSR_TR_1_2_4)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_2_4.getConstant());
		if ((capwrite & MSRConst.MSR_TR_1_3) == MSRConst.MSR_TR_1_3)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_3.getConstant());
		if ((capwrite & MSRConst.MSR_TR_1_3_4) == MSRConst.MSR_TR_1_3_4)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_3_4.getConstant());
		if ((capwrite & MSRConst.MSR_TR_1_4) == MSRConst.MSR_TR_1_4)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_4.getConstant());
		if ((capwrite & MSRConst.MSR_TR_2) == MSRConst.MSR_TR_2)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_2.getConstant());
		if ((capwrite & MSRConst.MSR_TR_2_3) == MSRConst.MSR_TR_2_3)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_2_3.getConstant());
		if ((capwrite & MSRConst.MSR_TR_2_3_4) == MSRConst.MSR_TR_2_3_4)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_2_3_4.getConstant());
		if ((capwrite & MSRConst.MSR_TR_2_4) == MSRConst.MSR_TR_2_4)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_2_4.getConstant());
		if ((capwrite & MSRConst.MSR_TR_3) == MSRConst.MSR_TR_3)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_3.getConstant());
		if ((capwrite & MSRConst.MSR_TR_3_4) == MSRConst.MSR_TR_3_4)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_3_4.getConstant());
		if ((capwrite & MSRConst.MSR_TR_4) == MSRConst.MSR_TR_4)
			tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_4.getConstant());
		tracksToWrite.getSelectionModel().select(current);
	}
	
	private void setUpTracksToRead(){
		tracksToRead.getItems().clear();
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_2.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_2_3.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_2_3_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_2_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_3.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_3_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_2.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_2_3.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_2_3_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_2_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_3.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_3_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_NONE.getConstant());
		String current = DeviceProperties.getPropertyValue(service, new MSRConstantMapper(), "getTracksToRead");
		tracksToRead.getSelectionModel().select(current);
	}
	
	private void setUpTransmitSentinels(){
		boolean current = false;
		try {
			current = ((MSR)service).getTransmitSentinels();
		} catch (JposException e) {
			if (getDeviceState(service) != JposState.CLOSED) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
		transmitSentinels.getItems().clear();
		transmitSentinels.getItems().add(true);
		transmitSentinels.getItems().add(false);
		transmitSentinels.setValue(current);
	}	

	@Override
	public void setupGuiObjects(){
		super.setupGuiObjects();
		setUpDataEncryptionAlgorithm();
		setUpDecodeData();
		setUpErrorReportingType();
		setUpParseDecodeData();
		setUpTracksToRead();
		setUpTracksToWrite();
		setUpTransmitSentinels();
		setUpListViews();
		setUpTrackData();
	}

	private void setUpTrackData() {
		MSRConstantMapper mapper = new MSRConstantMapper();
		trackData.getItems().clear();
		trackData.setStyle("-fx-font-family: 'monospaced';");
		boolean encrypted = false;
		int availabletracks = 0;
		try {
			encrypted = (((MSR)service).getCapDataEncryption() != MSRConst.MSR_DE_NONE ?
					((MSR)service).getDataEncryptionAlgorithm() != MSRConst.MSR_DE_NONE : false) &&
					((MSR)service).getCapTrackDataMasking() == false;
			availabletracks = ((MSR)service).getTracksToRead();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((availabletracks & MSRConst.MSR_TR_1) == 0)
			trackData.getItems().add("");
		else if (encrypted)
			trackData.getItems().add("[" +
					DeviceProperties.getPropertyValue(service, mapper, "getTrack1EncryptedDataLength") +
					" encrypted bytes]");
		else
			trackData.getItems().add(DeviceProperties.getPropertyValue(service, mapper, "getTrack1Data"));
		if ((availabletracks & MSRConst.MSR_TR_2) == 0)
			trackData.getItems().add("");
		else if (encrypted)
			trackData.getItems().add("[" +
					DeviceProperties.getPropertyValue(service, mapper, "getTrack2EncryptedDataLength") +
					" encrypted bytes]");
		else
			trackData.getItems().add(DeviceProperties.getPropertyValue(service, mapper, "getTrack2Data"));
		if ((availabletracks & MSRConst.MSR_TR_3) == 0)
			trackData.getItems().add("");
		else if (encrypted)
			trackData.getItems().add("[" +
					DeviceProperties.getPropertyValue(service, mapper, "getTrack3EncryptedDataLength") +
					" encrypted bytes]");
		else
			trackData.getItems().add(DeviceProperties.getPropertyValue(service, mapper, "getTrack3Data"));
		if ((availabletracks & MSRConst.MSR_TR_4) == 0)
			trackData.getItems().add("");
		else if (encrypted)
			trackData.getItems().add("[" +
					DeviceProperties.getPropertyValue(service, mapper, "getTrack4EncryptedDataLength") +
					" encrypted bytes]");
		else
			trackData.getItems().add(DeviceProperties.getPropertyValue(service, mapper, "getTrack4Data"));
	}

	private void setUpListViews(){
		writeTracks_data.getItems().clear();
	}

	@Override
	public void dataOccurred(DataEvent ev) {
		super.dataOccurred(ev);
		setUpTrackData();
	}
}
