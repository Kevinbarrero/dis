package ru.itis.Simple_serv;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Http_not_log implements HttpHandler{

    @Override
    public void process(HttpRequest request, HttpResponse response) throws IOException {
        response.setHeaderparamres("Content-Type", "text/html");
//
        String result = Files.readString(Paths.get("/home/holdandup/IdeaProjects/dis/semestrobka/src/main/resources/not_log.html"));
        response.setBody(result);
        response.send();
        // System.out.println(response.getBody());
    }
}
