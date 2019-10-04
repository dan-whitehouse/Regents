package org.neric.regents.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="order_exam")
public class OrderExam implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_exam_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id", nullable = false)
	private Exam exam;

	@Column(name="exam_amount")
	private Integer examAmount;

	@Column(name="answer_sheet_amount")
	private String answerSheetAmount;
	
	@Column(name="students_per_csv")
	private String studentsPerCSV;
	

	public OrderExam()
	{
		super();
	}
	
	public OrderExam(Exam exam)
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

	public Order getOrder()
	{
		return order;
	}
	
	public void setOrder(Order order)
	{
		this.order = order;
	}
	
	public Exam getExam()
	{
		return exam;
	}

	public void setExam(Exam exam)
	{
		this.exam = exam;
	}

	public Integer getExamAmount()
	{
		return examAmount;
	}

	public void setExamAmount(Integer examAmount)
	{
		this.examAmount = examAmount;
	}

	public String getAnswerSheetAmount()
	{
		return answerSheetAmount;
	}

	public void setAnswerSheetAmount(String answerSheetAmount)
	{
		this.answerSheetAmount = answerSheetAmount;
	}

	public String getStudentsPerCSV()
	{
		return studentsPerCSV;
	}

	public void setStudentsPerCSV(String studentsPerCSV)
	{
		this.studentsPerCSV = studentsPerCSV;
	}


	@Override
	public String toString()
	{
		return "OrderExam [id=" + id + ", order=" + order + ", exam=" + exam + ", examAmount=" + examAmount + ", answerSheetAmount=" + answerSheetAmount + ", studentsPerCSV=" + studentsPerCSV + "]";
	}
}
