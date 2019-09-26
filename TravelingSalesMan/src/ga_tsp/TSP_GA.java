package ga_tsp;

import java.util.Random;

public class TSP_GA {
    private static double[][] generateDistanceMatrix(int[][] locations) {
        double[][] matrix = new double[locations.length][locations.length];
        for(int i = 0; i < locations.length; i++) {
            for(int j = 0; j < locations.length; j++) {
                matrix[i][j] = getDistance(locations[i], locations[j]);
            }
        }
        return matrix;
    }
    private static double getDistance(int[] p1, int[] p2) {
        // to be replace by querying using google map api
        return  Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
    }

    public static void main(String[] args) {
        int size = 50;
        int[][] locations = new int[50][2];
        Random rd = new Random();
        // generate dummy data
        for(int i = 0; i < size; i++) {
            locations[i][0] = rd.nextInt(1000);
            locations[i][1] = rd.nextInt(1000);
            TourManager.addLocation(new Location(locations[i][0], locations[i][1], i));
        }

        Tour.matrix = generateDistanceMatrix(locations);
        // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}