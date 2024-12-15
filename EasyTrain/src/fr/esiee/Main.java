//NAMIAS Gabriel
//01 10 2024

package fr.esiee;


import fr.esiee.modele.Arret;
import fr.esiee.modele.Role;
import fr.esiee.modele.Trajet;
import fr.esiee.modele.Utilisateur;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Utilisateur test1 = new Utilisateur();
        test1.setId(1);
        test1.setLogin("Jon01");
        test1.setMdp("jonjon");
        test1.setNom("Snow");
        test1.setPrenom("Jon");
        test1.setDateEmbauche(LocalDate.of(2023, 4, 24));
        test1.setRole(Role.EMPLOYE);

        String prenom = test1.getPrenom();
        String nom = test1.getNom();
        String role = test1.getRole().toString();

        System.out.println(prenom + " " + nom + " " + role);

        Utilisateur test2 = new Utilisateur(2, "sam02", "samsam", "Caswell", "Sam", LocalDate.of(2024, 3, 12), Role.EMPLOYE);

        String prenom2 = test2.getPrenom();
        String nom2 = test2.getNom();
        String role2 = test2.getRole().toString();

        System.out.println(prenom2 + " " + nom2 + " " + role2);

        Arret winterfell = new Arret();
        winterfell.setId(1);
        winterfell.setNom("winterfell");

        Arret castleblack = new Arret(2, "castleblack");

        System.out.println("Arret 1 = " + winterfell.getId() + " " + winterfell.getNom());
        System.out.println("Arret 2 = " + castleblack.getId() + " " + castleblack.getNom());

        LocalDateTime tempsDepart1 = LocalDateTime.of(2024, 10, 2, 9, 0);
        LocalDateTime tempsArrivee1 = LocalDateTime.of(2024, 10, 2, 12, 30);
        LocalDateTime tempsDepart2 = LocalDateTime.of(2024, 10, 2, 18, 0);
        LocalDateTime tempsArrivee2 = LocalDateTime.of(2024, 10, 2, 21, 30);

        Trajet trajet1 = new Trajet(tempsDepart, code, tempsArrivee, arretDepart, arretArrivee);
        trajet1.setCode("001");
        trajet1.setTempsDepart(tempsDepart1);
        trajet1.setTempsArrivee(tempsArrivee1);
        trajet1.setArretDepart(winterfell);
        trajet1.setArretArrivee(castleblack);

        Trajet trajet2 = new Trajet("002", tempsArrivee2, tempsDepart2, castleblack, winterfell);

        System.out.println("Trajet 1 = " + trajet1.getCode() + " " + trajet1.getTempsDepart() + " " + trajet1.getTempsArrivee() + " " + trajet1.getArretDepart().getNom() + " " + trajet1.getArretArrivee().getNom());
        System.out.println("Trajet 2 = " + trajet2.getCode() + " " + trajet2.getTempsDepart() + " " + trajet2.getTempsArrivee() + " " + trajet2.getArretDepart().getNom() + " " + trajet2.getArretArrivee().getNom());
    }
}
