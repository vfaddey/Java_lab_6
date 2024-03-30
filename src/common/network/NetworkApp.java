package common.network;

import java.io.IOException;

public interface NetworkApp {
    void openConnection() throws IOException;
    void run() throws IOException;
}
