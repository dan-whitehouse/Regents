package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.OptOut;
import org.neric.regents.model.OrderForm;
import org.neric.regents.model.User;

public interface OptOutService {
    OptOut findById(int id);

    OptOut findByUUID(String uuid);

    List<OptOut> findAllOptOuts();

    List<OptOut> findAllOptOutsByUsername(String username);

    List<OptOut> findAllOptOutsByUserUUID(String uuid);

    List<OptOut> findAllActiveOptOuts(int orderFormId);

    List<OptOut> findAllOptOutsByUserAndOrderForm(User user, OrderForm orderForm);

    void save(OptOut optOut);

    void deleteById(int id);

    void deleteByUUID(String uuid);

    int countByActiveOrderForm(int id);
}
