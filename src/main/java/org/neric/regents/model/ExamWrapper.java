package org.neric.regents.model;

public class ExamWrapper
{
	private boolean selected;
	private boolean pasSelected;
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
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
	
	/**
	 * @return the pasSelected
	 */
	public boolean isPasSelected()
	{
		return pasSelected;
	}

	/**
	 * @param pasSelected the pasSelected to set
	 */
	public void setPasSelected(boolean pasSelected)
	{
		this.pasSelected = pasSelected;
	}

	/**
	 * @return the orderExam
	 */
	public OrderExam getOrderExam()
	{
		return orderExam;
	}

	/**
	 * @param orderExam the orderExam to set
	 */
	public void setOrderExam(OrderExam orderExam)
	{
		this.orderExam = orderExam;
	}
	
	

}
