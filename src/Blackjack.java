import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Blackjack {

    private static ArrayList<Card> deck; // Deklaration des Decks als statische Variable, damit darauf in der main-Methode zugegriffen werden kann

    public static void main(String[] args) {
        deck = new ArrayList<>(); // Initialisierung des Decks

        Spieler spieler = new Spieler(); // Spielerobjekt erstellen

        Scanner sc = new Scanner(System.in);
        boolean ende = false;

        System.out.println("Willkommen zu unserem Game!");
        System.out.println("――――――――――――――――――――――――――");
        System.out.println("  ♥♦ Black Jack ♣♠");
        System.out.println("――――――――――――――――――――――――――");
        System.out.println(deck);
        System.out.print("Gib deinen Spielernamen ein: ");
        spieler.name = sc.nextLine();
        
        System.out.println("\nWillkommen " + spieler.name + ", dein aktuelles Guthaben beträgt: €" + spieler.guthaben);
        System.out.println("Los gehts...");
        
        while (!ende) {
            System.out.println("\nGib deinen Einsatz ein:");
            spieler.einsatz = sc.nextInt();
            
            while (spieler.einsatz < 1 || spieler.einsatz > spieler.guthaben) {
                System.out.println("Dein Einsatz ist ungültig, gib ihn nochmal ein");
                spieler.einsatz = sc.nextInt();
            }
            
            // Hier können Sie den Code für das Mischen und Austeilen der Karten einfügen
            // Ich füge beispielsweise eine Methode hinzu, um das Deck zu erstellen
            erstelleDeck();
            mischeDeck();
            System.out.println(deck);
            // Hier können Sie weitere Spiellogik einfügen
            
            // Beispiel: Spiel beenden
            ende = true;
        }
        
        sc.close(); // Scanner schließen, um Ressourcen freizugeben
    }

    // Methode zum Erstellen des Kartendecks
    private static void erstelleDeck() {
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "D", "K", "A"};
        
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
    }
    // Methode zum Mischen des Kartendecks
    private static void mischeDeck() {
        Collections.shuffle(deck);
    }
}

class Card {
    String rank;
    String suit;
    
    Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    public String toString() {
        return rank + suit; // Kartenrang und Symbol kombinieren
    }
}

class Spieler {
    String name;
    int guthaben = 100;
    int einsatz = 0;
    
    public void BlackJack(){
        
    }
    
    public void Gewonnen(){
        guthaben += einsatz;
    }
    
    public void Verloren(){
        guthaben -= einsatz; 
    }
}
