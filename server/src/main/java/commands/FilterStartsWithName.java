package commands;

import data.dao.Dao;
import model.Organization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class FilterStartsWithName extends ArguedServerCommand<String> {
    public FilterStartsWithName(Dao dao) {
        super(dao);
        this.name = "filter_starts_with_name name";
    }

    @Override
    public void execute() {
        Collection<Organization> resultCollection = new ArrayList<>();
        Predicate<Organization> startsWithName = (organization) -> organization.getName().startsWith(commandArgument);
        dao.getCollection().stream().filter(startsWithName).sorted().forEach(resultCollection::add);
        result.setJavaObject(resultCollection);
        resultCollection.stream().map(Organization::toString).forEach(result::setGoodResult);
        if (result.getMessage().equals(""))
            setBadResult("В коллекции нет элементов, значение поля name которых начинается с заданной подстроки");
    }
}
