package com.fcv.expressCourier.services.location;

import com.fcv.expressCourier.payload.Location;

public interface LocationService {
    Location getLatLon(String address);
    double straightLineDistInMeter(Location latLonOne, Location latLonTwo);
}
