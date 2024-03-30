package server.network;

import jdk.net.ExtendedSocketOptions;
import server.managers.CommandManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class TCPServer implements NetworkServer {
    private static final int BUFFER_SIZE = 4096;
    ByteBuffer buffer;

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private CommandManager commandManager;

    public TCPServer(ServerSocketChannel serverSocketChannel, CommandManager commandManager) {
        this.serverSocketChannel = serverSocketChannel;
        this.commandManager = commandManager;
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    @Override
    public void openConnection() throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.configureBlocking(false);
//        this.serverSocketChannel.setOption(ExtendedSocketOptions.TCP_KEEPIDLE, 10);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(HOST, PORT);
        this.serverSocketChannel.bind(inetSocketAddress);
        this.selector = initSelector();
    }

    @Override
    public void run() {
        try {
            while (true) {
                selector.selectNow();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = takeKey(selectedKeys);
                    handleKey(key);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SelectionKey takeKey(Iterator<SelectionKey> selectionKeyIterator) {
        SelectionKey key = selectionKeyIterator.next();
        selectionKeyIterator.remove();
        return key;
    }

    private Selector initSelector() throws IOException {
        Selector socketSelector = SelectorProvider.provider().openSelector();
        this.serverSocketChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
        return socketSelector;
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                accept(key);
            } else if (key.isReadable()) {
                read(key);
            } else if (key.isWritable()) {
                write(key);
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int bytesRead;
        try {
            bytesRead = socketChannel.read(this.buffer);
        } catch (IOException e) {
            key.cancel();
            socketChannel.close();
            return;
        }

        if (bytesRead == -1) {
            key.cancel();
            return;
        }

        

    }

    private void write(SelectionKey key) {

    }

}
