package server.network;

import java.io.IOException;

public interface NetworkServer {
    void openConnection() throws IOException;
    void run();
}
