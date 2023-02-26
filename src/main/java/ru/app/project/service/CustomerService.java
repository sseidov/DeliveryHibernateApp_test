package ru.app.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.app.project.dao.CustomerDAO;
import ru.app.project.model.Customer;
import ru.app.project.model.Order;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> listOfCustomersEmail() {
        return customerDAO.listOfCustomersEmail();
    }

    public Customer getCustomerByEmail(String email){
        return customerDAO.getCustomerByEmail(email);
    }

    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    public void update(String email, Customer customer) {
        customerDAO.update(email, customer);
    }

    public void delete(String email) {
        customerDAO.delete(email);
    }

    public List<Order>  getCustomerOrdersDate(String phoneNumber) {
        return customerDAO.getCustomerOrdersDate(phoneNumber);
    }
}
