package ru.app.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.app.project.DTO.OrderDishDTO;
import ru.app.project.model.Dish;
import ru.app.project.model.Order;
import ru.app.project.model.OrderDish;

import java.util.List;

@Component
public class OrderDishDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDishDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(OrderDishDTO orderDishDTO) {
        Session session = sessionFactory.getCurrentSession();

        Dish dish = session.get(Dish.class, orderDishDTO.getDishID());
        Order order = session.get(Order.class, orderDishDTO.getOrderID());

        OrderDish orderDish = new OrderDish();
        orderDish.setOrder(order);
        orderDish.setDish(dish);
        orderDish.setQuantity(orderDishDTO.getQuantity());

        order.getOrderDishes().add(orderDish);
        dish.getDishOrders().add(orderDish);
        session.save(orderDish);
    }
    @Transactional
    public void update(int id, OrderDishDTO orderDishDTO) {
        Session session = sessionFactory.getCurrentSession();
        Query sessionQuery  = session.createQuery("from OrderDish where OrderDish.id=:id");
        sessionQuery.setParameter("id", id);
        OrderDish orderDish = (OrderDish) sessionQuery.uniqueResult();

        System.out.println(orderDish.getId());
        System.out.println(orderDish.getQuantity());
        System.out.println("123");
        orderDish.setQuantity(orderDishDTO.getQuantity());
    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(OrderDish.class, id));
    }

    @Transactional
    public List<OrderDish> getAllDishesInOrder(int orderID) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderID);
        Query query = session.createQuery("select o from OrderDish o where o.order=:order");
        query.setParameter("order", order);
        return query.getResultList();
    }

}
