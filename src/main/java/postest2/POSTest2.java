package postest2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;

public class POSTest2 extends Application {

	static Stage stage;

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("gui/MainWindow.fxml"));
		Scene scene = new Scene(root, 1200, 790);
		primaryStage.setTitle("JavaPOS POStest 2");
		primaryStage.getIcons().add(new Image("postest2/logo.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
		stage = primaryStage;
	}

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			for (BaseJposControl service : BaseController.Services.values()) {
				try {
					if (service.getState() != JposConst.JPOS_S_CLOSED) {
						service.close();
					}
				} catch (JposException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
