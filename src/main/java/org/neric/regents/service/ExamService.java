package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.Exam;


public interface ExamService {

    Exam findById(int id);

    Exam findByUUID(String uuid);

    Exam findByName(String examName);

    List<Exam> findAllExams();

    List<Exam> findAllExamsByOrderFormId(Integer id);

    List<Exam> findAllActiveExams();

    void saveExam(Exam exam);

    void updateExam(Exam exam);

    void deleteByExamName(String examName);

    void deleteByExamId(int id);

    void deleteByExamUUID(String uuid);

    void lockByExamId(int id, Boolean locked);

    void lockByExamUUID(String uuid, boolean isLocked);

    void hideByExamId(int id, Boolean hidden);

    void hideByExamUUID(String uuid, boolean isHidden);
}