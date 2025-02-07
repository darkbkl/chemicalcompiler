package compiler.reaction;

import compiler.substances.*;
import java.util.Set;

/**
 * La classe Reaction représente une réaction chimique qui consiste en un ensemble de réactants et de produits.
 * Chaque réaction contient un ensemble d'objets {@link Reactant} et un ensemble d'objets {@link Product}.
 * 
 * La classe fournit des méthodes pour accéder à ces réactants et produits, ainsi qu'une méthode {@link #toString()} 
 * qui permet d'afficher la réaction sous forme de chaîne de caractères.
 */
public class Reaction {
    
    private Set<Reactant> reactants;
    private Set<Product> products;

    /**
     * Constructeur de la classe Reaction.
     * 
     * @param reactants Un ensemble de réactants de type {@link Reactant}, représentant les substances de départ dans la réaction.
     * @param products Un ensemble de produits de type {@link Product}, représentant les substances résultant de la réaction.
     */
    public Reaction(Set<Reactant> reactants, Set<Product> products) {
        this.reactants = reactants;
        this.products = products;
    }

    /**
     * Retourne l'ensemble des réactants de la réaction.
     * 
     * @return Un ensemble de réactants ({@link Set} de {@link Reactant}).
     */
    public Set<Reactant> getReactants() {
        return this.reactants;
    }

    /**
     * Retourne l'ensemble des produits de la réaction.
     * 
     * @return Un ensemble de produits ({@link Set} de {@link Product}).
     */
    public Set<Product> getProducts() {
        return this.products;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la réaction chimique.
     * La représentation suit le format : 
     * "(réactant1, réactant2, ...) -> (produit1, produit2, ...)".
     * 
     * Utilise un {@link StringBuilder} pour une construction plus efficace de la chaîne.
     * 
     * @return Une chaîne de caractères représentant la réaction chimique, incluant les réactants et les produits.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("(");
        
        // Ajoute les réactants à la chaîne de caractères
        for (Reactant r : this.reactants) {
            res.append(r.toString()).append(", ");
        }
        
        // Ajoute les produits à la chaîne de caractères
        for (Product p : this.products) {
            res.append(" ").append(p.toString());
        }
        
        res.append(")");
        return res.toString();
    }
}