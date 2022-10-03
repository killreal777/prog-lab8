package commands;

import data.dao.Dao;
import model.Organization;

public class Head extends ServerCommand {
    public Head(Dao dao) {
        super(dao);
        this.name = "head";
    }

    @Override
    public void execute() {
        dao.getCollection().stream().findFirst().ifPresent(result::setJavaObject);
        if (result.getJavaObject() == null)
            result.setBadResult("Коллекция пуста");
        else
            result.setGoodResult(result.getJavaObject().toString());
    }
}
