package client.managers;

public enum MessageType {
    ERROR {
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.printError(param);
            return null;
        }
    },
    TYPE_REQUEST {
        public String execute(ConsoleHandler consoleHandler, String param) {
            return consoleHandler.ask(param);
        }
    },
    ADVICE {
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.printAdvice(param);
            return null;
        }
    },
    QUESTION {
        public String execute(ConsoleHandler consoleHandler, String param) {
            return consoleHandler.ask(param);
        }
    },
    DEFAULT {
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.println(param);
            return null;
        }
    };

    public abstract String execute(ConsoleHandler consoleHandler, String param);

}
