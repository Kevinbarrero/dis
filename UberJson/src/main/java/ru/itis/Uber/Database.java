package ru.itis.Uber;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Client> clients;
    private List<Driver> drivers;
    private List<Trip> trips;

    public Database(ArrayList<Client> clients, ArrayList<Driver> drivers, ArrayList<Trip> trips) {
        this.clients = clients;
        this.drivers = drivers;
        this.trips = trips;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    @Override
    public String toString() {
        return "Database{" +
                "clients=" + clients +
                ", Drivers=" + drivers +
                ", Trips=" + trips +
                '}';
    }
}
