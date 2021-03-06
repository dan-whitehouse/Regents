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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, unique=false)
	private Order order;
	
	@Column(name="contact_first_name", nullable=true)
	private String firstName;
	
	@Column(name="contact_middle_name", nullable=true)
	private String middleName;
	
	@Column(name="contact_last_name", nullable=true)
	private String lastName;
	
	@Column(name="contact_title", nullable=true)
	private String title;

	@Column(name="contact_email", nullable=true)
	private String email;
	
	@Column(name="contact_phone", nullable=true)
	private String phone;
	
	@Column(name="alt_contact_info", nullable=true, length = 1000)
	private String altContactInfo;
	
	
	public OrderContact()
	{
		super();
	}

	public OrderContact(Order order)
	{
		super();
		this.order = order;
	}
	
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

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
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

	public String getAltContactInfo()
	{
		return altContactInfo;
	}

	public void setAltContactInfo(String altContactInfo)
	{
		this.altContactInfo = altContactInfo;
	}
}
