import java.util.Scanner;

public class Blackjack {
	
    public static void main(String[] args) {
        Spieler spieler = new Spieler(); // Spielerobjekt erstellen
        Deck deck = new Deck(); // Spielerobjekt erstellen
        
        Scanner sc = new Scanner(System.in);
        boolean ende = false;
        
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
            Deck.Card spielerKarte1 = deck.zieheKarte();
            Deck.Card spielerKarte2 = deck.zieheKarte();
            Deck.Card dealerKarte1 = deck.zieheKarte();
            Deck.Card dealerKarte2 = deck.zieheKarte();    
            
            int spielerTotal = Deck.kartenWert(spielerKarte1) + Deck.kartenWert(spielerKarte2);
            int dealerTotal = Deck.kartenWert(dealerKarte1) + Deck.kartenWert(dealerKarte2);
            
            System.out.println("Deine Karten: " + spielerKarte1.rank + spielerKarte1.suit + ", " + spielerKarte2.rank + spielerKarte2.suit);
            System.out.println("Dealer Karte: " + dealerKarte1.rank + dealerKarte1.suit);
            
            boolean spielerAmZug = true;
            
 
        }
        
        System.out.println("Danke fürs Spielen. Bis zum nächsten Mal!");
        sc.close();
    }

}


