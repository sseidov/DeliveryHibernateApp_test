package ru.app.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.app.project.model.Customer;
import ru.app.project.model.Order;

import java.util.List;

@Component
public class CustomerDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Customer> listOfCustomersEmail() {
        return sessionFactory.getCurrentSession().createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }
    @Transactional
    public void save(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }


    @Transactional(readOnly = true)
    public Customer getCustomerByEmail(String email){
        Session session = sessionFactory.getCurrentSession();
        Query sessionQuery  = session.createQuery("from Customer where email=:email");
        sessionQuery.setParameter("email", email);
        return (Customer) sessionQuery.uniqueResult();
    }

    @Transactional
    public void update(String email, Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        Query sessionQuery  = session.createQuery("from Customer where email=:email");
        sessionQuery.setParameter("email", email);
        Customer customerToBeUpdated = (Customer) sessionQuery.uniqueResult();

        customerToBeUpdated.setPassword(customer.getPassword());
    }
    @Transactional
    public void delete(String email){
        Session session = sessionFactory.getCurrentSession();
        Customer customer = getCustomerByEmail(email);
        session.remove(session.get(Customer.class, customer.getPhonenumber()));
    }

    @Transactional
    public List<Order> getCustomerOrdersDate(String phoneNumber){
        Session session = sessionFactory.getCurrentSession();
        Query sessionQuery  = session.createQuery("from Customer where phonenumber=:phoneNumber");
        sessionQuery.setParameter("phoneNumber", phoneNumber);
        Customer customer = (Customer) sessionQuery.uniqueResult();

        Query query = session.createQuery("select o from Order o where o.customer=:customer");
        query.setParameter("customer", customer);
        return query.getResultList();
    }
}
