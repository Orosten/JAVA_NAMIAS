package fr.esiee;

import fr.esiee.dao.EasyTrainDAO;
import fr.esiee.modele.Arret;
import fr.esiee.modele.Role;
import fr.esiee.modele.Trajet;
import fr.esiee.modele.Utilisateur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class testDAO {
    public static void main(String[] args) {
        EasyTrainDAO dao = new EasyTrainDAO();

        // Test de connexion
        System.out.println("==== Test de Connexion ====");
        dao.testConnection();

        // Test ajout d'utilisateur
        System.out.println("\n==== Test ajout d'utilisateur ====");
        Utilisateur utilisateur = new Utilisateur(1, "jsnow", "passwordtest", "John", "Snow", LocalDate.now(), Role.EMPLOYE);
        if (dao.ajouterUtilisateur(utilisateur)) {
            System.out.println("Utilisateur ajouté avec ID : " + utilisateur.getId());
        } else {
            System.out.println("Échec de l'ajout de l'utilisateur.");
        }

        // Test récupération utilisateur par ID
        System.out.println("\n==== Test récupération utilisateur par ID ====");
        Utilisateur utilisateurParId = dao.getUtilisateurById(utilisateur.getId());
        if (utilisateurParId != null) {
            System.out.println("Utilisateur trouvé : " + utilisateurParId.getLogin());
        } else {
            System.out.println("Aucun utilisateur trouvé avec cet ID.");
        }

        // Test récupération de tous les utilisateurs
        System.out.println("\n==== Test récupération de tous les utilisateurs ====");
        List<Utilisateur> utilisateurs = dao.getAllUtilisateurs();
        System.out.println("Nombre d'utilisateurs trouvés : " + utilisateurs.size());

        // Test ajout d'arrêt
        System.out.println("\n==== Test ajout d'arrêt ====");
        Arret arret = new Arret(0, "TestArret");
        if (dao.ajouterArret(arret)) {
            System.out.println("Arrêt ajouté avec ID : " + arret.getId());
        } else {
            System.out.println("Échec de l'ajout de l'arrêt.");
        }

        // Test récupération arrêt par ID
        System.out.println("\n==== Test récupération arrêt par ID ====");
        Arret arretParId = dao.getArretById(arret.getId());
        if (arretParId != null) {
            System.out.println("Arrêt trouvé : " + arretParId.getNom());
        } else {
            System.out.println("Aucun arrêt trouvé avec cet ID.");
        }

        // Test récupération de tous les arrêts
        System.out.println("\n==== Test récupération de tous les arrêts ====");
        List<Arret> arrets = dao.getAllArrets();
        System.out.println("Nombre d'arrêts trouvés : " + arrets.size());

        // Test ajout de trajet
        System.out.println("\n==== Test ajout de trajet ====");
        Trajet trajet = new Trajet(LocalDateTime.now(), "TR123", LocalDateTime.now().plusHours(2), arret, arret);
        if (dao.ajouterTrajet(trajet)) {
            System.out.println("Trajet ajouté avec le code : " + trajet.getCode());
        } else {
            System.out.println("Échec de l'ajout du trajet.");
        }

        // Test récupération trajet par code
        System.out.println("\n==== Test récupération trajet par code ====");
        Trajet trajetParCode = dao.getTrajetById(trajet.getCode());
        if (trajetParCode != null) {
            System.out.println("Trajet trouvé : " + trajetParCode.getCode());
        } else {
            System.out.println("Aucun trajet trouvé avec ce code.");
        }

        // Test récupération de tous les trajets
        System.out.println("\n==== Test récupération de tous les trajets ====");
        List<Trajet> trajets = dao.getAllTrajets();
        System.out.println("Nombre de trajets trouvés : " + trajets.size());
    }
}
