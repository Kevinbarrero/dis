package ru.itis;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_number {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;
    public static void main(String[] args) throws IOException {
        int attempts = 3;
        try {
            server = new ServerSocket(10000);
            System.out.println("Server is running");
            clientSocket = server.accept();
            System.out.println("Connection with client established");
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int num = (int) (Math.random() * 10);
            System.out.println(num);
            while (attempts > 0) {
                out.write("Give me the number, you have: " + (attempts) + " attempts"+"\n");
                out.flush();
                String word = in.readLine();
                System.out.println(word);
                if (Integer.parseInt(word) == num){
                    out.write("You win" + "\n");
                    out.flush();
                    server.close();
                    break;
                }else
                    attempts--;
            }

        } catch (IOException e) {
            System.err.println(e);
        }
        server.close();
        System.out.println("Server has been Closed");
    }
}