package org.neric.regents.model;

public class XExamWrapper
{
	 private boolean selected;
	 private OrderExam orderExam;
	 
	 public XExamWrapper(OrderExam orderExam)
	 {
        this.orderExam = orderExam;
	 }

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	public OrderExam getOrderExam()
	{
		return orderExam;
	}

	public void setOrderExam(OrderExam orderExam)
	{
		this.orderExam = orderExam;
	}
}
