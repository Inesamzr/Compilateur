public class GeneratedMethods {
    public static Integer droite() { return +1; }
    public static Integer gauche() { return -1; }
    public static Integer test() { return -1+1; }
    public static Integer call(String fichier) {
        switch (fichier) {
            case "droite.txt":
                droite();
            case "gauche.txt":
                gauche();
            case "test.txt":
                test();
            default:
                return 0;
        }
    }
