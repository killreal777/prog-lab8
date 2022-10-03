package app;

import abstractions.command.Command;
import requestes.ArguedCommandRequest;
import requestes.CommandRequest;
import commands.ArguedServerCommand;
import commands.ServerCommand;
import response.Result;

public class ServerExecutionManager {
    private final ServerCommandManager commandsManager;

    public ServerExecutionManager() {
        this.commandsManager = new ServerCommandManager();
    }

    public Result executeCommand(CommandRequest request) {
        ServerCommand command = commandsManager.clonePrototype(request.getCommandName());
        command.setUserName(request.getUserName());
        if (command instanceof ArguedServerCommand<?>)
            defineCommandArgument(command, request);
        command.execute();
        return command.getResult();
    }

    private void defineCommandArgument(Command command, CommandRequest request) {
        ArguedServerCommand arguedCommand = (ArguedServerCommand) command;
        ArguedCommandRequest arguedCommandRequest = (ArguedCommandRequest) request;
        arguedCommand.extractArgumentFromRequest(arguedCommandRequest);
    }
}
