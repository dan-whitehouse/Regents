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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="\"order\"")
public class Order implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	private Integer id;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "order_date", unique = true, nullable = false)
	private Date orderDate;
	
	@Column(name="order_status", nullable=false)
	private String orderStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_print_id", nullable = false)
	private OptionPrint orderPrint;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_scan_id", nullable = false)
	private OptionScan orderScan;
	
	@NotEmpty
	@Column(name="report_to_level_one", unique=false, nullable=false)
	private Boolean reportToLevelOne;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderExam> orderExams = new HashSet<OrderExam>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderDocument> orderDocuments = new HashSet<OrderDocument>(0);

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public OptionPrint getOrderPrint()
	{
		return orderPrint;
	}

	public void setOrderPrint(OptionPrint orderPrint)
	{
		this.orderPrint = orderPrint;
	}

	public OptionScan getOrderScan()
	{
		return orderScan;
	}

	public void setOrderScan(OptionScan orderScan)
	{
		this.orderScan = orderScan;
	}

	public Boolean getReportToLevelOne()
	{
		return reportToLevelOne;
	}

	public void setReportToLevelOne(Boolean reportToLevelOne)
	{
		this.reportToLevelOne = reportToLevelOne;
	}

	public Set<OrderExam> getOrderExams()
	{
		return orderExams;
	}

	public void setOrderExams(Set<OrderExam> orderExams)
	{
		this.orderExams = orderExams;
	}

	public Set<OrderDocument> getOrderDocuments()
	{
		return orderDocuments;
	}

	public void setOrderDocuments(Set<OrderDocument> orderDocuments)
	{
		this.orderDocuments = orderDocuments;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public Date getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}

	public String getOrderStatus()
	{
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus)
	{
		this.orderStatus = orderStatus;
	}
	
	
}
