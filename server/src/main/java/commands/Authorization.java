package commands;

import data.dao.Dao;

public class Authorization extends ArguedServerCommand<String> {
    public Authorization(Dao dao) {
        super(dao);
    }

    @Override
    public void execute() {
        if (!isUserExists()) {
            result.setBadResult("Пользователь не зарегестрирован");
        } else if (!dao.getPasswordByUserName(userName).equals(commandArgument)) {
            result.setBadResult("Неверный паорль");
        } else {
            result.setGoodResult(String.format("Вы вошли как %s", userName));
        }
    }
}
