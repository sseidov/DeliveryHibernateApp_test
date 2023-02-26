package ru.app.project.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ingredients", nullable = false)
    private String ingredients;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "weight", nullable = false)
    private int weight;

    @OneToMany(mappedBy = "dish")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<OrderDish> dishOrders = new ArrayList<>();

    public Dish() {
    }

    public Dish(String name, String ingredients, BigDecimal price, int weight) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id && weight == dish.weight && Objects.equals(name, dish.name) && Objects.equals(ingredients, dish.ingredients) && Objects.equals(price, dish.price) && Objects.equals(dishOrders, dish.dishOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredients, price, weight, dishOrders);
    }
}
