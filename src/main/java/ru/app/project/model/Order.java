package ru.app.project.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "deliverydate", nullable = false)
    private String date;
    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<OrderDish> orderDishes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customers_phonenumber", referencedColumnName = "phonenumber", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "couriers_phonenumber", referencedColumnName = "phonenumber", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Courier courier;

    public Order() {
    }

    public Order(String date, Customer customer, Courier courier) {
        this.date = date;
        this.customer = customer;
        this.courier = courier;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public List<OrderDish> getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(date, order.date) && Objects.equals(price, order.price) && Objects.equals(orderDishes, order.orderDishes) && Objects.equals(customer, order.customer) && Objects.equals(courier, order.courier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, price, orderDishes, customer, courier);
    }
}
