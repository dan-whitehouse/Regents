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
}
