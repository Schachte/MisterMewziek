package LoginApp;

import Admin.AdminController;
import User.UserController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbStatus;
    @FXML
    private TextField userField;
    @FXML
    private TextField pwdField;
    @FXML
    private ComboBox<AccessTypes> divAccess;
    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbStatus.setText("DB Connection Successful!");
        }
        else {
            this.userField.setEditable(false);
            this.pwdField.setEditable(false);
            this.loginBtn.setDisable(true);
            this.dbStatus.setText("DB Disconnected.");
        }

        this.divAccess.setItems(FXCollections.observableArrayList(AccessTypes.values()));
    }

    @FXML
    public void Login(ActionEvent event) {
        try {
            Stage stage = (Stage)this.loginBtn.getScene().getWindow();
            userLogin();

        } catch(Exception localException) {

        }
    }

    /**
     * Loads the User Dashboard
     */
    public void userLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/User/userFXML.FXML").openStream());

            UserController userControl = (UserController)loader.getController();
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("User Dashboard");
            userStage.setResizable(false);
            userStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the Admin Dashboard
     */
    public void adminLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Admin/adminFXML.fxml").openStream());

            AdminController adminControl = (AdminController)loader.getController();
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Admin Dashboard");
            userStage.setResizable(false);
            userStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
