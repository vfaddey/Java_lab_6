package client.managers;

public enum MessageType {
    ERROR {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.printError(param);
            return null;
        }
    },
    TYPE_REQUEST {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            return consoleHandler.ask(param);
        }
    },
    ADVICE {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.printAdvice(param);
            return null;
        }
    },
    QUESTION {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            return consoleHandler.ask(param);
        }
    },

    SUCCESS {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.println(param);
            return null;
        }
    },
    DEFAULT {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.println(param);
            return null;
        }
    },
    WHAT_TO_CHANGE {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            return consoleHandler.askWhatToChange();
        }
    };

    public abstract String execute(ConsoleHandler consoleHandler, String param);

}
