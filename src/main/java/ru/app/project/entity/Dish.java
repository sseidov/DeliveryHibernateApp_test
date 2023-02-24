package ru.app.project.entity;

import javax.persistence.*;
import java.math.BigDecimal;

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
}
