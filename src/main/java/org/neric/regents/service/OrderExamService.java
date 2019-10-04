package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.OrderExam;

public interface OrderExamService {
    OrderExam findById(int id);

    List<OrderExam> findAllOrderExams();

    void saveOrderExam(OrderExam orderExam);

    void updateOrderExam(OrderExam orderExam);

    void deleteOrderExam(int id);
}
