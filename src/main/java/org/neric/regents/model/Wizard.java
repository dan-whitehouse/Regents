package org.neric.regents.model;

import java.util.ArrayList;
import java.util.List;

public class Wizard
{
	private List<ExamWrapper> allAvailableExams = new ArrayList<>();
	 
	public Wizard()
	{
		
	}

	/**
	 * @return the allAvailableExams
	 */
	public List<ExamWrapper> getAllAvailableExams()
	{
		return allAvailableExams;
	}

	/**
	 * @param allAvailableExams the allAvailableExams to set
	 */
	public void setAllAvailableExams(List<ExamWrapper> allAvailableExams)
	{
		this.allAvailableExams = allAvailableExams;
	}
	
	
}
