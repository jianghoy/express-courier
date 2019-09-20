package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Order;

public interface OrderDao {
    void addSalesOrder(Order SalesOrder);
    Order getOrder(int orderId);
}
