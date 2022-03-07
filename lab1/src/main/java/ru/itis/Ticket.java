package ru.itis;

public class Ticket {
    String date;
    float close;
    public Ticket(String date, float close){
        this.date = date;
        this.close = close;
    }
    public float getClose() {
        return close;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Date='" + date + '\'' +
                ", close=" + close +
                '}';
    }
    public int getnumbers(){
        return Integer.parseInt(date.substring(0,5));
    }
    public void setDate(String date) {
        date = date;
    }

    public String sort(){
    return null;

    }
}
