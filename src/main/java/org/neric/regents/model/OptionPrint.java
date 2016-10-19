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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="option_print")
public class OptionPrint implements Serializable
{ 
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "option_print_id", unique = true, nullable = false)
	private Integer id;
	
	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderPrint")
	private Set<Order> ordersPrint = new HashSet<Order>(0);

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<Order> getOrdersPrint()
	{
		return ordersPrint;
	}

	public void setOrdersPrint(Set<Order> ordersPrint)
	{
		this.ordersPrint = ordersPrint;
	}
}
