package compiler.parser;
import java.util.*;
import compiler.rules.*;

public class Demo {
    public static void main(String[] args) {
        Parser p = new Parser("compiler/parser/reactions.txt");
        String rules = "2H2 + O2 -> 2H2O; N2 + 3H2 -> 2NH3; C6H12O6 + 6O2 -> 6CO2 + 6H2O;";
        Builder b = new Builder(p);
        String reaction = (p.parseReactions(rules)).get(2);
        Rule rule = new Rule(b.buildReaction(reaction));
        System.out.println(reaction);
        System.out.println(rule);
        
    }
}