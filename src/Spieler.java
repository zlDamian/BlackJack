import java.util.Scanner;

// Anlegen der Klasse Spieler
public class Spieler {
    // Name, Guthaben, Einsatz
    String name;
    int guthaben = 100;
    int einsatz;

    // Gewinn BlackJack
    public void BlackJack() {
        guthaben += einsatz * 1.5;
    }

    // Gewinn wird draufgerechnet
    public void Gewonnen() {
        guthaben += einsatz;
    }

    // Gewinn wird abgezogen
    public void Verloren() {
        guthaben -= einsatz;
    }

    public void Unentschieden() {
        // Keine Aktion notwendig bei Unentschieden
    }

    public void Aufgeben() {
        guthaben -= einsatz / 2;
    }

    // Prüft den Einsatz
    public void checkEinsatz(Scanner sc) {
        while (einsatz < 1 || einsatz > guthaben) {
            System.out.println("\nDein Einsatz ist ungültig, gib ihn nochmal ein");
            einsatz = sc.nextInt();
        }
    }
}