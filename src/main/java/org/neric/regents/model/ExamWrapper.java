package org.neric.regents.model;

public class ExamWrapper
{
	private boolean selected;
    private OrderExam orderExam;
    
	public ExamWrapper(OrderExam orderExam)
	{
		 this.orderExam = orderExam;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected()
	{
		return selected;
	}

	/**
	 * @return the orderExam
	 */
	public OrderExam getOrderExam()
	{
		return orderExam;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	/**
	 * @param orderExam the orderExam to set
	 */
	public void setOrderExam(OrderExam orderExam)
	{
		this.orderExam = orderExam;
	}
	
	

}
