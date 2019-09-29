package com.fcv.expressCourier.utils;

import com.fcv.expressCourier.models.Address;
import org.springframework.stereotype.Service;

//TODO: move it into model Address.java
@Service
public class AddressToString {
    public static String conversion(Address address){
        return address.getAddress() + "," + address.getCity();
    }
}
