package client.managers;

import common.exceptions.CommandNotExistsException;
import common.requests.Request;

import java.util.HashMap;

public class RequestManager {
    private HashMap<String, Request> requestHashMap = new HashMap<>();

    public RequestManager(Request... requests) {
        for (Request request : requests) {
            this.requestHashMap.put(request.getCommandName(), request);
        }
    }

    public void addRequest(Request request) {
        this.requestHashMap.put(request.getCommandName(), request);
    }

    public Request get(String commandName) throws CommandNotExistsException {
        Request request = this.requestHashMap.get(commandName);
        if (request != null) {
            return request;
        } else {
            throw new CommandNotExistsException("Такой команды нет или у Вас нет доступа.");
        }
    }
}
