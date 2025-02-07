package compiler.parser;

import compiler.reaction.Reaction;
import compiler.substances.*;
import java.util.*;
import java.util.regex.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * La classe <code>Parser</code> permet d'analyser un fichier contenant des réactions chimiques
 * et d'en extraire les informations sous forme de listes de réactifs et de produits.
 */
public class Parser {
    private static final String reactionPattern = "\\s*(\\d*[A-Za-z0-9]+\\d*)(\\s*\\+\\s*\\d*[A-Za-z0-9]+\\d*)*\\s*->\\s*(\\d*[A-Za-z0-9]+\\d*)(\\s*\\+\\s*\\d*[A-Za-z0-9]+\\d*)*\\s*";
    private static final String reactantsPattern = "\\s*(\\d*[A-Za-z0-9]+\\d*)(\\s*\\+\\s*\\d*[A-Za-z0-9]+\\d*)*\\s*->";
    private static final String productsPattern = "->\\s*(\\d*[A-Za-z0-9]+\\d*)(\\s*\\+\\s*\\d*[A-Za-z0-9]+\\d*)*\\s*";
    
    private String filePath;

    /**
     * Constructeur du parser.
     *
     * @param filePath Chemin du fichier contenant les réactions chimiques.
     */
    public Parser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Lit le contenu du fichier spécifié.
     *
     * @return Une chaîne de caractères contenant le contenu du fichier.
     */
    public String readRulesFile() {
        
        StringBuilder content = new StringBuilder();
        
        try {
            File file = new File(this.filePath);
            Scanner fileContentReader = new Scanner(file);
            
            while (fileContentReader.hasNextLine()) {
                content.append(fileContentReader.nextLine());
            }
            fileContentReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return content.toString();
    }
    
    /**
     * Analyse une chaîne contenant des règles de réactions chimiques et les extrait sous forme de liste.
     *
     * @param rules Chaîne contenant les réactions chimiques.
     * @return Une liste de chaînes représentant les différentes réactions chimiques.
     */
    public List<String> parseReactions(String rules) {
        Pattern pattern = Pattern.compile(reactionPattern);
        Matcher matcher = pattern.matcher(rules);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            System.out.println("Reaction en cours de parsing : " + matcher.group());
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * Extrait la liste des réactifs d'une réaction chimique.
     *
     * @param reaction Chaîne contenant la réaction chimique.
     * @return Une liste de chaînes représentant les réactifs.
     */
    public List<String> parseReactants(String reaction) {
        Pattern pattern = Pattern.compile(reactantsPattern);
        Matcher matcher = pattern.matcher(reaction);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * Extrait la liste des produits d'une réaction chimique.
     *
     * @param reaction Chaîne contenant la réaction chimique.
     * @return Une liste de chaînes représentant les produits.
     */
    public List<String> parseProducts(String reaction) {
        Pattern pattern = Pattern.compile(productsPattern);
        Matcher matcher = pattern.matcher(reaction);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }
}