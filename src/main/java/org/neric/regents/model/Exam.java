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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="exam")
public class Exam implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "exam_id", unique = true, nullable = false)
	private Integer id;
	
	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@NotEmpty
	@Column(name="code", unique=true, nullable=false)
	private String code;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exam")
	private Set<OrderExam> exams = new HashSet<OrderExam>(0);

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "orderForm_exam", joinColumns = { @JoinColumn(name = "exam_id", nullable = false, updatable = false)},
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

	public Set<OrderExam> getExams()
	{
		return exams;
	}

	public void setExams(Set<OrderExam> exams)
	{
		this.exams = exams;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
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
