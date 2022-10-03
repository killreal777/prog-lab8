package abstractions.command;

import abstractions.prototypes.CloneablePrototype;
import exceptions.ArgumentAmountException;
import io.Format;
import io.TextFormatter;
import response.Result;

public abstract class Command extends CloneablePrototype {
    protected String name;
    protected Result result;

    public Command() {
        this.result = new Result();
    }

    abstract public void execute();

    public void setArgs(String[] args) {
        checkArgumentsAmount(args, 0); // command doesn't have any arguments by default
    }

    protected void checkArgumentsAmount(String[] args, int amount) {
        if (args.length != amount)
            throw new ArgumentAmountException(args.length, amount);
    }

    public void writeResult(String newResult) {
        result.writeMessageLine(newResult);
    }

    public void setGoodResult(String message) {
        result.setGoodResult(message);
    }

    public void setBadResult(String message) {
        result.setBadResult(message);
    }

    public String getMessage() {
        return result.getMessage();
    }

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
