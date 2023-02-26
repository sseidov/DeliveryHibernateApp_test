package ru.app.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.app.project.DTO.OrderDTO;
import ru.app.project.model.Courier;
import ru.app.project.model.Customer;
import ru.app.project.model.Order;

import java.util.List;

@Component
public class OrderDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(OrderDTO orderDTO) {
        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class, orderDTO.getCustomerPhoneNumber());
        Courier courier = session.get(Courier.class, orderDTO.getCourierPhoneNumber());

        Order order = new Order(orderDTO.getDate(), customer, courier);
        courier.getOrders().add(order);
        customer.getOrders().add(order);

        session.save(order);
    }
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Order.class, id));
    }
    @Transactional
    public Order getOrderByID(int id){
        Session session = sessionFactory.getCurrentSession();
        Query sessionQuery  = session.createQuery("from Order where id=:id");
        sessionQuery.setParameter("id", id);
        return (Order) sessionQuery.uniqueResult();
    }
    @Transactional
    public List<Order> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    @Transactional
    public void update( int id, OrderDTO orderDTO) {
        Session session = sessionFactory.getCurrentSession();

        Query sessionQuery  = session.createQuery("from Order where id=:id");
        sessionQuery.setParameter("id", id);
        Order order = (Order) sessionQuery.uniqueResult();
        order.setDate(orderDTO.getDate());
    }
}
