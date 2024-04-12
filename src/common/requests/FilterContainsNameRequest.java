package common.requests;

import common.exceptions.WrongParameterException;

public class FilterContainsNameRequest extends Request implements RequestWithParameters{
    private String substring;
    public FilterContainsNameRequest(String commandName) {
        super(commandName);
    }

    @Override
    public void setParameters(String... parameters) throws WrongParameterException {
        this.substring = parameters[0];
    }

    public String getSubstring() {
        return substring;
    }
}
