package org.neric.regents.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Wizard
{
	private static final long serialVersionUID = 1L;	 
    private Integer id;
    
    private String title;
    
    @NotNull
    private OptionPrint selectedOptionPrint;
    
    @NotNull
    private OptionScan selectedOptionScan;
    
    @NotNull
    private List<ExamWrapper> selectedExams = new ArrayList<>();
    
    @NotNull
    private List<DocumentWrapper> selectedDocuments = new ArrayList<>();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public OptionPrint getSelectedOptionPrint()
	{
		return selectedOptionPrint;
	}

	public void setSelectedOptionPrint(OptionPrint selectedOptionPrint)
	{
		this.selectedOptionPrint = selectedOptionPrint;
	}

	public OptionScan getSelectedOptionScan()
	{
		return selectedOptionScan;
	}

	public void setSelectedOptionScan(OptionScan selectedOptionScan)
	{
		this.selectedOptionScan = selectedOptionScan;
	}

	public List<ExamWrapper> getSelectedExams()
	{
		return selectedExams;
	}

	public void setSelectedExams(List<ExamWrapper> selectedExams)
	{
		this.selectedExams = selectedExams;
	}
	
	

	public List<DocumentWrapper> getSelectedDocuments()
	{
		return selectedDocuments;
	}

	public void setSelectedDocuments(List<DocumentWrapper> selectedDocuments)
	{
		this.selectedDocuments = selectedDocuments;
	}

	@Override
	public String toString()
	{
		return "Wizard [id=" + id + ", title=" + title + ", selectedOptionPrint=" + selectedOptionPrint + ", selectedOptionScan=" + selectedOptionScan + ", selectedExams=" + selectedExams + ", selectedDocuments=" + selectedDocuments + "]";
	}
	
//	private List<ExamWrapper> allAvailableExams = new ArrayList<>();
//	private List<DocumentWrapper> allAvailableDocuments = new ArrayList<>();
//	 
//	public Wizard()
//	{
//		
//	}
//
//	public List<ExamWrapper> getAllAvailableExams()
//	{
//		return allAvailableExams;
//	}
//
//	public void setAllAvailableExams(List<ExamWrapper> allAvailableExams)
//	{
//		this.allAvailableExams = allAvailableExams;
//	}
//
//	public List<DocumentWrapper> getAllAvailableDocuments()
//	{
//		return allAvailableDocuments;
//	}
//
//	public void setAllAvailableDocuments(List<DocumentWrapper> allAvailableDocuments)
//	{
//		this.allAvailableDocuments = allAvailableDocuments;
//	}
	
}
