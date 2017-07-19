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

	public Exam()
	{
		super();
	}

	public Exam(Integer id, String name, String code)
	{
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	public Exam(Integer id, String name, String code, Boolean visible, Boolean locked)
	{
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.visible = visible;
		this.locked = locked;
	}

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
	
	@Column(name="visible", unique=false, nullable=true)	
	private Boolean visible;
	
	@Column(name="locked", unique=false, nullable=true)	
	private Boolean locked;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exam")
	private Set<OrderExam> exams = new HashSet<OrderExam>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exam")
	private Set<OrderFormExam> orderFormExams = new HashSet<OrderFormExam>(0);
	

	
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

	public Set<OrderFormExam> getOrderFormExams()
	{
		return orderFormExams;
	}

	public void setOrderFormExams(Set<OrderFormExam> orderFormExams)
	{
		this.orderFormExams = orderFormExams;
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
		return "Exam [id=" + id + ", name=" + name + ", code=" + code + ", visible=" + visible + ", locked=" + locked +"]";
	}
}
