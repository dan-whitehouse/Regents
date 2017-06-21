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
@Table(name="order_document")
public class OrderDocument implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_document_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", nullable = false)
	private Document document;

	@Column(name="document_amount", nullable=true)
	private Integer documentAmount;

	
	public OrderDocument()
	{
		super();
	}
	
	public OrderDocument(Document document)
	{
		super();
		this.document = document;
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

	public Document getDocument()
	{
		return document;
	}

	public void setDocument(Document document)
	{
		this.document = document;
	}

	public Integer getDocumentAmount()
	{
		return documentAmount;
	}

	public void setDocumentAmount(Integer documentAmount)
	{
		this.documentAmount = documentAmount;
	}

	@Override
	public String toString()
	{
		return "OrderDocument [id=" + id + ", order=" + order + ", document=" + document + ", documentAmount=" + documentAmount + "]";
	}
}
