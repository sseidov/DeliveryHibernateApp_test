package ru.app.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.app.project.model.Dish;

import java.util.List;

@Component
public class DishDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public DishDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Dish dish) {
        sessionFactory.getCurrentSession().save(dish);
    }

    @Transactional(readOnly = true)
    public List<Dish> listOfDishesName() {
        return sessionFactory.getCurrentSession().createQuery("SELECT d FROM Dish d", Dish.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Dish getDishByName(String name){
        Session session = sessionFactory.getCurrentSession();
        Query sessionQuery  = session.createQuery("from Dish where name=:name");
        sessionQuery.setParameter("name", name);
        return (Dish) sessionQuery.uniqueResult();
    }

    @Transactional
    public void update( String name, Dish dish) {
        Session session = sessionFactory.getCurrentSession();

        Query sessionQuery  = session.createQuery("from Dish where name=:name");
        sessionQuery.setParameter("name", name);
        Dish dishToBeUpdated = (Dish) sessionQuery.uniqueResult();

        dishToBeUpdated.setName(dish.getName());
        dishToBeUpdated.setIngredients(dish.getIngredients());
        dishToBeUpdated.setPrice(dish.getPrice());
        dishToBeUpdated.setWeight(dish.getWeight());
    }
    @Transactional
    public void delete(String name){
        Session session = sessionFactory.getCurrentSession();
        Dish dish = getDishByName(name);
        session.remove(session.get(Dish.class, dish.getId()));
    }

}
