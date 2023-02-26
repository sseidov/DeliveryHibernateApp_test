package ru.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.app.project.DTO.OrderDishDTO;
import ru.app.project.service.OrderDishService;

@Controller
@RequestMapping("/orderDish")
public class OrderDishController {

    private final OrderDishService orderDishService ;
    @Autowired
    public OrderDishController(OrderDishService orderDishService) {
        this.orderDishService = orderDishService;
    }


    @GetMapping("/all/{orderID}")
    private String getAll(Model model, @PathVariable("orderID") int orderID){
        model.addAttribute("dishes", orderDishService.getAllDishesInOrder(orderID));
        return "orderDish/all";
    }

    @GetMapping("/new")
    public String addDishToOrder(Model model) {
        OrderDishDTO orderDishDTO = new OrderDishDTO();
        model.addAttribute("orderDishDTO", orderDishDTO);
        return "orderDish/add";
    }

    @PostMapping
    public String create(@ModelAttribute("orderDishDTO") OrderDishDTO orderDishDTO) {
        orderDishService.save(orderDishDTO);
//      change
        return "redirect:/orderDish/add";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id){
        model.addAttribute("orderDishDTO", new OrderDishDTO());
        return "orderDish/edit";
    }

    @PatchMapping("/{id}")
    public String upd(@ModelAttribute("orderDishDTO") OrderDishDTO orderDishDTO, @PathVariable("id") int id){
        orderDishDTO.setId(id);
        orderDishService.update(id, orderDishDTO);
//      change
        return "redirect:/order/all";
    }

}
