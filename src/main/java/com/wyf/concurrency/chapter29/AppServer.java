package com.wyf.concurrency.chapter29;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread{

    private int port;
    
    private final static int PORT = 9999;

    
    private volatile boolean start = true;
    
    private List<ClientHandler> handlerList = new ArrayList<>();

    private ServerSocket serverSocket;
    
    
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    public AppServer() {
        this(PORT);
    }
    
    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        while(start){
            try {
                this.serverSocket = new ServerSocket(port);
                while(start){
                    Socket client = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(client);
                    executor.submit(clientHandler);
                    handlerList.add(clientHandler);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                this.dispose();
            }
        }
    }

    private void dispose(){
        System.out.println("dispose");
        handlerList.stream().forEach(ClientHandler::stop);
        executor.shutdown();
    }

    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.serverSocket.close();
        
    }
}

