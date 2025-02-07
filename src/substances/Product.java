package compiler.substances;

/**
 * La classe <code>Product</code> représente un produit, qui est un type particulier de substance.
 */
public class Product extends Substance {

    /**
     * Constructeur permettant d'initialiser un produit avec un nom et une quantité.
     *
     * @param name     Le nom du produit.
     * @param quantity La quantité du produit.
     */
    public Product(String name, int quantity) {
        super(name, quantity);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du produit.
     *
     * @return Une chaîne représentant le produit sous la forme "Product(nom, quantité)".
     */
    @Override
    public String toString() {
        return "Product(" + super.name + ", " + super.quantity + ")";
    }
}