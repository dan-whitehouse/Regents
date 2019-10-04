package org.neric.regents.dao;

import org.neric.regents.model.OrderForm;

import java.util.List;

public interface OrderFormDAO {

    OrderForm findById(int id);

    OrderForm findByUUID(String uuid);

    List<OrderForm> findAllOrderForms();

    void saveOrderForm(OrderForm order);

    void updateOrderForm(OrderForm order);

    void deleteOrderForm(int id);

    void deleteOrderForm(String uuid);

    void setAllInactive();

    boolean hasActiveOrderForm();

    OrderForm getActiveOrderForm();
}
