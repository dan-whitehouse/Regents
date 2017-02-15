package org.neric.regents.model;

public class XOptionPrintWrapper
{
	 private OptionPrint optionPrint;
	 
	 public XOptionPrintWrapper(OptionPrint optionPrint)
	 {
        this.optionPrint = optionPrint;
	 }

	public XOptionPrintWrapper()
	{

	}

	public OptionPrint getOptionPrint()
	{
		return optionPrint;
	}

	public void setOptionPrint(OptionPrint optionPrint)
	{
		this.optionPrint = optionPrint;
	}
}
