package org.neric.regents.model;

import java.util.ArrayList;
import java.util.List;

public class XForm
{
	private List<XExamWrapper> allAvailableExams = new ArrayList<>();
	private List<XDocumentWrapper> allAvailableDocuments = new ArrayList<>();
	private List<XOptionPrintWrapper> allAvailableOptionPrints = new ArrayList<>();
	private OptionScan optionScan;
	
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


	public List<XOptionPrintWrapper> getAllAvailableOptionPrints()
	{
		return allAvailableOptionPrints;
	}

	public void setAllAvailableOptionPrints(List<XOptionPrintWrapper> allAvailableOptionPrints)
	{
		this.allAvailableOptionPrints = allAvailableOptionPrints;
	}

	public OptionScan getOptionScan()
	{
		return optionScan;
	}

	public void setOptionScan(OptionScan optionScan)
	{
		this.optionScan = optionScan;
	}
}
