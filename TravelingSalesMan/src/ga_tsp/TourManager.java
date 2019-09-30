package ga_tsp;

import java.util.ArrayList;

public class TourManager {

    // Holds our cities
    private static ArrayList destinationCities = new ArrayList<Location>();

    // Adds a destination city
    public static void addLocation(Location location) {
        destinationCities.add(location);
    }

    // Get a city
    public static Location getLocation(int index){
        return (Location)destinationCities.get(index);
    }

    // Get the number of destination cities
    public static int numberOfLocations(){
        return destinationCities.size();
    }
}