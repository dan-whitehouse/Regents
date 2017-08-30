package org.neric.regents.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="optout")
public class OptOut implements Serializable
{
	private static final long serialVersionUID = 1L;


	public OptOut() {
		super();
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "optout_id", unique = true, nullable = false)
	private Integer id;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderform_id", nullable = false)
	private OrderForm orderForm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", nullable = false)
	private District district;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User optOutUser;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "opt_out_date", unique = false, nullable = false)
	private Date optOutDate;


	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public OrderForm getOrderForm()
	{
		return orderForm;
	}

	public void setOrderForm(OrderForm orderForm)
	{
		this.orderForm = orderForm;
	}

	public District getDistrict()
	{
		return district;
	}

	public void setDistrict(District district)
	{
		this.district = district;
	}

	public User getOptOutUser()
	{
		return optOutUser;
	}

	public void setOptOutUser(User optOutUser)
	{
		this.optOutUser = optOutUser;
	}

	public Date getOptOutDate()
	{
		return optOutDate;
	}

	public void setOptOutDate(Date optOutDate)
	{
		this.optOutDate = optOutDate;
	}

	@Override
	public String toString()
	{
		return "OptOut [id=" + id + ", uuid=" + uuid + ", orderForm=" + orderForm + ", district=" + district + ", optOutUser=" + optOutUser + "]";
	}
	
}
