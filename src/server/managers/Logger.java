package server.managers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logger {
    private final java.util.logging.Logger logger;
    private final FileHandler fileHandler;

    public Logger(String logFilename) throws IOException {
        this.logger = java.util.logging.Logger.getLogger("Logger");
        this.fileHandler = new FileHandler(logFilename);
        this.fileHandler.setFormatter(new SimpleFormatter());
        this.logger.addHandler(this.fileHandler);
    }

    public void log(String message) {
        logger.info(message);
    }

    public void close() {
        this.fileHandler.close();
    }
}