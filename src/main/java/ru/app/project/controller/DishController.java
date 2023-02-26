package ru.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.app.project.model.Dish;
import ru.app.project.service.DishService;

@Controller
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("dishes", dishService.listOfDishesName());
        return "dish/all";
    }
    @GetMapping("/{name}")
    private String getByName(@PathVariable("name") String name, Model model){
        model.addAttribute("dish", dishService.getDishByName(name));
        return "dish/dishInfo";
    }

    @GetMapping("/new")
    public String newDish(Model model){
        Dish dish = new Dish();
        model.addAttribute("dish", dish );
        return "dish/add";
    }

    @PostMapping()
    public String createNewDish(@ModelAttribute("dish") Dish dish){
        dishService.save(dish);
        return "redirect:/dish/all";
    }

    @GetMapping("/{name}/edit")
    public String update(Model model, @PathVariable("name") String name){
        model.addAttribute("dish", dishService.getDishByName(name));
        return "dish/edit";
    }

    @PatchMapping("/{name}")
    public String upd(@ModelAttribute("dish") Dish dish, @PathVariable("name") String name){
        dishService.update(name, dish);
        return "redirect:/dish/all";
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable("name") String name){
        dishService.delete(name);
        return "redirect:/dish/all";
    }

}
