package org.neric.regents.service;

import org.neric.regents.dao.ExamDao;
import org.neric.regents.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("examService")
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDao dao;

    public Exam findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Exam findByUUID(String uuid) {
        return dao.findByUUID(uuid);
    }

    public Exam findByName(String examName) {
	    return dao.findByName(examName);
    }

    public List<Exam> findAllExams() {
        return dao.findAllExams();
    }

    public List<Exam> findAllActiveExams() {
        return dao.findAllActiveExams();
    }

    @Override
    public List<Exam> findAllExamsByOrderFormId(Integer id) {
        return dao.findAllExamsByOrderFormId(id);
    }


    public void saveExam(Exam exam) {
        dao.save(exam);
    }

    public void updateExam(Exam exam) {
        Exam entity = dao.findById(exam.getId());
        if(entity != null) {
            entity.setCode(exam.getCode());
            entity.setExams(exam.getExams());
            entity.setName(exam.getName());
        }
    }

    public void deleteByExamName(String examName) {
        dao.deleteByExamName(examName);
    }

    @Override
    public void deleteByExamId(int id) {
        dao.deleteByExamId(id);
    }

    @Override
    public void deleteByExamUUID(String uuid) {
        dao.deleteByExamUUID(uuid);
    }

    @Override
    public void lockByExamId(int id, Boolean isLocked) {
        Exam entity = dao.findById(id);
        if(entity != null) {
            entity.setLocked(isLocked);
        }
    }

    @Override
    public void lockByExamUUID(String uuid, boolean isLocked) {
        Exam entity = dao.findByUUID(uuid);
        if(entity != null) {
            entity.setLocked(isLocked);
        }
    }

    @Override
    public void hideByExamId(int id, Boolean isHidden) {
        Exam entity = dao.findById(id);
        if(entity != null) {
            entity.setVisible(isHidden);
        }
    }

    @Override
    public void hideByExamUUID(String uuid, boolean isHidden) {
        Exam entity = dao.findByUUID(uuid);
        if(entity != null) {
            entity.setVisible(isHidden);
        }
    }


}
