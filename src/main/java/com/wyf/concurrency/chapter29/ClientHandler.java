package com.wyf.concurrency.chapter29;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    
    private Socket client;

    private volatile boolean running = true; 
    
    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
       
        try(InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter pr = new PrintWriter(outputStream))
        {
           
            while(running){
                String message = br.readLine();
                if(message == null)
                    break;
                System.out.println("Come from client > "+message+"\n");
                pr.write("echo"+message);
                pr.flush();
            }
        }catch (IOException e){
            this.running = false;
        }finally {
            this.stop();
        }
    }
      
    
    
    public void stop(){
        //已经自己端开了
        if(!running){
            return;
        }
        
        this.running = false;
        try {
            this.client.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    
}
