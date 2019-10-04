package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.OptionPrint;

public interface OptionPrintDao {
    OptionPrint findById(int id);

    OptionPrint findByUUID(String uuid);

    List<OptionPrint> findAllOptionPrints();

    List<OptionPrint> findAllActiveOptionPrints();

    void save(OptionPrint optionPrint);

    void update(OptionPrint optionPrint);

    void delete(int id);

    void deleteByUUID(String uuid);
}
