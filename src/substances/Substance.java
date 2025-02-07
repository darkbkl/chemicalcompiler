package compiler.substances;

import java.util.Objects;

/**
 * La classe <code>Substance</code> permet de représenter une substance avec un nom et une quantité.
 */
public class Substance {
    protected String name;
    protected int quantity;

    /**
     * Constructeur permettant d'initialiser une substance avec un nom et une quantité.
     *
     * @param name     Le nom de la substance.
     * @param quantity La quantité de la substance.
     */
    public Substance(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Retourne le nom de la substance.
     *
     * @return Le nom de la substance.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retourne la quantité de la substance.
     *
     * @return La quantité de la substance.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Modifie la quantité de la substance.
     *
     * @param quantity La nouvelle quantité de la substance.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calcule le code de hachage de la substance basé sur son nom.
     *
     * @return Le code de hachage de la substance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Compare cette substance avec un autre objet pour vérifier l'égalité.
     *
     * @param obj L'objet avec lequel comparer cette substance.
     * @return true si l'objet est une instance de <code>Substance</code> et possède le même nom, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Si c'est le même objet, ils sont égaux
        if (obj == null || getClass() != obj.getClass()) return false;
        Substance react = (Substance) obj;
        return Objects.equals(this.name, react.getName());
    }
}