package com.fcv.expressCourier.services.robotManagement;

import com.fcv.expressCourier.dao.WareHouseRepository;
import com.fcv.expressCourier.models.Address;
import com.fcv.expressCourier.models.Robot;
import com.fcv.expressCourier.models.WareHouse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@PropertySource("classpath:secret.properties")
@Component
public class InitializeRobotsAndWarehouses {

    @Value("${location.warehouse.one}")
    private String strOne;
    @Value("${latitude.warehouse.one}")
    private double latOne;
    @Value("${longitude.warehouse.one}")
    private double lonOne;

    @Value("${location.warehouse.two}")
    private String strTwo;
    @Value("${latitude.warehouse.two}")
    private double latTwo;
    @Value("${longitude.warehouse.two}")
    private double lonTwo;

    @Value("${location.warehouse.three}")
    private String strThree;
    @Value("${latitude.warehouse.three}")
    private double latThree;
    @Value("${longitude.warehouse.three}")
    private double lonThree;

    @Value("${location.warehouse.four}")
    private String strFour;
    @Value("${latitude.warehouse.four}")
    private double latFour;
    @Value("${longitude.warehouse.four}")
    private double lonFour;


    private final WareHouseRepository wareHouseRepository;

    public InitializeRobotsAndWarehouses(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        WareHouse wareHouseOne = new WareHouse();
        Address addressOne = new Address();
        addressOne.setAddress(strOne);
        addressOne.setCity("San Francisco");
        addressOne.setState("CA");
        addressOne.setLatitude(latOne);
        addressOne.setLongtitude(lonOne);
        wareHouseOne.setWareHouseAddress(addressOne);
        List<Robot> robotListOne = new ArrayList<>();

        // generate 10 car for
        for (int i = 0; i < 5; i++) {
            Robot robot = new Robot();
            robot.setLat(latOne);
            robot.setLon(lonOne);
            robot.setType(0);
            robot.setEstimatedIdleTime(new Date());
            robot.setWareHouse(wareHouseOne);
            robotListOne.add(robot);
        }

        Robot robot = new Robot();
        robot.setLat(latOne);
        robot.setLon(lonOne);
        robot.setType(1);
        robot.setEstimatedIdleTime(new Date());
        robot.setWareHouse(wareHouseOne);
        robotListOne.add(robot);
        wareHouseOne.setRobotList(robotListOne);
        wareHouseRepository.save(wareHouseOne);

        WareHouse wareHouseTwo = new WareHouse();
        Address addressTwo = new Address();
        addressTwo.setAddress(strTwo);
        addressTwo.setCity("San Francisco");
        addressTwo.setState("CA");
        addressTwo.setLatitude(latTwo);
        addressTwo.setLongtitude(lonTwo);
        wareHouseTwo.setWareHouseAddress(addressTwo);
        List<Robot> robotListTwo = new ArrayList<>();

        // generate 10 car for
        for (int i = 0; i < 5; i++) {
            Robot robotTwo = new Robot();
            robotTwo.setLat(latTwo);
            robotTwo.setLon(lonTwo);
            robotTwo.setType(0);
            robotTwo.setEstimatedIdleTime(new Date());
            robotTwo.setWareHouse(wareHouseTwo);
            robotListTwo.add(robotTwo);
        }

        Robot robotTwo = new Robot();
        robotTwo.setLat(latTwo);
        robotTwo.setLon(lonTwo);
        robotTwo.setType(1);
        robotTwo.setEstimatedIdleTime(new Date());
        robotTwo.setWareHouse(wareHouseTwo);
        robotListTwo.add(robotTwo);
        wareHouseTwo.setRobotList(robotListTwo);
        wareHouseRepository.save(wareHouseTwo);

        WareHouse wareHouseThree = new WareHouse();
        Address addressThree = new Address();
        addressThree.setAddress(strThree);
        addressThree.setCity("San Francisco");
        addressThree.setState("CA");
        addressThree.setLatitude(latThree);
        addressThree.setLongtitude(lonThree);
        wareHouseThree.setWareHouseAddress(addressThree);
        List<Robot> robotListThree = new ArrayList<>();

        // generate 10 car for
        for (int i = 0; i < 5; i++) {
            Robot robotThree = new Robot();
            robotThree.setLat(latThree);
            robotThree.setLon(lonThree);
            robotThree.setType(0);
            robotThree.setEstimatedIdleTime(new Date());
            robotThree.setWareHouse(wareHouseThree);
            robotListThree.add(robotThree);
        }

        Robot robotThree = new Robot();
        robotThree.setLat(latThree);
        robotThree.setLon(lonThree);
        robotThree.setType(1);
        robotThree.setEstimatedIdleTime(new Date());
        robotThree.setWareHouse(wareHouseThree);
        robotListThree.add(robotThree);
        wareHouseThree.setRobotList(robotListThree);
        wareHouseRepository.save(wareHouseThree);

        WareHouse wareHouseFour = new WareHouse();
        Address addressFour = new Address();
        addressFour.setAddress(strFour);
        addressFour.setCity("San Francisco");
        addressFour.setState("CA");
        addressFour.setLatitude(latFour);
        addressFour.setLongtitude(lonFour);
        wareHouseFour.setWareHouseAddress(addressFour);
        List<Robot> robotListFour = new ArrayList<>();

        // generate 10 car for
        for (int i = 0; i < 5; i++) {
            Robot robotFour = new Robot();
            robotFour.setLat(latFour);
            robotFour.setLon(lonFour);
            robotFour.setType(0);
            robotFour.setEstimatedIdleTime(new Date());
            robotFour.setWareHouse(wareHouseFour);
            robotListFour.add(robotFour);
        }

        Robot robotFour = new Robot();
        robotFour.setLat(latFour);
        robotFour.setLon(lonFour);
        robotFour.setType(1);
        robotFour.setEstimatedIdleTime(new Date());
        robotFour.setWareHouse(wareHouseFour);
        robotListFour.add(robotFour);
        wareHouseFour.setRobotList(robotListFour);
        wareHouseRepository.save(wareHouseFour);



    }
}
