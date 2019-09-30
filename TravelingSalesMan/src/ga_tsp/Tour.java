package ga_tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour{

    // Holds our tour of cities
    private List<Location> tour = new ArrayList<Location>();
    // Cache
    static double[][] matrix;
    private double fitness = 0;
    private int distance = 0;

    // Constructs a blank tour
    public Tour(){
        for (int i = 0; i < TourManager.numberOfLocations(); i++) {
            tour.add(null);
        }
    }

    public Tour(List<Location> tour){
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 0; cityIndex < TourManager.numberOfLocations(); cityIndex++) {
            setLocation(cityIndex, TourManager.getLocation(cityIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public Location getLocation(int tourPosition) {
        return (Location)tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setLocation(int tourPosition, Location location) {
        tour.set(tourPosition, location);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }

    // Gets the total distance of the tour
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // Loop through our tour's cities
            for (int locationIndex=0; locationIndex < tourSize(); locationIndex++) {
                // Get city we're travelling from
                Location fromLocation = getLocation(locationIndex);
                // City we're travelling to
                Location destinationLocation;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if(locationIndex + 1 < tourSize()){
                    destinationLocation = getLocation(locationIndex + 1);
                }
                else{
                    destinationLocation = getLocation(0);
                }
                // Get the distance between the two cities
                tourDistance +=  matrix[fromLocation.getId()][destinationLocation.getId()];
//                tourDistance += fromLocation.distanceTo(destinationLocation);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsLocation(Location location){
        return tour.contains(location);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getLocation(i)+"|";
        }
        return geneString;
    }
}