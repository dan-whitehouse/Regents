package org.neric.regents.service;

import org.neric.regents.dao.OptionScanDao;
import org.neric.regents.model.OptionScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("optionScanService")
@Transactional
public class OptionScanServiceImpl implements OptionScanService {

    @Autowired
    private OptionScanDao dao;

    public OptionScan findById(int id) {
        return dao.findById(id);
    }

    @Override
    public OptionScan findByUUID(String uuid) {
        return dao.findByUUID(uuid);
    }

    public List<OptionScan> findAllOptionScans() {
        return dao.findAllOptionScans();
    }

    @Override
    public List<OptionScan> findAllActivelOptionScans() {
        return dao.findAllActiveOptionScans();
    }

    public void save(OptionScan optionScan) {
        dao.save(optionScan);

    }

    public void update(OptionScan optionScan) {
        OptionScan entity = dao.findById(optionScan.getId());
        if(entity != null) {
            entity.setName(optionScan.getName());
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
    public void lockByOptionScanId(int id, Boolean isLocked) {
        OptionScan entity = dao.findById(id);
        if(entity != null) {
            entity.setLocked(isLocked);
        }
    }

    @Override
    public void lockByOptionScanUUID(String uuid, Boolean isLocked) {
        OptionScan entity = dao.findByUUID(uuid);
        if(entity != null) {
            entity.setLocked(isLocked);
        }
    }

    @Override
    public void hideByOptionScanId(int id, Boolean isHidden) {
        OptionScan entity = dao.findById(id);
        if(entity != null) {
            entity.setVisible(isHidden);
        }
    }

    @Override
    public void hideByOptionScanUUID(String uuid, Boolean isHidden) {
        OptionScan entity = dao.findByUUID(uuid);
        if(entity != null) {
            entity.setVisible(isHidden);
        }

    }
}
