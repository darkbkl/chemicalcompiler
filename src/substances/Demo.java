package compiler.substances;

public class Demo {
    public static void main(String[] args) {
        Reactant r1 = new Reactant("O2", 2);
        Reactant r2 = new Reactant("H2", 0); 
        Product p = new Product("H2O", 0);

        System.out.println(r1 + " + " + r2 + " -> " + p);
    }
}