package org.neric.regents.model;

import java.io.Serializable;
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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="school")
public class School implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "school_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", nullable = false)
	private District district;
	
	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	private Set<OrderContact> orderContacts = new HashSet<OrderContact>(0);

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public District getDistrict()
	{
		return district;
	}

	public void setDistrict(District district)
	{
		this.district = district;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<OrderContact> getOrderContacts()
	{
		return orderContacts;
	}

	public void setOrderContacts(Set<OrderContact> orderContacts)
	{
		this.orderContacts = orderContacts;
	}
}
