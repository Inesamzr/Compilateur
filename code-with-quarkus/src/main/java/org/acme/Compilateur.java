package org.acme;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Compilateur {

    public void genererMethode(String nomFichier) {
        try {
            String contenu = Files.readString(Paths.get(nomFichier));
            String[] lignes = contenu.split("\\r?\\n");
            //public statis integer call(string action)

            StringBuilder code = new StringBuilder();
            for (String ligne : lignes) {
                if (!ligne.isEmpty()) {
                    ligne = ligne.trim();
                    if (ligne.equals("droite")) {
                        ajouterMethodeDroite();
                    } else if (ligne.equals("gauche")) {
                        ajouterMethodeGauche();
                    } else {
                        System.out.println("Instruction non reconnue : " + ligne);
                    }
                }
            }

            System.out.println("Fichiers de méthodes générés avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajouterMethodeDroite() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("GeneratedMethods.java", true))) {
            writer.newLine();
            writer.write("    public static void droite() {");
            writer.newLine();
            writer.write("        x++;");
            writer.newLine();
            writer.write("    }");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajouterMethodeGauche() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("GeneratedMethods.java", true))) {
            writer.newLine();
            writer.write("    public static void gauche() {");
            writer.newLine();
            writer.write("        x--;");
            writer.newLine();
            writer.write("    }");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Compilateur compilateur = new Compilateur();
        compilateur.genererMethode("/home/ubuntu/Documents/S8/test/Compilateur/code-with-quarkus/src/main/java/org/acme/droite.txt");
        compilateur.genererMethode("/home/ubuntu/Documents/S8/test/Compilateur/code-with-quarkus/src/main/java/org/acme/gauche.txt");
    }
}
