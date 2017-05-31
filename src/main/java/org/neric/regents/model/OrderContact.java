package org.neric.regents.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="order_contact")
public class OrderContact implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_contact_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id", nullable = false)
	private School school;

	@Column(name="contact_name", nullable=true)
	private String name;
	
	@Column(name="contact_title", nullable=true)
	private String title;

	@Column(name="contact_email", nullable=true)
	private String email;
	
	@Column(name="contact_phone", nullable=true)
	private String phone;
	
	@Column(name="alt_contact_name", nullable=true)
	private String altName;
	
	@Column(name="alt_line1", nullable=true)
	private String altLine1;
	
	@Column(name="alt_line2", nullable=true)
	private String altLine2;
	
	@Column(name="alt_city", nullable=true)
	private String altCity;
	
	@Column(name="alt_state", nullable=true)
	private String altState;
	
	@Column(name="alt_zip_code", nullable=true)
	private String altZipCode;
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public School getSchool()
	{
		return school;
	}

	public void setSchool(School school)
	{
		this.school = school;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAltName()
	{
		return altName;
	}

	public void setAltName(String altName)
	{
		this.altName = altName;
	}

	public String getAltLine1()
	{
		return altLine1;
	}

	public void setAltLine1(String altLine1)
	{
		this.altLine1 = altLine1;
	}

	public String getAltLine2()
	{
		return altLine2;
	}

	public void setAltLine2(String altLine2)
	{
		this.altLine2 = altLine2;
	}

	public String getAltCity()
	{
		return altCity;
	}

	public void setAltCity(String altCity)
	{
		this.altCity = altCity;
	}

	public String getAltState()
	{
		return altState;
	}

	public void setAltState(String altState)
	{
		this.altState = altState;
	}

	public String getAltZipCode()
	{
		return altZipCode;
	}

	public void setAltZipCode(String altZipCode)
	{
		this.altZipCode = altZipCode;
	}
}
