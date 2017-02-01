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

	@Column(name="exam_amount", nullable=true)
	private Integer examAmount;

	@Column(name="answer_sheet_amount", nullable=true)
	private String answerSheetAmount;
	
	@Column(name="students_per_csv", nullable=true)
	private String studentsPerCSV;
	
	@Column(name="pas", nullable=true)
	private Boolean pearsonAnswerSheet;

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

	public Boolean getPearsonAnswerSheet()
	{
		return pearsonAnswerSheet;
	}

	public void setPearsonAnswerSheet(Boolean pearsonAnswerSheet)
	{
		this.pearsonAnswerSheet = pearsonAnswerSheet;
	}
}
