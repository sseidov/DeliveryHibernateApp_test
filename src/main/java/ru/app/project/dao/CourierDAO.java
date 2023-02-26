package ru.app.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.app.project.model.Courier;
import ru.app.project.model.Order;
import java.util.List;

@Component
public class CourierDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CourierDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Courier> listOfCouriers() {
        return sessionFactory.getCurrentSession().createQuery("SELECT c FROM Courier c", Courier.class).getResultList();
    }
    @Transactional
    public void save(Courier courier) {
        sessionFactory.getCurrentSession().save(courier);
    }

    @Transactional(readOnly = true)
    public Courier getCourierByPhoneNumber(String phonenumber){
        Session session = sessionFactory.getCurrentSession();
        Query sessionQuery  = session.createQuery("from Courier where phonenumber=:phonenumber");
        sessionQuery.setParameter("phonenumber", phonenumber);
        return (Courier) sessionQuery.uniqueResult();
    }

    @Transactional
    public void update( String phonenumber, Courier courier) {
        Session session = sessionFactory.getCurrentSession();
        Query sessionQuery  = session.createQuery("from Courier where phonenumber=:phonenumber");
        sessionQuery.setParameter("phonenumber", phonenumber);
        Courier courierToBeUpdated = (Courier) sessionQuery.uniqueResult();
        courierToBeUpdated.setDeliverytype(courier.getDeliverytype());
    }
    @Transactional
    public void delete(String phonenumber){
        Session session = sessionFactory.getCurrentSession();
        Courier customer = getCourierByPhoneNumber(phonenumber);
        session.remove(session.get(Courier.class, customer.getPhonenumber()));
    }

    @Transactional
    public List<Order> getCourierOrdersDate(String phonenumber){
        Session session = sessionFactory.getCurrentSession();
        Courier courier = getCourierByPhoneNumber(phonenumber);
        Query query = session.createQuery("select o from Order o where o.courier=:courier");
        query.setParameter("courier", courier);
        return query.getResultList();
    }
}
