package org.neric.regents.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

//http://howtodoinjava.com/spring/spring-mvc/spring-mvc-populate-and-validate-dropdown-example/

public class XForm2 implements Serializable 
{
	private static final long serialVersionUID = 1L;	 
    private Integer id;
    
    private String title;
    
    @NotNull
    private OptionPrint selectedOptionPrint;
    
    @NotNull
    private OptionScan selectedOptionScan;
    
    @NotNull
    private List<XExamWrapper> selectedExams = new ArrayList<>();

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

	public List<XExamWrapper> getSelectedExams()
	{
		return selectedExams;
	}

	public void setSelectedExams(List<XExamWrapper> selectedExams)
	{
		this.selectedExams = selectedExams;
	}

	@Override
	public String toString()
	{
		return "XForm2 [id=" + id + ", title=" + title + ", selectedOptionPrint=" + selectedOptionPrint + ", selectedOptionScan=" + selectedOptionScan + ", selectedExams=" + selectedExams + "]";
	}
}
