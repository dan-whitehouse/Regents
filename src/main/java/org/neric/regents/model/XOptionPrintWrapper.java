package org.neric.regents.model;

public class XOptionPrintWrapper
{
	 private boolean selected;
	 private OptionPrint optionPrint;
	 
	 public XOptionPrintWrapper(OptionPrint optionPrint)
	 {
        this.optionPrint = optionPrint;
	 }

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
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
