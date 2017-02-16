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
@Table(name="option_scan")
public class OptionScan implements Serializable
{ 
	private static final long serialVersionUID = 1L;

	public OptionScan()
	{
		super();
	}
	
	public OptionScan(Integer id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}
	
	public OptionScan(Integer id, String name, Boolean visible, Boolean locked)
	{
		super();
		this.id = id;
		this.name = name;
		this.visible = visible;
		this.locked = locked;
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "option_scan_id", unique = true, nullable = false)
	private Integer id;
	
	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;
			
	@Column(name="visible", unique=false, nullable=true)	
	private Boolean visible;
	
	@Column(name="locked", unique=false, nullable=true)	
	private Boolean locked;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderScan")
	private Set<Order> ordersScan = new HashSet<Order>(0);
	
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

	public Set<Order> getOrdersScan()
	{
		return ordersScan;
	}

	public void setOrdersScan(Set<Order> ordersScan)
	{
		this.ordersScan = ordersScan;
	}
	
	public Boolean getVisible()
	{
		return visible;
	}

	public void setVisible(Boolean visible)
	{
		this.visible = visible;
	}

	public Boolean getLocked()
	{
		return locked;
	}

	public void setLocked(Boolean locked)
	{
		this.locked = locked;
	}

	@Override
	public String toString()
	{
		return "OptionScan [id=" + id + ", name=" + name + ", visible=" + visible + ", locked=" + locked + "]";
	}
}
