package exercise.components;

import exercise.annotations.City;
import exercise.annotations.Country;
import exercise.annotations.Population;

@Country
public class Russia {
    @City
    public City city;
    @Population
    public Population population;

}
