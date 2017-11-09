package User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.DragEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    public void handleSongDrop(DragEvent event) {
        System.out.println(event.getTarget());
    }

//    @FXML
//    public void hoverMouse(Hover event) {
//        System.out.println("Hover");
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
