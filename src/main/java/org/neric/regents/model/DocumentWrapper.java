package org.neric.regents.model;

public class DocumentWrapper
{
	private boolean selected;
	private OrderDocument orderDocument;
	
	public DocumentWrapper()
	{
		super();
	}

	public DocumentWrapper(OrderDocument orderDocument)
	{
		this.orderDocument = orderDocument;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public OrderDocument getOrderDocument()
	{
		return orderDocument;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	public void setOrderDocument(OrderDocument orderDocument)
	{
		this.orderDocument = orderDocument;
	}

	@Override
	public String toString()
	{
		return "DocumentWrapper [selected=" + selected + ", orderDocument=" + orderDocument + "]";
	}

	
}
