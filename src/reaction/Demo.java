package compiler.reaction;
import compiler.substances.*;
import java.util.Set;
import java.util.HashSet;

public class Demo {
    public static void main(String[] args) {
        Reactant r1 = new Reactant("O2", 2);
        Reactant r2 = new Reactant("H2", 2); 
        Reactant r3 = new Reactant("O2", 2);
        Reactant r4 = new Reactant("H2", 1);
        Product p = new Product("H2O", 2);
        Set<Reactant> reactants = new HashSet<> ();
        Set<Reactant> reactants2 = new HashSet<> ();
        Set<Product> products = new HashSet<> ();
        reactants.add(r1); reactants.add(r2); products.add(p);
        reactants2.add(r3); reactants2.add(r4); 

        Reaction reaction = new Reaction(reactants, products);
        System.out.println(reaction);
        if (reaction.isTriggerable(reactants2))
            System.out.println("It's triggerable");
        else 
            System.out.println("It's not triggerable");
    }
}