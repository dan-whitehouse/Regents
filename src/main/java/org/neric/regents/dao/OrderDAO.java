package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Order;

public interface OrderDAO {

    Order findById(int id);

    Order findByUUID(String uuid);

    List<Order> findAllOrders();

    List<Order> findAllOrdersByUserId(int id);

    List<Order> findAllOrdersByUsername(String username);

    void saveOrder(Order order);

    void updateOrder(Order order);

    void updateStatusOfOrders(List<String> uuids, String action);

    void deleteOrder(int id);

    void deleteOrder(String uuid);

    int count();

    int countByActiveOrderForm(int id);

    int countByActiveOrderFormUniqueDistrict(int id);

    List<Order> findAllOrdersByActiveOrderForm(int id);
}
