package org.neric.regents.model;

public class SelectedExam
{
	private Exam exam;
	private Boolean selected;
	
	public Exam getExam()
	{
		return exam;
	}
	public void setExam(Exam exam)
	{
		this.exam = exam;
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
