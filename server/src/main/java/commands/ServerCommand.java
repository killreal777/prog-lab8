package commands;

import abstractions.command.Command;
import data.dao.Dao;
import response.Result;

public abstract class ServerCommand extends Command {
    protected Dao dao;
    protected String userName;

    public ServerCommand(Dao dao) {
        this.dao = dao;
    }

    protected boolean isUserExists() {
        return dao.getUserNamesList().contains(userName);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    protected void setResult(Result result) {
        this.result = result;
    }


}
