package org.neric.regents.service;

import org.neric.regents.model.School;

import java.util.List;
import java.util.Set;


public interface SchoolService {

    School findById(int id);

    School findByUUID(String uuid);

    List<School> findAll();

    List<School> findAllByDistrictId(int id);

    void save(School school);

    void update(School school);

    void updateAll(Set<School> set);

    void deleteById(int id);

    void deleteByUUID(String uuid);

    void lockById(int id, boolean isLocked);

    void lockByUUID(String uuid, boolean isLocked);

    void hideById(int id, boolean isHidden);

    void hideByUUID(String uuid, boolean isHidden);

    int count();
}
