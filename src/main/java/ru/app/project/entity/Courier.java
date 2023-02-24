package ru.app.project.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "couriers")
public class Courier {
    @Id
    @Column(name = "phonenumber", nullable = false)
    private String phonenumber;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "deliverytype", nullable = false)
    private String deliverytype;

    @OneToMany(mappedBy = "courier")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Order> orders = new HashSet<>();

    public Courier() {
    }

    public Courier(String phonenumber, String password, String firstname, String lastname, String deliverytype) {
        this.phonenumber = phonenumber;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.deliverytype = deliverytype;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(String deliverytype) {
        this.deliverytype = deliverytype;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
