package org.acme;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;



public class Compilateur {

    private List<String> methodNames;

    public Compilateur() {
        this.methodNames = new ArrayList<>();
    }


    public void genererMethode(String nomFichier,BufferedWriter writer) {
        try {
            String nomMethode = nomFichier.substring(nomFichier.lastIndexOf("/") + 1, nomFichier.lastIndexOf("."));
            methodNames.add(nomMethode);
            String contenu = Files.readString(Paths.get(nomFichier));
            String[] lignes = contenu.split("\\r?\\n");

            StringBuilder code = new StringBuilder();
            code.append("return ");
            for (String ligne : lignes) {
                if (!ligne.isEmpty()) {
                    ligne = ligne.trim();
                    if (ligne.equals("droite")) {
                        code.append("+1");
                    } else if (ligne.equals("gauche")) {
                        code.append("-1");
                    } else {
                        System.out.println("Instruction non reconnue : " + ligne);
                    }
                }
            }
            code.append(";");
            ajouterMethode(nomMethode, code.toString(),writer);
            System.out.println("Méthode " + nomMethode + " générée avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajouterMethode(String nomMethode, String contenu, BufferedWriter writer) {
        try {
            writer.newLine();
            writer.write("    public static Integer " + nomMethode + "() { ");
            writer.write(contenu);
            writer.write(" }");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Compilateur compilateur = new Compilateur();
        BufferedWriter writer = new BufferedWriter(new FileWriter("GeneratedMethods.java", true));
        compilateur.ajouterSignature(writer);
        compilateur.genererMethode("/home/ubuntu/Documents/S8/test/Compilateur/code-with-quarkus/src/main/java/org/acme/droite.txt",writer);
        compilateur.genererMethode("/home/ubuntu/Documents/S8/test/Compilateur/code-with-quarkus/src/main/java/org/acme/gauche.txt",writer);
        compilateur.genererMethode("/home/ubuntu/Documents/S8/test/Compilateur/code-with-quarkus/src/main/java/org/acme/test.txt",writer);
        compilateur.ajouterMethodeCall(writer);


    }

    private void ajouterSignature(BufferedWriter writer) {
        try  {
            writer.write("public class GeneratedMethods {");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ajouterMethodeCall(BufferedWriter writer) {
        try {
            writer.newLine();
            writer.write("    public static Integer call(String fichier) {");
            writer.newLine();
            writer.write("        switch (fichier) {");
            writer.newLine();

            for (String methodName : methodNames) {
                writer.write("            case \"" + methodName + ".txt\":");
                writer.newLine();
                writer.write("                " + methodName + "();");
                writer.newLine();
            }

            // Fin du switch case
            writer.write("            default:");
            writer.newLine();
            writer.write("                return 0;");
            writer.newLine();
            writer.write("        }");
            writer.newLine();
            writer.write("    }");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
