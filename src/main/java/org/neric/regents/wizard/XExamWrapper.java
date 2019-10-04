package org.neric.regents.wizard;

import org.neric.regents.model.OrderExam;

public class XExamWrapper {
    private boolean selected;
    private OrderExam orderExam;

    public XExamWrapper(OrderExam orderExam) {
        this.orderExam = orderExam;
    }

    public XExamWrapper() {
        super();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public OrderExam getOrderExam() {
        return orderExam;
    }

    public void setOrderExam(OrderExam orderExam) {
        this.orderExam = orderExam;
    }

    @Override
    public String toString() {
        return "XExamWrapper [selected=" + selected + ", orderExam=" + orderExam + "]";
    }
}
