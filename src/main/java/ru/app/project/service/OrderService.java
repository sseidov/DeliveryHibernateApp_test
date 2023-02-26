package ru.app.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.app.project.DTO.OrderDTO;
import ru.app.project.dao.OrderDAO;
import ru.app.project.model.Order;

import java.util.List;

@Service
public class OrderService {
    private final OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void save(OrderDTO orderDTO){
        orderDAO.save(orderDTO);
    }
    public void delete(int id){
        orderDAO.delete(id);
    }
    public Order getOrderByID(int id){
        return orderDAO.getOrderByID(id);
    }


    public List<Order> getAllOrders() {

        return orderDAO.getAllOrders();
    }

    public void update(int id, OrderDTO orderDTO) {
        orderDAO.update(id, orderDTO);
    }
}
