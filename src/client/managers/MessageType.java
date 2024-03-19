package client.managers;

public enum MessageType {
    ERROR(false) {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.printError(param);
            return null;
        }
    },
    TYPE_REQUEST(true) {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            return consoleHandler.ask(param);
        }
    },
    ADVICE(false) {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.printAdvice(param);
            return null;
        }
    },
    QUESTION(true) {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            return consoleHandler.ask(param);
        }
    },

    SUCCESS(false) {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.println(param);
            return null;
        }
    },
    DEFAULT(false) {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            consoleHandler.println(param);
            return null;
        }
    },
    WHAT_TO_CHANGE(true) {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            return consoleHandler.askWhatToChange();
        }
    },

    VALIDATE(true) {
        @Override
        public String execute(ConsoleHandler consoleHandler, String param) {
            return null;
        }
    };

    private final boolean isRequest;
    public abstract String execute(ConsoleHandler consoleHandler, String param);

    MessageType(boolean isRequest) {
        this.isRequest = isRequest;
    }

    public boolean isRequest() {
        return isRequest;
    }

}
