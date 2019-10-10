package com.fcv.expressCourier.services.robot;

import com.fcv.expressCourier.models.Robot;
import com.fcv.expressCourier.models.WareHouse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotsQueryImplTest {

    @Autowired
    private RobotsQuery robotsQuery;

    @Test
    public void testGet() {
        WareHouse wh = new WareHouse();
        Robot r1 = new Robot();
        r1.setWareHouse(wh);
        r1.setEstimatedIdleTime(new Date());
        r1.setType(1);
        Robot r2 = new Robot();
        r2.setWareHouse(wh);
        r2.setEstimatedIdleTime(new Date(0));
        r2.setType(1);
        wh.setRobotList(Arrays.asList(r1,r2));
        assertThat(robotsQuery.findEarliestAvailableDroneInWarehouse(wh)).isEqualToComparingFieldByField(r2);

    }



}