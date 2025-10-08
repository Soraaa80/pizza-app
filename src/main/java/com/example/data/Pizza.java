package com.example.data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity // Indique que cette classe est une entité JPA (mappée à une table)
public class Pizza implements Serializable {

	private static final long serialVersionUID = 1L; // Pour assurer la compatibilité lors de la sérialisation

	@Id // Clé primaire de l'entité
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation (dépend du SGBD)
	@Column(name = "id_pizza") // Nom de la colonne dans la base de données
	private long idPizza;

	@Column(name = "name_pizza", nullable = false, length = 100)
	private String namePizza; // Nom de la pizza (obligatoire, max 100 caractères)

	@Enumerated(EnumType.STRING) // Stocké sous forme de chaîne (ex: "SMALL"), pas d'ordinal
	@Column(name = "size_pizza", nullable = false, length = 10)
	private PizzaSize sizePizza; // Taille de la pizza (enum : SMALL, MEDIUM)

	@Column(name = "ingredients_pizza", nullable = false, columnDefinition = "TEXT")
	private String ingredientsPizza; // Liste des ingrédients, stockée en texte long

	@Column(name = "price_pizza", nullable = false, precision = 5, scale = 2)
	private BigDecimal pricePizza; // Prix avec précision de 5 chiffres (ex : 999.99)

	@Enumerated(EnumType.STRING)
	@Column(name = "statut_pizza", nullable = false, length = 10)
	private PizzaStatut statutPizza; // Statut (enum : AVAILABLE, etc.)

	@Column(name = "name_type", nullable = false, length = 100)
	private String nameType; // Type de pizza (ex: Végétarienne, Viande…)

	@Column(name = "photo_url_pizza", length = 255)
	private String photoUrlPizza; // URL de l’image (optionnel)

	@Column(name = "description_pizza", columnDefinition = "TEXT")
	private String descriptionPizza; // Description longue (optionnelle)

	// === ENUMS ===

	public enum PizzaStatut {
		AVAILABLE, UNAVAILABLE, IN_PREPARATION
	}

	public enum PizzaSize {
		SMALL, MEDIUM,
		// ❗ Attention : il manque une taille ici (LARGE ?)
	}

	// === GETTERS & SETTERS ===

	public long getIdPizza() {
		return idPizza;
	}

	public void setIdPizza(long idPizza) {
		this.idPizza = idPizza;
	}

	public String getNamePizza() {
		return namePizza;
	}

	public void setNamePizza(String namePizza) {
		this.namePizza = namePizza;
	}

	public PizzaSize getSizePizza() {
		return sizePizza;
	}

	public void setSizePizza(PizzaSize sizePizza) {
		this.sizePizza = sizePizza;
	}

	public String getIngredientsPizza() {
		return ingredientsPizza;
	}

	public void setIngredientsPizza(String ingredientsPizza) {
		this.ingredientsPizza = ingredientsPizza;
	}

	public BigDecimal getPricePizza() {
		return pricePizza;
	}

	public void setPricePizza(BigDecimal pricePizza) {
		this.pricePizza = pricePizza;
	}

	public PizzaStatut getStatutPizza() {
		return statutPizza;
	}

	public void setStatutPizza(PizzaStatut statutPizza) {
		this.statutPizza = statutPizza;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getPhotoUrlPizza() {
		return photoUrlPizza;
	}

	public void setPhotoUrlPizza(String photoUrlPizza) {
		this.photoUrlPizza = photoUrlPizza;
	}

	public String getDescriptionPizza() {
		return descriptionPizza;
	}

	public void setDescriptionPizza(String descriptionPizza) {
		this.descriptionPizza = descriptionPizza;
	}

	// === toString() ===

	@Override
	public String toString() {
		return "Pizza{" +
			"idPizza=" + idPizza +
			", namePizza='" + namePizza + '\'' +
			", sizePizza=" + sizePizza +
			", ingredientsPizza='" + ingredientsPizza + '\'' +
			", pricePizza=" + pricePizza +
			", statutPizza=" + statutPizza +
			", nameType='" + nameType + '\'' +
			", photoUrlPizza='" + photoUrlPizza + '\'' +
			", descriptionPizza='" + descriptionPizza + '\'' +
			'}';
	}
}
