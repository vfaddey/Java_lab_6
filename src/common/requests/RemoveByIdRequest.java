package common.requests;

import common.exceptions.WrongParameterException;
import client.managers.Validator;

public class RemoveByIdRequest extends Request implements RequestWithParameters{
    private long id;
    public RemoveByIdRequest(String commandName) {
        super(commandName);
    }

    @Override
    public void setParameters(String... parameters) throws WrongParameterException {
        if (!parameters[0].isEmpty()) {
            if (Validator.isCorrectNumber(parameters[0], Long.class)) {
                this.id = Long.parseLong(parameters[0]);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
        } else {
            throw new WrongParameterException("Параметр пуст.");
        }

    }

    public long getId() {
        return id;
    }
}
