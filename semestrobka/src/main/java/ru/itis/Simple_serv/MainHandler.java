package ru.itis.Simple_serv;


import java.io.IOException;
import java.net.Socket;

public class MainHandler implements Runnable {
    private final Socket clientSocket;
    private final Context context;
    public MainHandler(Socket clientSocket, Context context) {
        this.clientSocket = clientSocket;
        this.context = context;
    }

    @Override
    public void run() {
        // create a request from client inputStream
        try {
            HttpRequest req = new HttpRequest(clientSocket.getInputStream());
            // and create a response from client outputStream
            HttpResponse res = new HttpResponse(clientSocket.getOutputStream());
            // check for user session in cookies from the request
            String sessionId = req.getCookies().get("JSESSION");
            if(sessionId != null) {
                req.setSession(context.getSession(sessionId));
            }

            if(req.getPath() == null) {
                throw new IOException("Path cannot be null");
            }

            // return a handler from request path
            context.getContext(req.getPath()).process(req, res);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) { }
            System.out.println(clientSocket + " closed");
        }
    }
}
