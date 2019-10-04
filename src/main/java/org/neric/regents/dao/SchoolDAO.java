package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.School;

public interface SchoolDAO {

    List<School> findAll();

    List<School> findAllByDistrictId(int id);

    School findById(int id);

    School findByUUID(String uuid);

    void save(School school);

    void deleteById(int id);

    void deleteByUUID(String uuid);

    int count();
}
