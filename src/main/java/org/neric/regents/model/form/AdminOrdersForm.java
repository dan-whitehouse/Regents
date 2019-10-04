package org.neric.regents.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class AdminOrdersForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private List<SelectedAction> selectedOrders = new ArrayList<>();

    @NotNull
    private String action;

    public List<SelectedAction> getSelectedOrders() {
        return selectedOrders;
    }

    public void setSelectedOrders(List<SelectedAction> selectedOrders) {
        this.selectedOrders = selectedOrders;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


}
