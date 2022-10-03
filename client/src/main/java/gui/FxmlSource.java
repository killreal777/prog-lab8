package gui;

public enum FxmlSource {
    MAIN("main.fxml"), AUTHORIZATION("authorization.fxml"),

    MESSAGE("message.fxml"), RECONNECTING("reconnecting.fxml"),

    ADD("add.fxml"), ADD_IF_MAX("add_if_max.fxml"), UPDATE("update.fxml");

    private final String fileName;

    FxmlSource(String fileName) {
        this.fileName = fileName;
    }

    public String get() {
        return fileName;
    }
}
