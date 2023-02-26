package ru.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.app.project.model.Customer;
import ru.app.project.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("customers", customerService.listOfCustomersEmail());
        return "customer/all";
    }

    @GetMapping("/{email}")
    private String getByEmail(@PathVariable("email") String email, Model model){
        model.addAttribute("customer", customerService.getCustomerByEmail(email));
        return "customer/customerInfo";
    }

    @GetMapping("/{phonenumber}/orders")
    public String getCourierOrderSet(Model model, @PathVariable("phonenumber") String phonenumber){
        model.addAttribute("orders", customerService.getCustomerOrdersDate(phonenumber) );
        return "order/courierAll";
    }

    @GetMapping("/new")
    public String newCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer );
        return "customer/add";
    }

    @PostMapping()
    public String registrNewCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        return "redirect:/customer/all";
    }

    @GetMapping("/{email}/edit")
    public String update(Model model, @PathVariable("email") String email){
        model.addAttribute("customer", customerService.getCustomerByEmail(email));
        return "customer/edit";
    }

    @PatchMapping("/{email}")
    public String upd(@ModelAttribute("customer") Customer customer, @PathVariable("email") String email){
        customerService.update(email, customer);
        return "redirect:/customer/all";
    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable("email") String email){
        customerService.delete(email);
        return "redirect:/customer/all";
    }
}
