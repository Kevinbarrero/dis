package ru.itis.Simple_serv;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    //headers: values
    private Map<String, String> headersparamsres;
    // siempre hay una linea vacia
    private final String EMPTY_LINE = "\r\n";
    //status code
    private String principal = "HTTP/1.1 200 OK";
    //body
    private String body;
    // output stream of client to write
    private OutputStream os;
    //name and value
    private Map<String, String> cookies;

    HttpResponse(OutputStream os) {
        this.os = os;
        this.headersparamsres = new HashMap<>();
        this.cookies = new HashMap<>();
    }

    public void setHeaderparamres(String key, String value) {
        headersparamsres.put(key, value);
    }

    public Map<String, String> getHeadersparamsres() {
        return headersparamsres;
    }

    public void setCookies(String key, String value){
        cookies.put(key, value);
    }
    // time expiration in seconds
    public void setCookies(String key, String value, int expiry) {
        setCookies(key,value+"; Max-Age="+expiry);
    }

    public void setHeadersparamsres(Map<String, String> headersparamsres) {

        headersparamsres.putAll(headersparamsres);
    }

    public String getEMPTY_LINE() {
        return EMPTY_LINE;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String httpVersion, String statusCode, String statusMessage) {
        principal = httpVersion+" "+statusCode+" "+statusMessage;}

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private void addContentLengthHeader() {
        if (body != null) {
            setHeaderparamres("Content-Length", "" + body.getBytes(StandardCharsets.UTF_8).length);
        }
    }

    private void addServerHeader() {
        setHeaderparamres("Date", new Date().toString());
        setHeaderparamres("Server", "Java TestCamilo Server");
    }

    public void send() throws IOException {
        addServerHeader();
        addContentLengthHeader();
        StringBuilder response = new StringBuilder();
        response.append(principal).append(EMPTY_LINE);
        headersparamsres.forEach((k, v) -> response.append(k).append(": ").append(v).append(EMPTY_LINE));
        cookies.forEach((k,v) -> response.append("Set-Cookie: ")
                .append(k).append("=").append(v).append("; HttpOnly").append(EMPTY_LINE));


        // header has finished
        response.append(EMPTY_LINE);

        // body
        if (body != null) {
            response.append(body);
        }

        // write, flush, close
        os.write(response.toString().getBytes());
        os.flush();
    }
}
