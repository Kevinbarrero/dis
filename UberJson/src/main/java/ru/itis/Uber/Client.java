package ru.itis.Uber;

public class Client {
    private int clientId;
    private String phone;
    private String name;
    Client(int clientId, String phone, String name){
        this.clientId = clientId;
        this.phone = phone;
        this.name = name;
    }
    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
