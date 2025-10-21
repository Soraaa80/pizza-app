package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Pizza;
import com.example.repository.PizzaRepository;

@Service // Indique que cette classe est un service Spring (composant métier)
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    // Injection du repository via le constructeur (bonne pratique pour l'injection)
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    // ========================
    // Récupérer toutes les pizzas
    // ========================
    public List<Pizza> getAllPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        pizzaRepository.findAll().forEach(pizzas::add); // Parcourt tous les résultats et les ajoute à la liste
        return pizzas;
    }

    // ========================
    // Récupérer une pizza par ID (nullable si non trouvée)
    // ========================
    public Pizza getPizza(Long id) {
        return pizzaRepository.findById(id).orElse(null); // Retourne null si l'ID n'existe pas
    }

    // ========================
    // Enregistrer ou mettre à jour une pizza (simple)
    // ========================
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza); // Save = insert ou update selon la présence d'un ID
    }

    // ========================
    // Mise à jour détaillée d'une pizza par ID (champ par champ)
    // ========================
    public Pizza updatePizza(Long id, Pizza pizzaDetails) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);
        if (optionalPizza.isPresent()) {
            Pizza pizza = optionalPizza.get();

            // Mise à jour des champs un par un
            pizza.setNamePizza(pizzaDetails.getNamePizza());
            pizza.setSizePizza(pizzaDetails.getSizePizza());
            pizza.setIngredientsPizza(pizzaDetails.getIngredientsPizza());
            pizza.setPricePizza(pizzaDetails.getPricePizza());
            pizza.setStatutPizza(pizzaDetails.getStatutPizza());
            pizza.setNameType(pizzaDetails.getNameType());
            pizza.setPhotoUrlPizza(pizzaDetails.getPhotoUrlPizza());
            pizza.setDescriptionPizza(pizzaDetails.getDescriptionPizza());

            return pizzaRepository.save(pizza); // Sauvegarde la mise à jour
        } else {
            return null; // Peut poser problème → à remplacer par une exception
        }
    }

    // ========================
    // Supprimer une pizza par ID
    // ========================
    public void deleteById(Long id) {
        pizzaRepository.deleteById(id);
    }

    // ========================
    // Récupérer une pizza ou lancer une exception si non trouvée
    // ========================
    public Pizza findById(Long id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza non trouvée avec id : " + id));
    }

    // ========================
    // Variante du findById avec message différent
    // ========================
    public Pizza getPizzaById(Long id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza non trouvée avec l'ID : " + id));
    }

}
