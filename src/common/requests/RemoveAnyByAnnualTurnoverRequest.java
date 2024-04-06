package common.requests;

import common.exceptions.NullUserRequestException;
import common.exceptions.WrongParameterException;
import client.managers.Validator;

public class RemoveAnyByAnnualTurnoverRequest extends Request implements RequestWithParameters{
    private long annualTurnover;
    public RemoveAnyByAnnualTurnoverRequest(String commandName) {
        super(commandName);
    }

    @Override
    public void setParameters(String... parameters) throws WrongParameterException {
        long result = -1;
        String parameter = parameters[0];
        try {
            if (Validator.isNull(parameter) || Validator.isEmptyArray(parameter.split(" "))) {
                throw new NullUserRequestException("Введена пустая строка");
            }
            if (parameter.contains(" ")) {
                String[] splitted = parameter.split(" ");
                if (Validator.isCorrectNumber(splitted[0], Long.class)) {
                    result = Long.parseLong(splitted[0]);
                }
            } else if (Validator.isCorrectNumber(parameter, Long.class)) {
                result = Long.parseLong(parameter);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
            if (result > 0) {
                this.annualTurnover = result;
            } else {
                throw new WrongParameterException("Годовой оборот не может быть меньше нуля.");
            }
        } catch (WrongParameterException | NullUserRequestException e) {
            throw new WrongParameterException("Неверно введен параметр");
        }
    }

    public long getAnnualTurnover() {
        return annualTurnover;
    }
}
