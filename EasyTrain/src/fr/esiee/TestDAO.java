package fr.esiee;

import fr.esiee.dao.EasyTrainDAO;
import fr.esiee.modele.Utilisateur;

import java.util.Scanner;

public class TestDAO {
    public static void main(String[] args) {
        EasyTrainDAO dao = new EasyTrainDAO();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID de l'utilisateur à rechercher : ");
        int id = scanner.nextInt();

        // Appeler la méthode pour afficher l'utilisateur
        dao.getUtilisateurById(id);

    }
}
