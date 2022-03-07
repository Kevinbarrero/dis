package ru.itis.Simple_serv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        // paths to and handler routes
        Context context = new Context();
        context.createContext("/home", new Http_principal());
        context.createContext("/feed", new Http_feed());
        context.createContext("/view", new Http_not_log());
        context.createContext("/login", new Http_login());


        //construction server
        try {
            int port = 9999;
            ServerSocket server = new ServerSocket(port);
            System.out.println("this is the principal path: http://localhost:9999/home");
            //listen browser
            while(true) {
                Socket ClientSocket = server.accept();
                System.out.println("client is ready");
                new Thread(new MainHandler(ClientSocket, context)).start();

            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
