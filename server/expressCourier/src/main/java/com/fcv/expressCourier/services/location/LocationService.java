package com.fcv.expressCourier.services.location;

public interface LocationService {
    Location getLatLon(String address);
    double straightLineDistInMeter(Location latLonOne, Location latLonTwo);
}
