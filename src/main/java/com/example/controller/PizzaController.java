package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.data.Pizza;
import com.example.service.PizzaService;

@Controller // Indique que cette classe est un contrôleur Spring MVC
@RequestMapping("/pizzas") // Préfixe commun à toutes les routes de ce contrôleur
public class PizzaController {

    private final PizzaService pizzaService;

    // Injection du service PizzaService via le constructeur
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    // ============================
    // Lister toutes les pizzas
    // ============================

    @GetMapping
    public String getAllPizzas(Model model) {
        List<Pizza> pizzas = pizzaService.getAllPizzas(); // Récupère la liste des pizzas
        model.addAttribute("pizzas", pizzas); // Ajoute la liste au modèle pour la vue
        return "pizzas"; // Renvoie le nom du template Thymeleaf (pizzas.html)
    }

    // ============================
    // Afficher les détails d'une pizza
    // ============================

    @GetMapping("/{id}")
    public String getPizzaById(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.getPizza(id); // Récupère la pizza par son id
        model.addAttribute("pizza", pizza); // Ajoute la pizza au modèle
        return "pizzas/pizzadetails"; // Template pour afficher les détails (pizzadetails.html)
    }

    // ============================
    // Formulaire d'ajout
    // ============================

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("pizza", new Pizza()); // Prépare un objet vide pour le binding
        return "pizzas/add"; // Affiche le formulaire d'ajout (add.html)
    }

    // Traitement du formulaire d'ajout
    @PostMapping("/add")
    public String addPizza(@ModelAttribute Pizza pizza) {
        pizzaService.savePizza(pizza); // Enregistre la nouvelle pizza
        return "redirect:/pizzas"; // Redirige vers la liste après l'ajout
    }

    // ============================
    // Confirmation de suppression
    // ============================

    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.findById(id); // Récupère la pizza à supprimer
        model.addAttribute("pizza", pizza);
        return "pizzas/delete"; // Page de confirmation (delete.html)
    }

    // Traitement de la suppression
    @PostMapping("/delete/{id}")
    public String deletePizza(@PathVariable Long id) {
        pizzaService.deleteById(id); // Supprime la pizza
        return "redirect:/pizzas"; // Redirige vers la liste
    }

    // ============================
    // Formulaire de modification
    // ============================

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.getPizzaById(id); // Récupère la pizza à modifier
        model.addAttribute("pizza", pizza); // Ajoute au modèle pour pré-remplir le formulaire
        return "pizzas/edit"; // Page de modification (edit.html)
    }

    // Traitement du formulaire de modification
    @PostMapping("/edit/{id}")
    public String updatePizza(@PathVariable Long id, @ModelAttribute Pizza pizza) {
        pizza.setIdPizza(id); // Assure que l'ID est bien défini
        pizzaService.savePizza(pizza); // Enregistre les modifications (ou appelle updatePizza si méthode dédiée)
        return "redirect:/pizzas"; // Redirige vers la liste
    }
}
