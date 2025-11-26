public class HeapLeakPasswordDemo {
    // mot de passe stocké en String (mauvaise pratique)
    private static String badPassword;

    // mot de passe stocké en char[] (meilleure pratique)
    private static char[] goodPassword;

    public static void main(String[] args) throws Exception {
        badPassword = "S3cr3tP@ssw0rd!";        // <- secret en clair dans une String
        goodPassword = "S3cr3tP@ssw0rd!".toCharArray(); // <- secret dans char[]

        System.out.println("Mot de passe initialisé. PID: " + ProcessHandle.current().pid());
        System.out.println("Appuyez sur ENTER pour créer le heap dump...");
        System.in.read(); // attend qu'on appuie sur Entrée

        // Optionnel : "efface" le bon mot de passe après usage
        java.util.Arrays.fill(goodPassword, '\0');

        System.out.println("Mot de passe goodPassword effacé du tableau char[]. Appuyez sur ENTER pour quitter...");
        System.in.read();
    }
}
