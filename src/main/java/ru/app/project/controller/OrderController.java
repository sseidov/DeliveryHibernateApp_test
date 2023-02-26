package ru.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.app.project.DTO.OrderDTO;
import ru.app.project.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/all";
    }

    @GetMapping("/{id}")
    private String getByName(@PathVariable("id") int id, Model model){
        model.addAttribute("order", orderService.getOrderByID(id));
        return "order/orderInfo";
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        OrderDTO orderDTO = new OrderDTO();
        model.addAttribute("orderDTO", orderDTO);
        return "order/new";
    }

    @PostMapping
    public String create(@ModelAttribute("orderDTO") OrderDTO orderDTO) {
        orderService.save(orderDTO);
        return "redirect:/orderDish/new";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        orderService.delete(id);
        return "redirect:/order/all";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id){
        model.addAttribute("orderDTO", new OrderDTO());
        return "order/edit";
    }

    @PatchMapping("/{id}")
    public String upd(@ModelAttribute("orderDTO") OrderDTO orderDTO, @PathVariable("id") int id){
        orderDTO.setId(id);
        orderService.update(id, orderDTO);
        return "redirect:/order/all";
    }


}
