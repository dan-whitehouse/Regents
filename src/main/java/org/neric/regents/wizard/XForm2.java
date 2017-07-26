package org.neric.regents.wizard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;

//http://howtodoinjava.com/spring/spring-mvc/spring-mvc-populate-and-validate-dropdown-example/

public class XForm2 implements Serializable 
{
	private static final long serialVersionUID = 1L;	 
    private Integer id;
    
    private String title;
    
    @NotNull
    private OptionPrint selectedOptionPrint;
    
    private boolean reportingOption;
    
    @NotNull
    private OptionScan selectedOptionScan;
    
    @NotNull
    private List<XExamWrapper> selectedExams = new ArrayList<>();
    
    @NotNull
    private List<XDocumentWrapper> selectedDocuments = new ArrayList<>();

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

	public boolean isReportingOption() {
		return reportingOption;
	}

	public void setReportingOption(boolean reportingOption) {
		this.reportingOption = reportingOption;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<XExamWrapper> getSelectedExams()
	{
		return selectedExams;
	}

	public void setSelectedExams(List<XExamWrapper> selectedExams)
	{
		this.selectedExams = selectedExams;
	}
	

	public List<XDocumentWrapper> getSelectedDocuments() {
		return selectedDocuments;
	}

	public void setSelectedDocuments(List<XDocumentWrapper> selectedDocuments) {
		this.selectedDocuments = selectedDocuments;
	}

	@Override
	public String toString()
	{
		return "XForm2 [id=" + id + ", title=" + title + ", selectedOptionPrint=" + selectedOptionPrint + ", reportingOption=" + reportingOption + ", selectedOptionScan=" + selectedOptionScan + ", selectedExams=" + selectedExams + ", selectedDocuments=" + selectedDocuments + "]";
	}
}
