package ru.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.app.project.model.Courier;
import ru.app.project.service.CourierService;

@Controller
@RequestMapping("/courier")
public class CourierController {

    private final CourierService courierService;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }
    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("couriers", courierService.listOfCouriers());
        return "courier/all";
    }

    @GetMapping("/{phonenumber}")
    private String getByEmail(@PathVariable("phonenumber") String phonenumber, Model model){
        model.addAttribute("courier", courierService.getCourierByPhoneNumber(phonenumber));
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
        model.addAttribute("orders", courierService.getCourierOrdersDate(phonenumber) );
        return "order/courierAll";
    }

    @PostMapping()
    public String registrationOfNewCustomer(@ModelAttribute("courier") Courier courier){
        courierService.save(courier);
        return "redirect:/courier/all";
    }

    @GetMapping("/{phonenumber}/edit")
    public String update(Model model, @PathVariable("phonenumber") String phonenumber){
        model.addAttribute("courier", courierService.getCourierByPhoneNumber(phonenumber));
        return "courier/edit";
    }

    @PatchMapping("/{phonenumber}")
    public String upd(@ModelAttribute("courier") Courier courier, @PathVariable("phonenumber") String phonenumber){
        courierService.update(phonenumber, courier);
        return "redirect:/courier/all";
    }

    @DeleteMapping("/{phonenumber}")
    public String delete(@PathVariable("phonenumber") String phonenumber){
        courierService.delete(phonenumber);
        return "redirect:/courier/all";
    }


}
