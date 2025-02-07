package compiler.reactionengine;

import java.util.*;
import compiler.rules.*;
import compiler.substances.*;

/**
 * La classe ReactionEngine est responsable de la gestion et de l'application des règles chimiques (de type {@link Rule})
 * sur un ensemble donné de réactants ({@link Reactant}). Elle permet de vérifier si une règle peut être appliquée (déclenchée)
 * et de calculer les résultats après réaction, incluant les produits générés et les réactifs excédentaires.
 * 
 * Les principales opérations sont l'application des règles, le calcul de la quantité des produits générés,
 * et la gestion des réactifs restants après réaction.
 */
public class ReactionEngine {

    private Set<Rule> rules;

    /**
     * Constructeur de la classe ReactionEngine.
     * 
     * @param rules L'ensemble des règles ({@link Rule}) à appliquer sur les réactants.
     */
    public ReactionEngine(Set<Rule> rules) {
        this.rules = rules;
    }

    /**
     * Retourne l'ensemble des règles actuellement gérées par le moteur de réaction.
     * 
     * @return Un ensemble de règles ({@link Set} de {@link Rule}).
     */
    public Set<Rule> getRules() {
        return this.rules;
    }

    public Set<Reactant> getAllReactants() {
        Set<Reactant> allReactants = new HashSet<> ();
        for (Rule r : this.rules) {
            allReactants.addAll(r.getRuleReactants());
        }

        return allReactants;
    }

    /**
     * Calcule la quantité des réactants après l'application de la réaction définie par la règle.
     * Si la règle peut être déclenchée avec les réactants donnés, cette méthode retourne
     * la quantité minimale de réactants nécessaires pour réaliser la réaction.
     * 
     * @param rule La règle ({@link Rule}) à appliquer.
     * @param reactants L'ensemble des réactants disponibles ({@link Set} de {@link Reactant}).
     * @return La quantité minimale de réactants nécessaires pour la réaction, ou 0 si la règle ne peut pas être appliquée.
     */
    public int getQuantityAfterReaction(Rule rule, Set<Reactant> reactants) {
        int minimumQuantiity = 0;

        // Vérifie si la règle peut être déclenchée avec les réactants donnés
        if (rule.isTriggerable(reactants)) {
            // Parcours des réactants pour déterminer la quantité minimale
            for (Reactant initReactant : reactants) {
                for (Reactant ruleCurrentReactant : rule.getReactionRule().getReactants()) {
                    if (initReactant.getName().equals(ruleCurrentReactant.getName())) {
                        int currentQuantity = initReactant.getQuantity() / ruleCurrentReactant.getQuantity();
                        if (minimumQuantiity == 0 || currentQuantity < minimumQuantiity) {
                            minimumQuantiity = currentQuantity;
                        }
                    }
                }
            }
        }

        return minimumQuantiity;
    }

    /**
     * Applique la règle à un ensemble donné de réactants et retourne l'ensemble des produits générés
     * après la réaction. Si des réactifs restent en excédent après la réaction, ils sont également ajoutés
     * à l'ensemble des produits sous forme de {@link Reactant}.
     * 
     * @param rule La règle ({@link Rule}) à appliquer.
     * @param reactants L'ensemble des réactants disponibles ({@link Set} de {@link Reactant}).
     * @return Un ensemble de {@link Substance}, incluant les produits générés et les réactifs excédentaires.
     */
    public Set<Substance> triggerRule(Rule rule, Set<Reactant> reactants) {
        int quantityAfterReaction = this.getQuantityAfterReaction(rule, reactants);
        int currentQuantity = 0;
        Set<Substance> products = new HashSet<>();        
        
        // Si la règle peut être déclenchée, appliquer la réaction
        if (quantityAfterReaction > 0) {
            /*
             * Ajout des réactifs excédentaires dans l'ensemble de produits.
             */
            for (Reactant currentReactant : reactants) {
                for (Reactant requiredReactant : rule.getReactionRule().getReactants()) {
                    if (rule.getReactionRule().getReactants().contains(currentReactant) && currentReactant.equals(requiredReactant)) {
                        // Quantité non consommée des réactifs excédentaires
                        currentQuantity = currentReactant.getQuantity() - quantityAfterReaction * requiredReactant.getQuantity();
                        
                        if (currentQuantity > 0)
                            products.add(new Reactant(currentReactant.getName(), currentQuantity));
                    } else if (!rule.getReactionRule().getReactants().contains(currentReactant))
                        products.add(new Reactant(currentReactant.getName(), currentReactant.getQuantity()));

                    
                }
            }

            // Ajout des produits dans l'ensemble des produits
            for (Product currentProduct : rule.getReactionRule().getProducts()) {
                currentQuantity = currentProduct.getQuantity() * quantityAfterReaction;
                if (currentQuantity > 0) {
                    currentProduct.setQuantity(currentQuantity);
                    products.add(currentProduct);
                }
            }
        }

        return products;
    }

    public Set<Substance> triggerAllRules(Set<Reactant> reactants) {
        Set<Substance> systemState = new HashSet<> ();
        Set<Reactant> sysInterState = new HashSet<> (reactants);
        int rulesSize = this.rules.size();
        int currentRulePos = 1;
        for (Rule currentRule : this.rules) {
            if(currentRulePos < rulesSize){ 
                for (Substance currentSubstance : sysInterState){
                    Set<Substance> currentSubs = triggerRule(currentRule, (sysInterState.isEmpty() ? reactants : sysInterState));
                    for(Substance r : currentSubs) {
                        if (currentSubstance.getName().equals(r.getName()))
                                r.setQuantity(r.getQuantity() - currentSubstance.getQuantity());
                        else{
                            if (currentSubstance instanceof Product && !this.getAllReactants().contains(Reactant.parseReactant(currentSubstance)))
                                systemState.add(currentSubstance);
                            else 
                                sysInterState.add(new Reactant(currentSubstance.getName(), currentSubstance.getQuantity()));

                        }
                                
                         
                    }
                    
                }

            } 
            else
                systemState.addAll(triggerRule(currentRule, sysInterState));

            currentRulePos++;
        }
        
        System.out.println(sysInterState);
        return systemState;
    }
   

}
