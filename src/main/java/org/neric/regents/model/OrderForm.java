package org.neric.regents.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="\"orderForm\"")
public class OrderForm implements Serializable
{
	private static final long serialVersionUID = 1L;

	public OrderForm()
	{
		super();
	}

	public OrderForm(Integer id, String uuid, String name, Date startDate, Date endDate)
	{
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public OrderForm(Integer id, String uuid, String name, Date startDate, Date endDate, Boolean visible, Boolean locked)
	{
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.visible = visible;
		this.locked = locked;
	}
	
	
	public OrderForm(Integer id, String uuid, String name, Date startDate, Date endDate, Boolean visible, Boolean locked, Set<OrderFormExam> orderFormExams, Set<OrderFormDocument> orderFormDocuments)
	{
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.visible = visible;
		this.locked = locked;
		this.orderFormExams = orderFormExams;
		this.orderFormDocuments = orderFormDocuments;
	}


	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderForm_id", unique = true, nullable = false)
	private Integer id;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	
	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "startDate", unique = false, nullable = false)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "endDate", unique = false, nullable = false)
	private Date endDate;
		
	@Column(name="visible", unique=false, nullable=true)	
	private Boolean visible;
	
	@Column(name="locked", unique=false, nullable=true)	
	private Boolean locked;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderForm")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<OrderFormExam> orderFormExams = new HashSet<OrderFormExam>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderForm")
	@Cascade(CascadeType.SAVE_UPDATE)
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Set<OrderFormExam> getOrderFormExams()
	{
		return orderFormExams;
	}

	public void setOrderFormExams(Set<OrderFormExam> orderFormExams)
	{
		this.orderFormExams = orderFormExams;
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
		return "OrderForm [id=" + id + ", uuid=" + uuid + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", visible=" + visible + ", locked=" + locked+ "]";
	}
}
