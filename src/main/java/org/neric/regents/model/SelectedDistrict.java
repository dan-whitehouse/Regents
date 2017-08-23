package org.neric.regents.model;

public class SelectedDistrict
{
	private District district;
	private Boolean selected;
	
	public District getDistrict()
	{
		return district;
	}
	public void setDistrict(District district)
	{
		this.district = district;
	}
	public Boolean getSelected()
	{
		return selected;
	}
	public void setSelected(Boolean selected)
	{
		this.selected = selected;
	}
}
