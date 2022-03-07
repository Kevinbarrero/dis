package ru.itis.Simple_serv;


import java.util.HashMap;
import java.util.Map;

public class Context {
    // path and route handlers
    private final Map<String, HttpHandler> httphandlers;

    // session and idsessions
    private final Map<String, Session> sessionMap;

    public Context() {

        this.httphandlers = new HashMap<>();
        this.sessionMap = new HashMap<>();
    }
    //get user of sesionid
    public Session getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
    //modify session
    public void setSession(String key, Session session){
        sessionMap.entrySet().removeIf((e) -> e.getValue().equals(session));
        this.sessionMap.put(key,session);
    }


    // adds the context to the handlers map
    public void createContext(String path, HttpHandler handler) {
        httphandlers.put(path,handler);
    }

    public HttpHandler getContext(String path) {
        String _path;
        if(path.endsWith("/")) {
            _path = path.substring(0,path.length()-1);
        } else {
            _path = path+"/";
        }
        // resource handlers first

        if(httphandlers.containsKey(path)) {
            return httphandlers.get(path);
        } else if(httphandlers.containsKey(_path)) {
            return httphandlers.get(_path);
        } else if (httphandlers.containsKey("/404")){
            return httphandlers.get("/404");
        } else {
            return httphandlers.get("/");
        }
    }


}


