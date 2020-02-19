package postest2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import javax.swing.JOptionPane;

import jpos.JposConst;
import jpos.JposException;

public abstract class CommonController extends BaseController implements Initializable {

	/* ************************************************************************
	 * *************** Action Handler For Exclusive Use Devices ***************
	 * ************************************************************************
	 */

	// Common
	@FXML
	@RequiredState(JposState.CLAIMED)
	public CheckBox deviceEnabled;

	@FXML
	public void handleOCE(ActionEvent e) {
		if (getDeviceState(service) == JposState.CLOSED) {
			handleOpen(e);
		}
		if (getDeviceState(service) == JposState.OPENED) {
			handleClaim(e);
		}
		if (getDeviceState(service) == JposState.CLAIMED) {
			deviceEnabled.setSelected(true);
			handleDeviceEnable(e);
		}
	}

	@Override
	public void setupGuiObjects() {
		super.setupGuiObjects();
		try {
			deviceEnabled.setSelected(service.getState() != JposConst.JPOS_S_CLOSED && service.getDeviceEnabled());
		} catch (JposException e) {
			deviceEnabled.setSelected(false);
		}
	}
}
