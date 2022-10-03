package cui;

import app.PasswordCrypt;
import requestes.CommandRequest;
import client.ConnectorCui;
import io.Format;
import io.Terminal;
import io.TextFormatter;
import requestes.AuthorizationRequest;
import requestes.RegistrationRequest;

import java.util.Arrays;
import java.util.function.Consumer;

public class AuthorizationManager {
    private final Terminal terminal;
    private final ConnectorCui connector;
    private final Consumer<String> setUserName;
    private final PasswordCrypt crypt;
    private String userName;

    public AuthorizationManager(Terminal terminal, ConnectorCui connector, Consumer<String> setUserName) {
        this.terminal = terminal;
        this.connector = connector;
        this.setUserName = setUserName;
        this.crypt = new PasswordCrypt();
    }

    public void login() {
        CommandRequest request = prepareRequest();
        configureRequest(request);
        String response = connector.executeCommandOnServer(request).get().getMessage();
        parseResponse(response);
    }

    private CommandRequest prepareRequest() {
        switch (readStartCommand()) {
            case "a":
                return new AuthorizationRequest();
            case "r":
                return new RegistrationRequest();
            default:
                throw new RuntimeException();
        }
    }

    private String readStartCommand() {
        String input = terminal.readLineEntire("Введите \"a\" для авторизации или \"r\" для регистрации: ");
        while (!input.equals("a") && !input.equals("r"))
            input = terminal.readLineEntire(TextFormatter.format("Вам доступны только команды \"а\" и \"r\": ", Format.RED));
        return input;
    }

    private void configureRequest(CommandRequest request) {
        userName = terminal.readLineEntire(TextFormatter.format("Login: ", Format.YELLOW));
        //String password = terminal.readLineEntire("Password: ");
        String password = crypt.cryptPassword(terminal.readLineEntire(TextFormatter.format("Password: ", Format.YELLOW)));
        System.out.println(password);
        request.setUserName(userName);
        request.setCommandArgs(new String[]{password});
    }

    private void parseResponse(String response) {
        terminal.print(response);
        if (responseIsNotGood(response))
            login();
        else
            setUserName.accept(userName);
    }

    private boolean responseIsNotGood(String response) {
        return !Arrays.asList(response.split(" ")).contains("вошли");
    }
}
