package ru.app.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.app.project.entity.Courier;
import ru.app.project.entity.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Courier getCourierByPhonenumber(String phonenumber){
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
        Courier customer = getCourierByPhonenumber(phonenumber);
        session.remove(session.get(Courier.class, customer.getPhonenumber()));
    }

    @Transactional
    public Set<Order> getThisOrderCourier(String phonenumber){
        System.out.println("______________________________________________________");
        Set<Order> orderSet = new HashSet<>();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Order o where o.courier=:courier");
        query.setParameter("courier", phonenumber);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! " + query.getResultList().size());

        System.out.println(query.uniqueResult().getClass());
        System.out.println(query.uniqueResult());
        orderSet = (Set<Order>) query.getResultList();
//        orderSet = (Set<Order>) query.getResultList();
        return orderSet;
    }
}
