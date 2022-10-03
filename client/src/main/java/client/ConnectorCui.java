package client;

import exceptions.ConnectionException;
import requestes.CommandRequest;
import exceptions.DeserializationException;
import io.Format;
import io.Terminal;
import io.TextFormatter;
import response.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConnectorCui {
    private final Terminal terminal;
    private final Client client;

    public ConnectorCui(Terminal terminal) {
        this.terminal = terminal;
        this.client = new Client();
        connect();
    }

    public Optional<Result> executeCommandOnServer(CommandRequest request) {
        try {
            return Optional.ofNullable(client.executeCommandOnServer(request));
        } catch (IOException | DeserializationException e) {
            reconnect();
            return Optional.empty();
        }
    }

    private void connect() {
        try {
            client.connect();
            terminal.print(TextFormatter.format("Соединение установлено", Format.GREEN));
        } catch (ConnectionException e) {
            reconnect();
        }
    }

    private void reconnect() {
        String input = readValidInput();
        switch (input) {
            case "exit":
                System.exit(0);
            case "reconnect":
                connect();
        }
    }

    private String readValidInput() {
        String input = readInput();
        while (!isInputValid(input))
            input = readInput();
        return input;
    }

    private String readInput() {
        String message = "Сервер недоступен, введите exit для выхода или reconnect для переподключения";
        terminal.print(TextFormatter.format(message, Format.RED));
        return terminal.readLineEntire();
    }

    private boolean isInputValid(String input) {
        List<String> validInputs = new ArrayList<>();
        validInputs.add("exit");
        validInputs.add("reconnect");
        return validInputs.contains(input);
    }
}
