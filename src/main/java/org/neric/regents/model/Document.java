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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="document")
public class Document implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Document()
	{
		super();
	}

	public Document(Integer id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}
	
	public Document(Integer id, String name, Boolean visible, Boolean locked)
	{
		super();
		this.id = id;
		this.name = name;
		this.visible = visible;
		this.locked = locked;
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "document_id", unique = true, nullable = false)
	private Integer id;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	
	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@Column(name="visible", unique=false, nullable=true)	
	private Boolean visible;
	
	@Column(name="locked", unique=false, nullable=true)	
	private Boolean locked;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "document")
	private Set<OrderDocument> documents = new HashSet<OrderDocument>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "document")
	private Set<OrderFormDocument> orderFormDocuments = new HashSet<OrderFormDocument>(0);
	
	
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

	public Set<OrderFormDocument> getOrderFormDocuments()
	{
		return orderFormDocuments;
	}

	public void setOrderFormDocuments(Set<OrderFormDocument> orderFormDocuments)
	{
		this.orderFormDocuments = orderFormDocuments;
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
		return "Document [id=" + id + ", name=" + name + ", visible=" + visible + ", locked=" + locked + "]";
	}
}
