package fr.esiee;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {

        String urlLocal = "jdbc:mariadb://localhost:3306/EasyTrain";
        String userLocal = "root";
        String pwdLocal = "";

        String urlDistant = "jdbc:mariadb://mysql-namias.alwaysdata.net/namias_jdbc";
        String userDistant = "namias_muser";
        String pwdDistant = "testJDBC";

        try {
            // Creation connexion a la BDD
        Connection connection = DriverManager.getConnection(urlLocal,userLocal,pwdLocal);
        System.out.println("Connection OK");

        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }

        try {
            // Creation connexion a la BDD
            Connection connection = DriverManager.getConnection(urlDistant,userDistant,pwdDistant);
            System.out.println("Connection OK");

        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }

    }
}
