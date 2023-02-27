package ru.app.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.app.project.DTO.OrderDishDTO;
import ru.app.project.dao.OrderDishDAO;
import ru.app.project.model.OrderDish;

import java.util.List;

@Service
public class OrderDishService {
    private final OrderDishDAO orderDishDAO;

    @Autowired
    public OrderDishService(OrderDishDAO orderDishDAO) {
        this.orderDishDAO = orderDishDAO;
    }

    public void save(OrderDishDTO orderDishDTO) {
        orderDishDAO.save(orderDishDTO);
    }
    public void update(int id, OrderDishDTO orderDishDTO) {
        orderDishDAO.update(id, orderDishDTO);
    }
    public void delete(int id){
        orderDishDAO.delete(id);
    }
    public List<OrderDish> getAllDishesInOrder(int orderID) {
        return orderDishDAO.getAllDishesInOrder(orderID);
    }
}
