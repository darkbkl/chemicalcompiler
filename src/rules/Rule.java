package compiler.rules;

import java.util.*;
import compiler.reaction.*;
import compiler.substances.*;

/**
 * La classe Rule représente une règle de transformation chimique, qui est une réaction chimique
 * associée à une logique conditionnelle de déclenchement. Elle permet de vérifier si une règle
 * peut être déclenchée par un ensemble donné de réactants, en fonction de leurs quantités.
 * 
 * La règle est définie par une réaction, et elle peut être activée si les réactants présents
 * dans l'ensemble de réactants sont suffisants pour satisfaire la condition de la règle.
 */
public class Rule {
    
    private Reaction reaction;

    /**
     * Constructeur de la classe Rule.
     * 
     * @param reaction L'objet {@link Reaction} représentant la règle de transformation chimique.
     */
    public Rule(Reaction reaction) {
        this.reaction = reaction;
    }

    /**
     * Retourne la réaction associée à cette règle.
     * 
     * @return L'objet {@link Reaction} qui est la règle de transformation chimique.
     */
    public Reaction getReactionRule() {
        return this.reaction;
    }

    public Set<Reactant> getRuleReactants() {
        return this.reaction.getReactants();
    }

    /**
     * Vérifie si la règle peut être déclenchée par un ensemble donné de réactants.
     * Une règle est déclenchée si, pour chaque réactant dans la règle, il y a suffisamment
     * de réactants dans l'ensemble donné pour satisfaire la quantité requise par la règle.
     * 
     * @param reactants L'ensemble des réactants disponibles ({@link Set} de {@link Reactant}).
     * @return {@code true} si la règle peut être déclenchée, {@code false} sinon.
     */
    public boolean isTriggerable(Set<Reactant> reactants) {
        int triggers = 0;
        // Parcourt les réactants dans l'ensemble fourni
        for (Reactant currentReactant : reactants) {
            // Compare avec les réactants de la réaction
            for (Reactant possibleReactant : this.reaction.getReactants()) {
                if (currentReactant.getName().equals(possibleReactant.getName()) && 
                    currentReactant.getQuantity() >= possibleReactant.getQuantity()) {
                    triggers += 1;
                }
            }
        }

        // La règle est déclenchable si le nombre de réactants valides correspond à la taille des réactants nécessaires
        return (triggers == this.reaction.getReactants().size());
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la règle.
     * La chaîne retournée est construite en utilisant un {@link StringBuilder} pour
     * améliorer les performances lors de la concaténation de chaînes.
     * 
     * @return Une chaîne représentant la règle sous le format "Rule( réaction )".
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Rule(").append(this.reaction.toString()).append(")");
        return res.toString();
    }
}