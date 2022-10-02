package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;

public abstract class Controller {
    protected void showWindow(String source, int width, int height) throws IOException {
        showWindow((root) ->  new Scene(root, width, height), source);
    }

    protected void showWindow(String source) throws IOException {
        showWindow(Scene::new, source);
    }

    private void showWindow(Function<Parent, Scene> sceneCreator, String source) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(source));
        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = sceneCreator.apply(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
