package compiler.reactionengine;
import java.util.*;
import compiler.parser.*;
import compiler.rules.*;
import compiler.reaction.*;
import compiler.substances.*;
import compiler.reactionengine.*;


public class Demo {
    public static void main(String[] args) {
        Parser p = new Parser("compiler/parser/reactions.txt");
        String fileContent = p.readRulesFile();
        Builder b = new Builder(p);
        List<String> parsedReactions = p.parseReactions(fileContent);
        Set<Reactant> initialReactants = new HashSet<> (Set.of(
                                                                new Reactant("O2", 1), 
                                                                new Reactant("N2", 1),
                                                                new Reactant("H2", 6)));
        
        //Parsing des règles et transformation en rules
        Set<Reaction> reactions = b.buildReactions(parsedReactions);
        Set<Rule> rules = new HashSet<> (); 
        for (Reaction currentReaction : reactions)
            rules.add(new Rule(currentReaction));
        Reactant r1 = new Reactant("H2", 4);
        Reactant r2 = new Reactant("H2", 4);
        System.out.println(r1.equals(r2));

        ReactionEngine engine = new ReactionEngine(rules);
        System.out.println("Le système à l etat final : " + engine.triggerAllRules(initialReactants));
/*         for (Rule currentRule : rules){
            System.out.println("Règle en cours d'exécution : " + currentRule);
            System.out.println("Réactifs initiaux : " + initialReactants);
            System.out.println("Quantité de réactifs consommée : " + engine.getQuantityAfterReaction(currentRule, initialReactants));
            System.out.println("Le système à l'état final : " + engine.triggerRule(currentRule, initialReactants));
            System.out.println("==============================================================================");
        } */

        
    }
}