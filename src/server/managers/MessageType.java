package server.managers;

import client.managers.Validator;

public enum MessageType {
    ERROR,
    TYPE_REQUEST,
    ADVICE,
    QUESTION,
    SUCCESS,
    DEFAULT,
    WHAT_TO_CHANGE,
    VALIDATE {
         public boolean valid(String param) {
             return Validator.isValidName(param);
         }
    };

}
