package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.OrderExamDAO;
import org.neric.regents.model.OrderExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderExam")
@Transactional
public class OrderExamServiceImpl implements OrderExamService {

    @Autowired
    private OrderExamDAO dao;

    public OrderExam findById(int id) {
        return dao.findById(id);
    }

    public List<OrderExam> findAllOrderExams() {
        return dao.findAllOrderExams();
    }

    public void saveOrderExam(OrderExam orderExam) {
        dao.saveOrderExam(orderExam);

    }

    public void updateOrderExam(OrderExam orderExam) {
        OrderExam entity = dao.findById(orderExam.getId());
        if(entity != null) {
            entity.setAnswerSheetAmount(orderExam.getAnswerSheetAmount());
            entity.setExam(orderExam.getExam());
            entity.setExamAmount(orderExam.getExamAmount());
            entity.setOrder(orderExam.getOrder());
            entity.setStudentsPerCSV(orderExam.getStudentsPerCSV());
        }
    }

    public void deleteOrderExam(int id) {
        dao.deleteOrderExam(id);

    }
}
