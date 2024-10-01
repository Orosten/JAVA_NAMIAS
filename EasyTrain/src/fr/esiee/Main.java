//NAMIAS Gabriel
//01 10 2024

package fr.esiee;


import fr.esiee.modele.Role;
import java.time.LocalDate;
import fr.esiee.modele.Utilisateur;

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

        System.out.println(prenom + " " + nom + " " +  role);

        Utilisateur test2 = new Utilisateur(2, "sam02", "samsam", "Caswell", "Sam", LocalDate.of(2024, 3, 12), Role.EMPLOYE);

        String prenom2 = test2.getPrenom();
        String nom2 = test2.getNom();
        String role2 = test2.getRole().toString();

        System.out.println(prenom2 + " " + nom2 + " " +  role2);


    }
}