package org.neric.regents.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="document")
public class Document implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "document_id", unique = true, nullable = false)
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "document")
	private Set<OrderDocument> documents = new HashSet<OrderDocument>(0);
	
	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "orderForm_document", joinColumns = { @JoinColumn(name = "document_id", nullable = false, updatable = false)},
								 	 inverseJoinColumns = { @JoinColumn(name = "orderForm_id",nullable = false, updatable = false)})
	private Set<OrderForm> orderForms = new HashSet<OrderForm>(0);
	
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Set<OrderDocument> getDocuments()
	{
		return documents;
	}

	public void setDocuments(Set<OrderDocument> documents)
	{
		this.documents = documents;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<OrderForm> getOrderForms()
	{
		return orderForms;
	}

	public void setOrderForms(Set<OrderForm> orderForms)
	{
		this.orderForms = orderForms;
	}
}
