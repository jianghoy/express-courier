package com.fcv.expressCourier.utils;

import com.fcv.expressCourier.models.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressToString {
    public static String conversion(Address address){
        return address.getAddress() + "," + address.getCity();
    }
}
