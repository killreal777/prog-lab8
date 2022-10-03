package gui;

import exceptions.ConnectionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ReconnectingController extends Controller {

    @FXML
    private Label messageLabel;

    @FXML
    private Button reconnectButton;

    @FXML
    void onReconnectButtonClick(ActionEvent event) {
        try {
            connector.connect();
            reconnectButton.getScene().getWindow().hide();
        } catch (ConnectionException e) {
            messageLabel.setText("Server is still anavaliable... Try again");
        }
    }

}

