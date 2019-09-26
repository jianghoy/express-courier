package ga_tsp;

public class Location {
    int x;
    int y;
    int id;

    // Constructs a city at chosen x, y location
    public Location(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    // Gets city's x coordinate
    public int getX(){
        return this.x;
    }

    // Gets city's y coordinate
    public int getY(){
        return this.y;
    }

    // Gets the distance to given city
    public double distanceTo(Location location){
        int xDistance = Math.abs(getX() - location.getX());
        int yDistance = Math.abs(getY() - location.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );

        return distance;
    }

    @Override
    public String toString(){
        return getX()+", "+getY();
        // output order of ID
//        return getId() + ", ";
    }
}