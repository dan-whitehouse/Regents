package org.neric.regents.wizard;

import org.neric.regents.model.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//http://howtodoinjava.com/spring/spring-mvc/spring-mvc-populate-and-validate-dropdown-example/

public class XForm2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String title;

    @NotNull
    private OptionPrint selectedOptionPrint;

    private boolean reportingOption;

    @NotNull
    private OptionScan selectedOptionScan;

    @NotNull
    private List<XExamWrapper> selectedExams = new ArrayList<>();

    @NotNull
    private List<XDocumentWrapper> selectedDocuments = new ArrayList<>();

    private District district;

    private School school;

    private String specialRequests;

    private OrderContact orderContact;

    private OrderForm orderForm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OptionPrint getSelectedOptionPrint() {
        return selectedOptionPrint;
    }

    public void setSelectedOptionPrint(OptionPrint selectedOptionPrint) {
        this.selectedOptionPrint = selectedOptionPrint;
    }

    public OptionScan getSelectedOptionScan() {
        return selectedOptionScan;
    }

    public void setSelectedOptionScan(OptionScan selectedOptionScan) {
        this.selectedOptionScan = selectedOptionScan;
    }

    public boolean isReportingOption() {
        return reportingOption;
    }

    public void setReportingOption(boolean reportingOption) {
        this.reportingOption = reportingOption;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public List<XExamWrapper> getSelectedExams() {
        return selectedExams;
    }

    public void setSelectedExams(List<XExamWrapper> selectedExams) {
        this.selectedExams = selectedExams;
    }

    public List<XDocumentWrapper> getSelectedDocuments() {
        return selectedDocuments;
    }

    public void setSelectedDocuments(List<XDocumentWrapper> selectedDocuments) {
        this.selectedDocuments = selectedDocuments;
    }

    public OrderContact getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(OrderContact orderContact) {
        this.orderContact = orderContact;
    }

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    @Override
    public String toString() {
        return "XForm2 [id=" + id + ", title=" + title + ", selectedOptionPrint=" + selectedOptionPrint + ", reportingOption=" + reportingOption + ", selectedOptionScan=" + selectedOptionScan + ", selectedExams=" + selectedExams + ", selectedDocuments=" + selectedDocuments + "]";
    }
}
