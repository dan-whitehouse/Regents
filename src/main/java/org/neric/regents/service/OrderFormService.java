package org.neric.regents.service;

import org.neric.regents.model.OrderForm;

import java.util.List;

public interface OrderFormService {
    OrderForm findById(int id);

    OrderForm findByUUID(String uuid);

    List<OrderForm> findAllOrderForms();

    void saveOrderForm(OrderForm orderForm);

    void updateOrderForm(OrderForm orderForm);

    void deleteOrderForm(int id);

    void deleteOrderForm(String uuid);

    void lockByOrderFormId(int id, Boolean locked);

    void hideByOrderFormId(int id, Boolean hidden);

    void lockByOrderFormUUID(String uuid, Boolean locked);

    void hideByOrderFormUUID(String uuid, Boolean hidden);

    void activateOrderFormUUID(String uuid, Boolean isActive);

    boolean hasActiveOrderForm();

    OrderForm getActiveOrderForm();
}
