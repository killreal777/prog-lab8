package cui;

import abstractions.command.Command;
import requestes.CommandRequest;
import app.ClientHistory;
import app.LocalCommandManager;
import app.RequestsManager;
import client.ConnectorCui;
import exceptions.MessagedRuntimeException;
import io.Format;
import io.TextFormatter;
import response.Result;
import script.UnixScriptedTerminal;

import java.util.Optional;

public class ClientExecutionManager {
    private final ClientHistory history;
    private final UnixScriptedTerminal terminal;
    private final CommandReader commandReader;
    private final LocalCommandManager localCommandManager;
    private final RequestsManager requestsManager;
    private final ConnectorCui connector;
    private String userName;

    public ClientExecutionManager() {
        this.history = new ClientHistory();
        this.terminal = new UnixScriptedTerminal();
        this.commandReader = new CommandReader(terminal);
        this.requestsManager = new RequestsManager(terminal);
        this.localCommandManager = new LocalCommandManager(terminal, history);
        this.connector = new ConnectorCui(terminal);
        this.userName = "";
    }

    public void run() {
        while (true) {
            if (!userName.equals(""))
                executeNextCommand();
            else {
                terminal.print("Вы вошли как гость");
                new AuthorizationManager(terminal, connector, this::setUserName).login();
                terminal.print("Для вывода справки по доступным командам введите help");
            }
        }
    }

    public void executeNextCommand() {
        try {
            CommandReader.UserInput input = commandReader.readCommand();
            String name = input.getCommandName();
            String[] args = input.getCommandArgs();
            executeCommand(name, args);
        } catch (MessagedRuntimeException e) {
            terminal.print(e.getMessage());
        }
    }

    private void executeCommand(String commandName, String[] commandArgs) {
        if (localCommandManager.contains(commandName))
            executeLocalCommand(commandName, commandArgs);
        else if (requestsManager.contains(commandName))
            executeServerCommand(commandName, commandArgs);
        else
            terminal.print(TextFormatter.format("Команда не найдена", Format.RED));
    }

    private void executeLocalCommand(String commandName, String[] commandArgs) {
        Command command = localCommandManager.clonePrototype(commandName);
        command.setArgs(commandArgs);
        command.execute();
        terminal.print(command.getMessage());
        history.addCommand(command);
    }

    private void executeServerCommand(String commandName, String[] commandArgs) {
        CommandRequest request = requestsManager.clonePrototype(commandName);
        request.setCommandArgs(commandArgs);
        request.setUserName(userName);
        Optional<Result> result = connector.executeCommandOnServer(request);
        if (result.isPresent()) {
            terminal.print(result.get().getMessage());
            if (result.get().isSuccessful())
                history.addRequest(request);
        }
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }
}
