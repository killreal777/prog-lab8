package commands;

import data.dao.Dao;
import model.Organization;

import java.util.Collection;

public class Show extends ServerCommand {

    public Show(Dao dao) {
        super(dao);
        this.name = "show";
    }

    @Override
    public void execute() {
        Collection<Organization> collection = dao.getCollection();
        result.setJavaObject(collection);
        collection.stream().sorted().map(Organization::toString).forEach(result::setGoodResult);
        if (result.equals(""))
            result.setBadResult("Коллекция пуста");
    }
}
