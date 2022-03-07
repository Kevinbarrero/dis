package ru.itis.Simple_serv;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    Session session;
    //   headersparams: value
    private Map<String, String> headersparamsr;
    // get
    private String method;
    // "/"
    private String path;
    // HTTP/1.1
    private String httpVersion;
    // html
    private String body;

    HttpRequest(InputStream in) throws IOException {
        headersparamsr = new HashMap<>();
        parseValues(in);
    }
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    //----------------- cookies
    public  Map<String,String > getCookies(){
        HashMap <String, String> cookies = new HashMap<>();
        String cookiestr = getHeaderparamr("Cookie");
        if(cookiestr != null) {
            Arrays.stream(cookiestr.split(";"))
                    .forEach(entry -> {
                        String[] kvPair = entry.trim().split("=");
                        cookies.put(kvPair[0],kvPair[1]);
                    });
        }
        return cookies;
    }

    //------------------------------ terminar los parse values ----------
    private void parseValues(InputStream in) throws IOException {
        // gather characters until a line is formed
        StringBuilder lineBuilder = new StringBuilder();

        // read first line
        int token = in.read();
        char tokenCharacter = (char) token;
        while (token != -1 && tokenCharacter != '\n') {
            if (tokenCharacter == '\r') {
                // gather the data and set values
                String firstLine = lineBuilder.toString();
                setMethodAndPath(firstLine);
                lineBuilder = new StringBuilder();
            } else {
                lineBuilder.append(tokenCharacter);
            }
            // update with new character
            token = in.read();
            tokenCharacter = (char) token;
        }

        // read headers
        boolean carriage = false;
        int lineCharCount = 0;
        token = in.read();
        tokenCharacter = (char) token;

        while (token != -1) {
            if (tokenCharacter != '\r' && tokenCharacter != '\n') {
                lineCharCount++;
                lineBuilder.append(tokenCharacter);
            } else if (tokenCharacter == '\r') {
                carriage = true;
            } else {
                if (carriage && lineCharCount == 0) {
                    // an empty line
                    break;
                } else if (carriage && lineCharCount > 0) {
                    carriage = false;
                    lineCharCount = 0;
                    String line = lineBuilder.toString();
                    setHeader(line);
                    lineBuilder = new StringBuilder();
                }
            }
            token = in.read();
            tokenCharacter = (char) token;
        }

        // read body
        if (headersparamsr.containsKey("Content-Length")) {
            while (in.available() != 0) {
                lineBuilder.append((char) in.read());
            }
            if (lineBuilder.length() != 0) body = lineBuilder.toString();
        }
    }

    private void setMethodAndPath(String firstLine) {
        String[] arr = firstLine.split(" ");
        method = arr[0];
        path = arr[1];
        httpVersion = arr[2];
        System.out.println(path);
    }

    private void setHeader(String line) {
        String[] arr = line.split(":");
        headersparamsr.put(arr[0].trim(), arr[1].trim());
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeadersparamsr() {
        return headersparamsr;
    }

    public String getHeaderparamr(String key) {
        return headersparamsr.get(key);
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHeadersparamsr(Map<String, String> headersparamsr) {
        this.headersparamsr = headersparamsr;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
