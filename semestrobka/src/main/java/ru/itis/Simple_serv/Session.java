package ru.itis.Simple_serv;


import java.util.Objects;

public class Session {
    public String username;
    public String password;

    Session(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(username, session.username) && Objects.equals(password, session.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public static Session fromcode(String encode){
        String username = "";
        String password = "";
        String value1 ="";
        String value2 ="";
        String[] partition = encode.split("&");
        value1 = partition[0];
        value2 = partition[1];
        String[] subpartition1 = value1.split("=");
        value1 = subpartition1[0];
        username = subpartition1[1];
        String[] subpartition2 = value2.split("=");
        value2 = subpartition2[0];
        password = subpartition2[1];
        System.out.println(value1 + "\t" + username + "\t" + value2 + "\t" + password + "\t");
        return new Session (username, password);

    }
}
