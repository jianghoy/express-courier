package com.fcv.expressCourier.services.utils;

import com.fcv.expressCourier.models.Address;


public class AddressToString {
    public static String conversion(Address address){
        return address.getAddress() + "," + address.getCity();
    }
}
