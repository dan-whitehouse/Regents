package org.neric.regents.model;

public class SelectedDocument
{
	private Document document;
	private Boolean selected;
	
	public Document getDocument()
	{
		return document;
	}
	public void setDocument(Document document)
	{
		this.document = document;
	}
	public Boolean getSelected()
	{
		return selected;
	}
	public void setSelected(Boolean selected)
	{
		this.selected = selected;
	}
}
