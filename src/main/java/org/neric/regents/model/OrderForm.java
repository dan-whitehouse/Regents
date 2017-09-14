package org.neric.regents.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.Interval;

@Entity
@Table(name="\"orderForm\"")
public class OrderForm implements Serializable
{
	private static final long serialVersionUID = 1L;

	public OrderForm()
	{
		super();
		this.locked = false;
		this.visible = true;
		this.active = false;
	}

	public OrderForm(Integer id, String uuid, String name, String period, Date startDate, Date endDate)
	{
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.period = period;
		this.startDate = startDate;
		this.endDate = endDate;
		this.locked = false;
		this.visible = true;
		this.active = false;
	}

	public OrderForm(Integer id, String uuid, String name, String period, Date startDate, Date endDate, Boolean visible, Boolean locked)
	{
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.period = period;
		this.startDate = startDate;
		this.endDate = endDate;
		this.visible = visible;
		this.locked = locked;
		this.active = false;
	}
	
	
	public OrderForm(Integer id, String uuid, String name, String period, Date startDate, Date endDate, Boolean visible, Boolean locked, Set<OrderFormExam> orderFormExams, Set<OrderFormDocument> orderFormDocuments)
	{
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.period = period;
		this.startDate = startDate;
		this.endDate = endDate;
		this.visible = visible;
		this.locked = locked;
		this.orderFormExams = orderFormExams;
		this.orderFormDocuments = orderFormDocuments;
		this.active = false;
	}
	
	
	public OrderForm(Integer id, String uuid, String name, String period, Date startDate, Date endDate, Boolean visible, Boolean locked, Boolean active, Set<OrderFormExam> orderFormExams, Set<OrderFormDocument> orderFormDocuments, Double rescanFee, Double inDistrictScanFee, Double nonSecureDocumentFee, Double processingFee)
	{
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.period = period;
		this.startDate = startDate;
		this.endDate = endDate;
		this.visible = visible;
		this.locked = locked;
		this.active = active;
		this.orderFormExams = orderFormExams;
		this.orderFormDocuments = orderFormDocuments;
		this.rescanFee = rescanFee;
		this.inDistrictScanFee = inDistrictScanFee;
		this.nonSecureDocumentFee = nonSecureDocumentFee;
		this.processingFee = processingFee;
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
	
	@NotEmpty
	@Column(name="period", unique=true, nullable=false)
	private String period;
	
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
	
	@Column(name="active", unique=false, nullable=false)	
	private Boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderForm", orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	private Set<OrderFormExam> orderFormExams = new HashSet<OrderFormExam>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderForm", orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	private Set<OrderFormDocument> orderFormDocuments = new HashSet<OrderFormDocument>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderForm", orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	private Set<Order> orders = new HashSet<Order>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderForm")
	private Set<OptOut> orderForm = new HashSet<OptOut>(0);
	
	@Column(name="rescanFee", unique=false, nullable=false)	
	private Double rescanFee;
	
	@Column(name="inDistrictScanFee", unique=false, nullable=false)	
	private Double inDistrictScanFee;
	
	@Column(name="nonSecureDocumentFee", unique=false, nullable=false)	
	private Double nonSecureDocumentFee;
	
	@Column(name="processingFee", unique=false, nullable=false)	
	private Double processingFee;
	
	
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

	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(String period)
	{
		this.period = period;
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

	public Boolean getActive()
	{
		return active;
	}
	
	public void setActive(Boolean active)
	{
		this.active = active;
	}

	public Set<Order> getOrders()
	{
		return orders;
	}

	public void setOrders(Set<Order> orders)
	{
		this.orders = orders;
	}
	
	
	public Set<OptOut> getOrderForm()
	{
		return orderForm;
	}

	public void setOrderForm(Set<OptOut> orderForm)
	{
		this.orderForm = orderForm;
	}

	public Double getRescanFee()
	{
		return rescanFee;
	}

	public void setRescanFee(Double rescanFee)
	{
		this.rescanFee = rescanFee;
	}

	public Double getInDistrictScanFee()
	{
		return inDistrictScanFee;
	}

	public void setInDistrictScanFee(Double inDistrictScanFee)
	{
		this.inDistrictScanFee = inDistrictScanFee;
	}

	public Double getNonSecureDocumentFee()
	{
		return nonSecureDocumentFee;
	}

	public void setNonSecureDocumentFee(Double nonSecureDocumentFee)
	{
		this.nonSecureDocumentFee = nonSecureDocumentFee;
	}

	public Double getProcessingFee()
	{
		return processingFee;
	}

	public void setProcessingFee(Double processingFee)
	{
		this.processingFee = processingFee;
	}

	@Transient
    public boolean isActivePeriod() 
	{
		Date now = new Date();
		Interval interval = new Interval(startDate.getTime(), endDate.getTime());
		return interval.contains(now.getTime());
    }
	
	@Transient
    public boolean isExpiredPeriod() 
	{
		Interval interval = new Interval(startDate.getTime(), endDate.getTime());
		return interval.isBeforeNow();
    }
	
	@Override
	public String toString()
	{
		return "OrderForm [id=" + id + ", uuid=" + uuid + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", visible=" + visible + ", locked=" + locked+ "]";
	}
}
