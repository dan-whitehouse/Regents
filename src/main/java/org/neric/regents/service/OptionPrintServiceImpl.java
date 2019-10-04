package org.neric.regents.service;

import org.neric.regents.dao.OptionPrintDao;
import org.neric.regents.model.OptionPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("optionPrintService")
@Transactional
public class OptionPrintServiceImpl implements OptionPrintService {

    @Autowired
    private OptionPrintDao dao;

    public OptionPrint findById(int id) {
        return dao.findById(id);
    }

    @Override
    public OptionPrint findByUUID(String uuid) {
        return dao.findByUUID(uuid);
    }

    public List<OptionPrint> findAllOptionPrints() {
        return dao.findAllOptionPrints();
    }

    @Override
    public List<OptionPrint> findAllActiveOptionPrints() {
        return dao.findAllActiveOptionPrints();
    }

    public void save(OptionPrint optionPrint) {
        dao.save(optionPrint);

    }

    public void update(OptionPrint optionPrint) {
        OptionPrint entity = dao.findById(optionPrint.getId());
        if(entity != null) {
            entity.setName(optionPrint.getName());
        }
    }

    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public void deleteByUUID(String uuid) {
        dao.deleteByUUID(uuid);
    }

    @Override
    public void lockByOptionPrintId(int id, Boolean isLocked) {
        OptionPrint entity = dao.findById(id);
        if(entity != null) {
            entity.setLocked(isLocked);
        }
    }

    @Override
    public void lockByOptionPrintUUID(String uuid, boolean isLocked) {
        OptionPrint entity = dao.findByUUID(uuid);
        if(entity != null) {
            entity.setLocked(isLocked);
        }
    }

    @Override
    public void hideByOptionPrintId(int id, Boolean isHidden) {
        OptionPrint entity = dao.findById(id);
        if(entity != null) {
            entity.setVisible(isHidden);
        }
    }

    @Override
    public void hideByOptionPrintUUID(String uuid, boolean isHidden) {
        OptionPrint entity = dao.findByUUID(uuid);
        if(entity != null) {
            entity.setVisible(isHidden);
        }
    }
}
