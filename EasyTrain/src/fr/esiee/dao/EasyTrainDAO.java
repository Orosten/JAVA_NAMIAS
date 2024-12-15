package fr.esiee.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.esiee.modele.Arret;
import fr.esiee.modele.Role;
import fr.esiee.modele.Trajet;
import fr.esiee.modele.Utilisateur;

public class EasyTrainDAO {
    private Connection getConnection() throws SQLException {
        String urllocal = "jdbc:mariadb://localhost:3306/easytrain";
        String userlocal = "root";
        String pwdlocal = "";
        return DriverManager.getConnection(urllocal, userlocal, pwdlocal);
    }

    public Connection CreateConnection() {
        Connection connection = null;
        try {
            connection = getConnection();
            System.out.println("Connexion réussie à la base de données !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : ");
            e.printStackTrace();
        }
        return connection;
    }

    public void testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Test de connexion : OK !");
            } else {
                System.err.println("Test de connexion : Échec !");
            }
        } catch (Exception e) {
            System.err.println("Erreur inattendue lors du test de connexion :");
            e.printStackTrace();
        }
    }

    public Utilisateur getUtilisateurById(int id) {
        Utilisateur utilisateur = null;
        String query = "SELECT * FROM utilisateur WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    utilisateur = new Utilisateur(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("mdp"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getTimestamp("dateEmbauche").toLocalDateTime().toLocalDate(),
                            Role.valueOf(resultSet.getString("role"))
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'utilisateur avec l'ID : " + id);
            e.printStackTrace();
        }

        return utilisateur;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("mdp"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getTimestamp("dateEmbauche").toLocalDateTime().toLocalDate(),
                        Role.valueOf(resultSet.getString("role"))
                );
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

    public boolean ajouterUtilisateur(Utilisateur u) {
        String query = "INSERT INTO utilisateur (login, mdp, nom, prenom, dateEmbauche, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, u.getLogin());
            statement.setString(2, u.getMdp());
            statement.setString(3, u.getNom());
            statement.setString(4, u.getPrenom());
            statement.setTimestamp(5, Timestamp.valueOf(u.getDateEmbauche().atStartOfDay()));
            statement.setString(6, u.getRole().toString());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        u.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Arret getArretById(int id) {
        Arret arret = null;
        String query = "SELECT * FROM arret WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    arret = new Arret(resultSet.getInt("id"), resultSet.getString("nom"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arret;
    }

    public List<Arret> getAllArrets() {
        List<Arret> arrets = new ArrayList<>();
        String query = "SELECT * FROM arret";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Arret arret = new Arret(resultSet.getInt("id"), resultSet.getString("nom"));
                arrets.add(arret);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrets;
    }

    public boolean ajouterArret(Arret a) {
        String query = "INSERT INTO arret (nom) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, a.getNom());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        a.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Trajet getTrajetById(String code) {
        String query = "SELECT * FROM trajet WHERE code = ?";
        Trajet trajet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String codeTrajet = resultSet.getString("code");
                LocalDateTime tempsDepart = resultSet.getTimestamp("tempsDepart").toLocalDateTime();
                LocalDateTime tempsArrivee = resultSet.getTimestamp("tempsArrivee").toLocalDateTime();
                int arretDepartId = resultSet.getInt("arretDepartId");
                int arretArriveeId = resultSet.getInt("arretArriveeId");
                Arret arretDepart = getArretById(arretDepartId);
                Arret arretArrivee = getArretById(arretArriveeId);
                trajet = new Trajet(tempsDepart, codeTrajet, tempsArrivee, arretDepart, arretArrivee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajet;
    }

    public List<Trajet> getAllTrajets() {
        List<Trajet> trajets = new ArrayList<>();
        String query = "SELECT * FROM trajet";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                LocalDateTime tempsDepart = resultSet.getTimestamp("tempsDepart").toLocalDateTime();
                LocalDateTime tempsArrivee = resultSet.getTimestamp("tempsArrivee").toLocalDateTime();
                int arretDepartId = resultSet.getInt("arretDepartId");
                int arretArriveeId = resultSet.getInt("arretArriveeId");
                Arret arretDepart = getArretById(arretDepartId);
                Arret arretArrivee = getArretById(arretArriveeId);
                trajets.add(new Trajet(tempsDepart, code, tempsArrivee, arretDepart, arretArrivee));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajets;
    }

    public boolean ajouterTrajet(Trajet t) {
        String query = "INSERT INTO trajet (code, tempsDepart, tempsArrivee, arretDepartId, arretArriveeId) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, t.getCode());
            statement.setTimestamp(2, Timestamp.valueOf(t.getTempsDepart()));
            statement.setTimestamp(3, Timestamp.valueOf(t.getTempsArrivee()));
            statement.setInt(4, t.getArretDepart().getId());
            statement.setInt(5, t.getArretArrivee().getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
