import java.util.Scanner;

public class Blackjack {


    public static void main(String[] args) {
        Spieler spieler = new Spieler(); // Spielerobjekt erstellen
        Deck deck = new Deck(); // Spielerobjekt erstellen
        
        Scanner sc = new Scanner(System.in);
        boolean ende = false;
        int total = 0;
        
        // Willkommens Nachricht
        
        System.out.println("Willkommen zu unserem Game!");
        System.out.println("――――――――――――――――――――――――――");
        System.out.println("  ♥♦ Black Jack ♣♠");
        System.out.println("――――――――――――――――――――――――――");

        System.out.print("Gib deinen Spielernamen ein: ");
        spieler.name = sc.nextLine();
     
        System.out.println("\nWillkommen " + spieler.name + ", dein aktuelles Guthaben beträgt: €" + spieler.guthaben);
        System.out.println("Los gehts...");
        
        // Spiel beginnt
        
        while (!ende) {
            System.out.println("\nGib deinen Einsatz ein:");
            spieler.einsatz = sc.nextInt();
            
            while (spieler.einsatz < 1 || spieler.einsatz > spieler.guthaben) {
                System.out.println("Dein Einsatz ist ungültig, gib ihn nochmal ein");
                spieler.einsatz = sc.nextInt();
            }
            
            // Hier können Sie den Code für das Mischen und Aus teilen der Karten einfügen
            // Ich füge beispielsweise eine Methode hinzu, um das Deck zu erstellen + mischen
            deck.erstelleDeck();
            deck.mischeDeck();
            // Hier können Sie weitere Spiellogik einfügen
            
            // Spiel beenden
            ende = true;
        }
        
        sc.close(); // Scanner schließen, um Ressourcen freizugeben
    }
}


