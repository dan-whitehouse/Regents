package org.neric.regents.model.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.neric.regents.model.District;
import org.neric.regents.model.Order;

public class DistrictOrder {
    private District district;
    private Long orderCount;
    private List<Order> orders;

    public DistrictOrder() {
        super();
        this.orders = new ArrayList<>();
    }

    public DistrictOrder(District district, Long orderCount) {
        super();
        this.district = district;
        this.orderCount = orderCount;
        this.orders = new ArrayList<>();
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
