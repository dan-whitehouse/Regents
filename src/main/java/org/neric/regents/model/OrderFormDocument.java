package org.neric.regents.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderform_document")
public class OrderFormDocument implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderForm_document_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderForm_id", nullable = false)
    private OrderForm orderForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;


    public OrderFormDocument() {
        super();
    }

    public OrderFormDocument(Document document) {
        super();
        this.document = document;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "OrderFormDocument [id=" + id + ", orderForm=" + orderForm + ", document=" + document + "]";
    }
}
