package fr.esiee.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import fr.esiee.modele.Utilisateur;

public class EasyTrainDAO {
    String urlLocal = "jdbc:mariadb://localhost:3306/EasyTrain";
    String userLocal = "root";
    String pwdLocal = "";

    public EasyTrainDAO() {
        try {
            // Création de la connexion à la BDD
            Connection connection = DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
            System.out.println("Connexion ok");

        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion : ");
            e.printStackTrace();
        }
    }

    public void getUtilisateurById(int id) {
        String sql = "SELECT * FROM utilisateur WHERE id = ?"; // Requête SQL

        try (Connection connection = DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id); // Définit l'ID comme paramètre de la requête
            ResultSet rs = ps.executeQuery(); // Exécute la requête SQL

            if (rs.next()) { // Si un utilisateur est trouvé
                System.out.println("Utilisateur trouvé :");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Login : " + rs.getString("login"));
                System.out.println("Nom : " + rs.getString("nom"));
                System.out.println("Prénom : " + rs.getString("prenom"));
                System.out.println("Date d'embauche : " + rs.getTimestamp("date_embauche"));
                System.out.println("Rôle : " + rs.getString("role"));
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID : " + id);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : ");
            e.printStackTrace();
        }
    }
}
