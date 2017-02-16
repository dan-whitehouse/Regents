package org.neric.regents.model;

public class ExamWrapper
{
	private boolean selected;
	private boolean pasSelected;
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

	public boolean isPasSelected()
	{
		return pasSelected;
	}

	public void setPasSelected(boolean pasSelected)
	{
		this.pasSelected = pasSelected;
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
		return "ExamWrapper [selected=" + selected + ", pasSelected=" + pasSelected + ", orderExam=" + orderExam + "]";
	}
}
