package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Pizza;
import com.example.service.PizzaService;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public String getAllPizzas(Model model) {
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzas", pizzas);
        return "pizzas";
    }

    @GetMapping("/{id}")
    public String getPizzaById(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.getPizza(id);
        model.addAttribute("pizza", pizza);
        return "pizzas/pizzadetails";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        // AJOUT : Passer les tailles et statuts au template
        model.addAttribute("pizzaSizes", Arrays.asList("SMALL", "MEDIUM", "LARGE", "EXTRA_LARGE"));
        model.addAttribute("pizzaStatuts", Arrays.asList("AVAILABLE", "UNAVAILABLE", "COMING_SOON"));
        return "pizzas/add";
    }

    @PostMapping("/add")
    public String addPizza(@ModelAttribute Pizza pizza) {
        pizzaService.savePizza(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.findById(id);
        model.addAttribute("pizza", pizza);
        return "pizzas/delete";
    }

    @PostMapping("/delete/{id}")
    public String deletePizza(@PathVariable Long id) {
        pizzaService.deleteById(id);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.getPizzaById(id);
        model.addAttribute("pizza", pizza);
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePizza(@PathVariable Long id, @ModelAttribute Pizza pizza) {
        pizza.setIdPizza(id);
        pizzaService.savePizza(pizza);
        return "redirect:/pizzas";
    }
}