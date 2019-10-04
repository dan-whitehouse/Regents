package org.neric.regents.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "optout")
public class OptOut implements Serializable {
    private static final long serialVersionUID = 1L;


    public OptOut() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "optout_id", unique = true, nullable = false)
    private Integer id;

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderform_id", nullable = false)
    private OrderForm orderForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User optOutUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "opt_out_date", nullable = false)
    private Date optOutDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public User getOptOutUser() {
        return optOutUser;
    }

    public void setOptOutUser(User optOutUser) {
        this.optOutUser = optOutUser;
    }

    public Date getOptOutDate() {
        return optOutDate;
    }

    public void setOptOutDate(Date optOutDate) {
        this.optOutDate = optOutDate;
    }

    @Override
    public String toString() {
        return "OptOut [id=" + id + ", uuid=" + uuid + ", orderForm=" + orderForm + ", district=" + district + ", optOutUser=" + optOutUser + "]";
    }

}
