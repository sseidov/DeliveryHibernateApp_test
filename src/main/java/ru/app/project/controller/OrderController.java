package ru.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.app.project.dao.OrderDAO;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderDAO OrderDAO;
    @Autowired
    public OrderController(ru.app.project.dao.OrderDAO orderDAO) {
        OrderDAO = orderDAO;
    }
//
//    @GetMapping("/all")
//    private String getAll(Model model){
//        model.addAttribute("courier", OrderDAO.getThisOrderCourier());
//        return "order/orderCourier";
//    }
}
