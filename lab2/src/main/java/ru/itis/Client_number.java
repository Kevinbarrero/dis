package ru.itis;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client_number {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        try {
            clientSocket = new Socket("127.0.0.1", 10000);
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            while(!clientSocket.isClosed()) {

                String serverWord = in.readLine();


                if (serverWord == null){
                    clientSocket.close();
                    System.out.println("You lose");
                    break;
                }

                if(Objects.equals(serverWord, "You win")){
                    System.out.println("You Win");
                    clientSocket.close();
                    break;
                }
                System.out.println(serverWord);
                out.write(reader.readLine() + "\n");
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("You lose");
            clientSocket.close();
        }

    }
}