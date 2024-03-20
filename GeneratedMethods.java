
    public static void droite() {
        x++;
    }
    public static void gauche() {
        x--;
    }
    public static void droite() {
        x++;
    }
    public static void gauche() {
        x--;
    }
    public static void call(String fichier) {
        switch (fichier) {
            case "droite.txt":
                droite();
                break;            case "gauche.txt":
                gauche();
                break;
            default:
                System.out.println("Fichier non reconnu : " + fichier);
        }
    }
    public static void droite() {
        x++;
    }
    public static void gauche() {
        x--;
    }
    public static void call(String fichier) {
        switch (fichier) {
            case "droite.txt":
                droite();
                break;            case "gauche.txt":
                gauche();
                break;
            default:
                System.out.println("Fichier non reconnu : " + fichier);
        }
    }

    }

    public static void droite() {
        x++;
    }

    public static void gauche() {
        x--;
    }

}