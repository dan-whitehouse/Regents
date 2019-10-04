package org.neric.regents.dao;

import org.neric.regents.model.OrderContact;

import java.util.List;

public interface OrderContactDAO {

    List<OrderContact> findAll();

    //School findByType(String type);

    OrderContact findById(int id);
}
