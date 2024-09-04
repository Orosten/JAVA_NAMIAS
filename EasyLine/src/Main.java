//NAMIAS Gabriel
//04/09/2024

public class Main {
    public static void main(String[] args) {
        Voyage djakarta = new Voyage();

        djakarta.setDestination("djakarta");
        djakarta.setDuree(8);
        djakarta.setPrix(1799.99);

        System.out.println("on part a " + djakarta.getDestination() + " cela coutera : " + djakarta.getPrix() + " euros et durera " + djakarta.getDuree() + " jours.");

    }
}