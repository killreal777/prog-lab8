package gui;

import client.Connector;
import exceptions.ConnectionException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import requestes.CommandRequest;
import response.Result;

import java.io.IOException;

public abstract class Controller {
    protected final static Connector connector = new Connector();

    protected Result executeCommandOnServer(CommandRequest request) throws ConnectionException {
        connectIfNotConnected();
        return connector.executeCommandOnServer(request);
    }

    private void connectIfNotConnected() throws ConnectionException {
        if (!connector.isConnected())
            reconnect();
    }

    private void reconnect() throws ConnectionException {
        try {
            connector.connect();
        } catch (ConnectionException e) {
            showWindow(FxmlSource.RECONNECTING.get());
        }
    }

    protected void showWindow(String source) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(source));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
