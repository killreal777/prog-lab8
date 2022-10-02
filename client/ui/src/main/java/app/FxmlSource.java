package app;

public enum FxmlSource {
    MAIN("main.fxml"), AUTHORIZATION("authorization.fxml");

    private final String fileName;

    FxmlSource(String fileName) {
        this.fileName = fileName;
    }

    public String get() {
        return fileName;
    }
}
