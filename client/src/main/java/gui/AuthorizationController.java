package gui;

import app.PasswordCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import requestes.AuthorizationRequest;
import requestes.CommandRequest;
import requestes.RegistrationRequest;
import response.Result;

import java.io.IOException;

public class AuthorizationController extends Controller {
    private final PasswordCrypt crypt;

    public AuthorizationController() {
        this.crypt = new PasswordCrypt();
    }

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
        Result result = executeAuthorizationRequest(new AuthorizationRequest());
        if (!result.isSuccessful())
            return;
        singInButton.getScene().getWindow().hide();
        showWindow(FxmlSource.MAIN.get());
    }

    @FXML
    void onSignUpButtonClick(ActionEvent event) {
        Result result = executeAuthorizationRequest(new RegistrationRequest());
        if (!result.isSuccessful())
            return;
        singInButton.getScene().getWindow().hide();
        showWindow(FxmlSource.MAIN.get());
    }

    private Result executeAuthorizationRequest(CommandRequest request) {
        request.setUserName(loginField.getText());
        String decryptedPassword = crypt.cryptPassword(passwordField.getText());
        request.setCommandArgs(new String[]{decryptedPassword});
        return executeCommandOnServer(request);
    }
}
