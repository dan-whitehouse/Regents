package org.neric.regents.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "option_print")
public class OptionPrint implements Serializable {
    private static final long serialVersionUID = 1L;

    public OptionPrint() {
        super();
    }

    public OptionPrint(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public OptionPrint(Integer id, String name, Boolean visible, Boolean locked) {
        super();
        this.id = id;
        this.name = name;
        this.visible = visible;
        this.locked = locked;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_print_id", unique = true, nullable = false)
    private Integer id;

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;

    @NotEmpty
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "locked")
    private Boolean locked;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderPrint")
    private Set<Order> ordersPrint = new HashSet<>(0);


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Order> getOrdersPrint() {
        return ordersPrint;
    }

    public void setOrdersPrint(Set<Order> ordersPrint) {
        this.ordersPrint = ordersPrint;
    }


    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "OptionPrint [id=" + id + ", name=" + name + ", visible=" + visible + ", locked=" + locked + "]";
    }
}
