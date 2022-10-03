package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController extends Controller {

    @FXML
    private Button addButton;

    @FXML
    private Button addIfMaxButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button executeScriptButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button filterStartsWithNameButton;

    @FXML
    private Button headButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button languageButton;

    @FXML
    private Button removeByAddressButton;

    @FXML
    private Button removeByIdButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button userButton;

    @FXML
    void onAddButtonClick(ActionEvent event) {
        showWindow(FxmlSource.ADD.get());
    }

    @FXML
    void onAddIfMaxButtonClick(ActionEvent event) {

    }

    @FXML
    void onClearButtonClick(ActionEvent event) {

    }

    @FXML
    void onExecuteScriptButtonClick(ActionEvent event) {

    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onFilterStartsWIthNameButtonClick(ActionEvent event) {

    }

    @FXML
    void onHeadButtonClick(ActionEvent event) {

    }

    @FXML
    void onHelpButtonClick(ActionEvent event) {

    }

    @FXML
    void onInfoButtonClick(ActionEvent event) {

    }

    @FXML
    void onLanguageButtonClick(ActionEvent event) {

    }

    @FXML
    void onRemoveByAddressButtonClick(ActionEvent event) {

    }

    @FXML
    void onRemoveByIdButtonClick(ActionEvent event) {

    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {
        showWindow(FxmlSource.UPDATE.get());
    }

    @FXML
    void onUserButtonClick(ActionEvent event) {

    }
}

