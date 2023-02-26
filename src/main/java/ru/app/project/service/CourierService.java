package ru.app.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.app.project.dao.CourierDAO;
import ru.app.project.model.Order;
import ru.app.project.model.Courier;

import java.util.List;

@Service
public class CourierService {
    private final CourierDAO courierDAO;

    @Autowired
    public CourierService(CourierDAO courierDAO) {
        this.courierDAO = courierDAO;
    }

    @Transactional(readOnly = true)
    public List<Courier> listOfCouriers() {
        return courierDAO.listOfCouriers();
    }
    @Transactional
    public void save(Courier courier) {
        courierDAO.save(courier);
    }


    @Transactional(readOnly = true)
    public Courier getCourierByPhoneNumber(String phoneNumber){
        return courierDAO.getCourierByPhoneNumber(phoneNumber);
    }

    @Transactional
    public void update(String phoneNumber, Courier courier) {
        courierDAO.update(phoneNumber, courier);
    }
    @Transactional
    public void delete(String phoneNumber){
        courierDAO.delete(phoneNumber);
    }
    public List<Order> getCourierOrdersDate(String phoneNumber){
        return courierDAO.getCourierOrdersDate(phoneNumber);
    }
}
