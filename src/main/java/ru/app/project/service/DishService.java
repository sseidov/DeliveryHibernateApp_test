package ru.app.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.app.project.dao.DishDAO;
import ru.app.project.model.Dish;

import java.util.List;

@Service
public class DishService {
    private final DishDAO dishDAO;

    @Autowired
    public DishService(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public void save(Dish dish) {
        dishDAO.save(dish);
    }

    public List<Dish> listOfDishesName() {
        return dishDAO.listOfDishesName();
    }

    public Dish getDishByName(String name){
        return dishDAO.getDishByName(name);
    }

    public void update( String name, Dish dish) {
        dishDAO.update(name, dish);
    }

    public void delete(String name){
        dishDAO.delete(name);
    }
}
