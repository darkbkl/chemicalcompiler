package compiler.parser;

import java.util.*;
import compiler.substances.*;
import compiler.reaction.*;

/**
 * La classe Builder est responsable de la construction de différents objets liés à une réaction chimique
 * à partir de chaînes de caractères représentant des réactants et des produits.
 * Elle utilise un objet {@link Parser} pour analyser les parties d'une réaction chimique et en extraire
 * des informations utiles pour la création d'objets Reactant, Product et Reaction.
 */
public class Builder {
    
    private Parser parser;

    /**
     * Constructeur de la classe Builder.
     * @param parser L'instance de {@link Parser} utilisée pour analyser les réactants et les produits.
     */
    public Builder(Parser parser) {
        this.parser = parser;
    }

    /**
     * Retourne l'instance de {@link Parser} utilisée par le Builder.
     * @return L'objet {@link Parser} associé à ce Builder.
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Construit un ensemble de réactants à partir d'une chaîne de caractères analysée représentant des réactants.
     * La chaîne d'entrée doit être sous forme de réactants séparés par un signe "+", et chaque réactant doit
     * être spécifié avec une quantité suivie de son nom.
     * 
     * Exemple : "2 H2O + 1 O2" sera converti en un ensemble de réactants avec des quantités et des noms.
     *
     * @param parsedReactants La chaîne de caractères représentant les réactants, après analyse du Parser.
     * @return Un ensemble de réactants, chacun étant un objet {@link Reactant} avec un nom et une quantité.
     */
    public Set<Reactant> buildReactants(String parsedReactants) {
        Set<Reactant> reactants = new HashSet<> ();
        String currentReaction = (parsedReactants.replace("->", "")).trim();
        
        String[] reactantsString = currentReaction.split("\\+");
        
        
        for (int i = 0; i < reactantsString.length; i++) {
            reactantsString[i] = reactantsString[i].trim();
            if (!currentReaction.equals("")){
                String quantity = "";
                String name = "";
                int j = 0;
                Character currentCharacter = reactantsString[i].charAt(j);
                
                while(j < reactantsString[i].length() &&  Character.isDigit(currentCharacter)){
                    quantity += reactantsString[i].charAt(j);
                    currentCharacter = reactantsString[i].charAt(j);
                    j++;
                    currentCharacter = reactantsString[i].charAt(j);
                } 

                name += reactantsString[i].substring(j);
                reactants.add(new Reactant(name.trim(), ((quantity.equals("")) ? 1 : Integer.parseInt(quantity))));
            }
        } 
        return reactants;
    }

    /**
     * Construit un ensemble de produits à partir d'une chaîne de caractères analysée représentant des produits.
     * La chaîne d'entrée doit être sous forme de produits séparés par un signe "+", et chaque produit doit
     * être spécifié avec une quantité suivie de son nom.
     * 
     * Exemple : "2 H2O + 1 O2" sera converti en un ensemble de produits avec des quantités et des noms.
     *
     * @param parsedProducts La chaîne de caractères représentant les produits, après analyse du Parser.
     * @return Un ensemble de produits, chacun étant un objet {@link Product} avec un nom et une quantité.
     */
    public Set<Product> buildProducts(String parsedProducts) {
        Set<Product> products = new HashSet<> ();
        String currentReaction = (parsedProducts.replaceAll("->", "")).trim();
        String[] productsString = currentReaction.split("\\+");
        
        for (int i = 0; i < productsString.length; i++) {
            productsString[i] = productsString[i].trim();
            if (!currentReaction.equals("")){
                String quantity = "";
                String name = "";
                int j = 0;
                Character currentCharacter = productsString[i].charAt(j);
                
                while(Character.isDigit(currentCharacter)){
                    quantity += productsString[i].charAt(j);
                    currentCharacter = productsString[i].charAt(j);
                    j++;
                    currentCharacter = productsString[i].charAt(j);
                } 
                name += productsString[i].substring(j);
                products.add(new Product(name.trim(), ((quantity.equals("")) ? 1 : Integer.parseInt(quantity))));
            }
        } 
        return products;
    }

    /**
     * Construit une réaction chimique à partir d'une chaîne de caractères analysée représentant une réaction.
     * La réaction est constituée de réactants et de produits, qui sont extraits et convertis en objets appropriés
     * avant de créer l'objet {@link Reaction}.
     * 
     * La chaîne d'entrée doit être sous la forme d'une réaction, par exemple "2 H2O + 1 O2 -> 2 H2O2".
     *
     * @param parsedReaction La chaîne de caractères représentant la réaction, après analyse du Parser.
     * @return Un objet {@link Reaction} contenant les réactants et les produits correspondants.
     */
    public Reaction buildReaction(String parsedReaction) {
        String parsedReactants = this.parser.parseReactants(parsedReaction).get(0);
        String parsedProducts = this.parser.parseProducts(parsedReaction).get(0);
        Set<Reactant> reactants = this.buildReactants(parsedReactants);
        Set<Product> products = this.buildProducts(parsedProducts);
        Reaction reaction = new Reaction(reactants, products);

        return reaction;
    }

    /**
     * Construit un ensemble de réactions à partir d'une liste de chaînes de caractères représentant des réactions.
     * Chaque chaîne est traitée pour créer un objet {@link Reaction} correspondant.
     *
     * @param parsedReactions Une liste de chaînes de caractères représentant plusieurs réactions.
     * @return Un ensemble de réactions, chacune étant un objet {@link Reaction} construit à partir des chaînes d'entrée.
     */
    public Set<Reaction> buildReactions(List<String> parsedReactions) {
        Set<Reaction> builtReactions = new HashSet<> ();
        for (int i = 0; i < parsedReactions.size(); i++) {
            builtReactions.add(this.buildReaction(parsedReactions.get(i)));
        }
        return builtReactions;
    }
}