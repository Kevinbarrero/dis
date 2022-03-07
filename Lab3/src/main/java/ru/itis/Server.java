package ru.itis;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
    private static ServerSocket server; // сервер
    public static Queue<ServerThreadMessage> userThreads;
    public static void main(String[] args) throws IOException {
        System.out.println("Server Info");

        try {
            server = new ServerSocket(9999);
            System.out.println("Server is running");
            userThreads = new LinkedList<>();

            while (true) {
                System.out.println("Waiting for connection...");
                //сокет для общения

                Socket clientSocket = server.accept();

                System.out.println("connected ..");

                ServerThreadMessage t = new ServerThreadMessage(clientSocket,userThreads);
                t.start();
                userThreads.add(t);
            }
        }finally {
            System.out.println("Server is closed");
            server.close();
        }
    }
}
