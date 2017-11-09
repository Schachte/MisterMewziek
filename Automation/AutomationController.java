package Automation;

import LoginApp.AccessTypes;
import LoginApp.LoginModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AutomationController implements Initializable {

    @FXML
    private Button dlBTN;

    @FXML
    private TextField songID;

    @FXML
    private Label statusID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void DownloadSong(ActionEvent event) {
        try {
            Stage stage = (Stage)this.dlBTN.getScene().getWindow();
            statusID.setText("Downloading " + songID);
            Automator automate = new Automator();
            if (!songID.getText().equalsIgnoreCase("")) {
                automate.RequestSong(songID.getText());
                statusID.setText("Complete!");

            }

        } catch(Exception localException) {

        }
    }

}
