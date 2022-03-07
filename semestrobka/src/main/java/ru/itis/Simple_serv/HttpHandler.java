package ru.itis.Simple_serv;

import java.io.IOException;

public interface HttpHandler {
    void process(HttpRequest request, HttpResponse response) throws IOException;
}
