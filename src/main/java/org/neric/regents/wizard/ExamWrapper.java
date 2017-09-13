package org.neric.regents.wizard;

import org.neric.regents.model.OrderExam;

public class ExamWrapper
{
	private boolean selected;
    private OrderExam orderExam;
    
	public ExamWrapper()
	{
		super();
	}

	public ExamWrapper(OrderExam orderExam)
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

	@Override
	public String toString()
	{
		return "ExamWrapper [selected=" + selected + ", orderExam=" + orderExam + "]";
	}
}
