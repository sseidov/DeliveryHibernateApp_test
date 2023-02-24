package ru.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.app.project.dao.CourierDAO;
import ru.app.project.entity.Courier;
import ru.app.project.entity.Order;

import java.util.Set;

@Controller
@RequestMapping("/courier")
public class CourierController {

    private final CourierDAO courierDAO;

    @Autowired
    public CourierController(CourierDAO courierDAO) {
        this.courierDAO = courierDAO;
    }

    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("couriers", courierDAO.listOfCouriers());
        return "courier/all";
    }

    @GetMapping("/{phonenumber}")
    private String getByEmail(@PathVariable("phonenumber") String phonenumber, Model model){
        model.addAttribute("courier", courierDAO.getCourierByPhonenumber(phonenumber));
        return "courier/courierInfo";
    }

    @GetMapping("/new")
    public String newCourier(Model model){
        Courier courier = new Courier();
        model.addAttribute("courier", courier );
        return "courier/add";
    }

    @GetMapping("/{phonenumber}/orders")
    public String getCourierOrderSet(Model model, @PathVariable("phonenumber") String phonenumber){
        Set<Order> orderSet = courierDAO.getThisOrderCourier(phonenumber);
        model.addAttribute("orders", orderSet );
        return "order/courierAll";
    }

    @PostMapping()
    public String registrationOfNewCustomer(@ModelAttribute("courier") Courier courier){
        courierDAO.save(courier);
        return "redirect:/courier/all";
    }

    @GetMapping("/{phonenumber}/edit")
    public String update(Model model, @PathVariable("phonenumber") String phonenumber){
        model.addAttribute("courier", courierDAO.getCourierByPhonenumber(phonenumber));
        return "courier/edit";
    }

    @PatchMapping("/{phonenumber}")
    public String upd(@ModelAttribute("courier") Courier courier, @PathVariable("phonenumber") String phonenumber){
        courierDAO.update(phonenumber, courier);
        return "redirect:/courier/all";
    }

    @DeleteMapping("/{phonenumber}")
    public String delete(@PathVariable("phonenumber") String phonenumber){
        courierDAO.delete(phonenumber);
        return "redirect:/courier/all";
    }


}
