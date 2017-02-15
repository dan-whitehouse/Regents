package org.neric.regents.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class XForm2 implements Serializable 
{
	private static final long serialVersionUID = 1L;	 
    private Integer id;
    
    private String title;
    
    @NotNull
    private OptionPrint selectedOptionPrint;

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

	@Override
	public String toString()
	{
		return "XForm2 [id=" + id + ", title=" + title + ", selectedOptionPrint=" + selectedOptionPrint.getName() + "]";
	}
     
}
