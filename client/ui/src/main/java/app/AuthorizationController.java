package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthorizationController extends Controller {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button singInButton;

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        singInButton.getScene().getWindow().hide();
    }

    @FXML
    void onSignInButtonClick(ActionEvent event) {
        try {
            singInButton.getScene().getWindow().hide();
            showWindow(FxmlSource.MAIN.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSignUpButtonClick(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        System.out.println(login + " " + password);
    }
}
