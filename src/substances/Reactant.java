package compiler.substances;

import java.util.Objects;

/**
 * La classe <code>Reactant</code> représente un réactif, qui est un type particulier de substance.
 */
public class Reactant extends Substance {

    /**
     * Constructeur permettant d'initialiser un réactif avec un nom et une quantité.
     *
     * @param name     Le nom du réactif.
     * @param quantity La quantité du réactif.
     */
    public Reactant(String name, int quantity) {
        super(name, quantity);
    }

    public static Reactant parseReactant(Substance p) {
        return new Reactant(p.getName(), p.getQuantity());
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du réactif.
     *
     * @return Une chaîne représentant le réactif sous la forme "Reactant(nom, quantité)".
     */
    @Override
    public String toString() {
        return "Reactant(" + super.name + ", " + super.quantity + ")";
    }
}