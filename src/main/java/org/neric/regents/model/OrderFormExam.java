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
@Table(name="orderform_exam")
public class OrderFormExam implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderForm_exam_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderForm_id", nullable = false)
	private OrderForm orderForm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id", nullable = false)
	private Exam exam;

	
	
	public OrderFormExam(Exam exam)
	{
		super();
		this.exam = exam;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public OrderForm getOrderForm()
	{
		return orderForm;
	}

	public void setOrderForm(OrderForm orderForm)
	{
		this.orderForm = orderForm;
	}

	public Exam getExam()
	{
		return exam;
	}

	public void setExam(Exam exam)
	{
		this.exam = exam;
	}

	@Override
	public String toString()
	{
		return "OrderFormExam [id=" + id + ", orderForm=" + orderForm + ", exam=" + exam + "]";
	}
}
