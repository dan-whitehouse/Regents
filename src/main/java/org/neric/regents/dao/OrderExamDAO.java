package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.OrderExam;

public interface OrderExamDAO {

    OrderExam findById(int id);

    List<OrderExam> findAllOrderExams();

    void saveOrderExam(OrderExam orderExam);

    void deleteOrderExam(int id);

}
