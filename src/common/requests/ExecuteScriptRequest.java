package common.requests;

import client.managers.Validator;

public class ExecuteScriptRequest extends Request implements RequestWithParameters{
    String filename;
    public ExecuteScriptRequest(String commandName) {
        super(commandName);
    }

    @Override
    public void setParameters(String... parameters) {
        if (Validator.isValidName(parameters[0])) {
            this.filename = parameters[0];
        }
    }

    public String getFilename() {
        return filename;
    }
}
