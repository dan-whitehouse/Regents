package org.neric.regents.model.dashboard;

import org.neric.regents.model.District;

public class DistrictOrder
{
	private District district;
	private Long orderCount;
	
	
	public DistrictOrder()
	{
		super();
	}

	public DistrictOrder(District district, Long orderCount)
	{
		super();
		this.district = district;
		this.orderCount = orderCount;
	}
	
	public District getDistrict()
	{
		return district;
	}
	public void setDistrict(District district)
	{
		this.district = district;
	}
	
	public Long getOrderCount()
	{
		return orderCount;
	}
	public void setOrderCount(Long orderCount)
	{
		this.orderCount = orderCount;
	}
}
