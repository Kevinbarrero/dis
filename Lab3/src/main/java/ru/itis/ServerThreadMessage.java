package ru.itis;


import java.io.*;
import java.net.Socket;
import java.util.*;

public class ServerThreadMessage extends Thread {
    Socket clientSocket;
    private BufferedReader in; // поток чтения из сокета
    private String name;
    private BufferedWriter out;
    private final Queue<ServerThreadMessage> serverThreadMassages;
    public Map<String, List<String>> messages;

    public ServerThreadMessage(Socket clientSocket, Queue<ServerThreadMessage> serverThreadMassages) {
        this.clientSocket = clientSocket;
        this.serverThreadMassages = serverThreadMassages;
        messages = new HashMap<>();
    }

    public void run() {
        try {
            try {

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                out.write("Enter your name: " + "\n");
                out.flush();

                name = in.readLine();
                sendmessageto();

                while (true) {

                    out.write("Please send a message, use format message->nameto: " + "\n");
                    out.flush();

                    String messageFromClient = in.readLine();
                    if ("quit".equals(messageFromClient))
                    {
                        break;
                    }

                    String[] ma = messageFromClient.split("->");
                    sendMessage(ma[0],ma[1]);

                }
            }finally {
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendMessage(String name, String message) throws IOException {
        // if user send message to himself ...
        if (name.equals(this.name))
        {
            out.write(name+"->"+ message +"" + "\n");
            out.flush();
            return;
        }
        for (ServerThreadMessage s:serverThreadMassages) {
            if (s.name.equals(name))
            {
                if (s.messages.containsKey(name))
                {
                    s.messages.get(name).add(message);
                }
                else
                {
                    List<String> mass = new ArrayList<>();
                    mass.add(message);
                    s.messages.put(this.name,mass);
                }
                s.sendmessageto();
                return;
            }
        }
        out.write( "User : \""+name+"\" not found in the system!!" + "\n");
        out.flush();
    }

    private void sendmessageto() throws IOException {
        for (String name : messages.keySet()) {
            String value = messages.get(name).toString();
            try {
                out.write("You have a new message: { "+name + ";" + value + " }\n");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        messages.clear();
    }
}
