package com.fcv.expressCourier.services.location;

import com.fcv.expressCourier.payload.LatLon;

public interface LocationService {
    LatLon getLatLon(String address);
    double straightLineDistInMeter(LatLon latLonOne, LatLon latLonTwo);
}
