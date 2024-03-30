package common.Requests;

import common.model.Organization;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Request implements Serializable {
    protected String commandName;
    protected String message = null;

    public Request(String commandName) {
        this.commandName = commandName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
