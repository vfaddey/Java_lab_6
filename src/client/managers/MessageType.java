package client.managers;

public enum MessageType {
    ERROR {
        public void execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.printError(param);
        }
    },
    TYPE_REQUEST {
        public void execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.println(param);
        }
    },
    ADVICE {
        public void execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.printAdvice(param);
        }
    },
    QUESTION {
        public void execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.ask(param);
        }
    },
    DEFAULT {
        public void execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.println(param);
        }
    };

    public abstract void execute(ConsoleHandler consoleHandler, String param);

}
