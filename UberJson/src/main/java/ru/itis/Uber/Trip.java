package ru.itis.Uber;

public class Trip{
    private String date;
    private Object client;
    private Object Driver;
    Trip(String date, Object client, Object driver){
        this.date = date;
        this.client = client;
        this.Driver = driver;
    }
    public Object getDriver() {
        return Driver;
    }


    public Object getClient() {
        return client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "date='" + date + '\'' +
                ", client=" + client +
                ", Driver=" + Driver +
                '}';
    }
}
