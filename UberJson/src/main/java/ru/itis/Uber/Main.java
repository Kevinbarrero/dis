package ru.itis.Uber;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    //ArrayLists to store every data
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Driver> drivers = new ArrayList<>();
    static ArrayList<Trip> trips = new ArrayList<>();
    public static void main(String[] args) {
        //probe if the data is ready in the console
        System.out.println(data());
        //Json parser to file
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            objectMapper.writeValue(new File("Database.json"), data());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //time search trip
        long startTime = System.nanoTime();
        System.out.println("Personal User Trip: "+ searchTrip(3));
        long endTime = System.nanoTime();
        System.out.println("time method execution: " + (endTime-startTime));
    }
    private static Trip searchTrip(Integer client_id){
        Client who = clients.stream().filter(x->client_id.equals(x.getClientId())).findAny().orElse(null);
        return trips.stream().filter(x-> who != null && who.equals(x.getClient())).findAny().orElse(null);
    }
    private static Database data(){


        //making data clients...
        clients.add(new Client(1,"9375257673","Kevin"));
        clients.add(new Client(2,"9783597618","Camilo"));
        clients.add(new Client(3, "9357947925","Sergey"));
        clients.add(new Client(4, "9367952819", "Arley"));
        //making data drivers...
        drivers.add(new Driver("9671542215",new Driver.Car("Kia Sportage", "M111M116")));
        drivers.add(new Driver("9687891542",new Driver.Car("Lada", "M22M712")));
        drivers.add(new Driver("9653541718", new Driver.Car("Kia Cerato", "M778M714")));
        drivers.add(new Driver("9862574616", new Driver.Car("Renault", "M447M833")));
        //making data trips...
        trips.add(new Trip("11/09/2021-15:12", clients.get(0),drivers.get(1)));
        trips.add(new Trip("12/09/2021-12:09", clients.get(2), drivers.get(0)));
        trips.add(new Trip("11/09/2021-13:50", clients.get(1), drivers.get(3)));
        return new Database(clients, drivers, trips);
    }
}
