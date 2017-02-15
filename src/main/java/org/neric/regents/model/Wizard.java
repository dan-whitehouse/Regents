package org.neric.regents.model;

import java.util.ArrayList;
import java.util.List;

public class Wizard
{
	private List<ExamWrapper> allAvailableExams = new ArrayList<>();
	private List<DocumentWrapper> allAvailableDocuments = new ArrayList<>();
	 
	public Wizard()
	{
		
	}

	public List<ExamWrapper> getAllAvailableExams()
	{
		return allAvailableExams;
	}

	public void setAllAvailableExams(List<ExamWrapper> allAvailableExams)
	{
		this.allAvailableExams = allAvailableExams;
	}

	public List<DocumentWrapper> getAllAvailableDocuments()
	{
		return allAvailableDocuments;
	}

	public void setAllAvailableDocuments(List<DocumentWrapper> allAvailableDocuments)
	{
		this.allAvailableDocuments = allAvailableDocuments;
	}
	
}
