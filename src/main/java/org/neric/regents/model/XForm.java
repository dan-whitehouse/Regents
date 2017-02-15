package org.neric.regents.model;

import java.util.ArrayList;
import java.util.List;

public class XForm
{
	private List<XExamWrapper> allAvailableExams = new ArrayList<>();
	private List<XDocumentWrapper> allAvailableDocuments = new ArrayList<>();
	private OptionPrint selectedOptionPrint;

    public XForm()
    {

    }

	public List<XExamWrapper> getAllAvailableExams()
	{
		return allAvailableExams;
	}

	public void setAllAvailableExams(List<XExamWrapper> allAvailableExams)
	{
		this.allAvailableExams = allAvailableExams;
	}

	public List<XDocumentWrapper> getAllAvailableDocuments()
	{
		return allAvailableDocuments;
	}

	public void setAllAvailableDocuments(List<XDocumentWrapper> allAvailableDocuments)
	{
		this.allAvailableDocuments = allAvailableDocuments;
	}

	public OptionPrint getSelectedOptionPrint()
	{
		return selectedOptionPrint;
	}

	public void setSelectedOptionPrint(OptionPrint selectedOptionPrint)
	{
		this.selectedOptionPrint = selectedOptionPrint;
	}
}
