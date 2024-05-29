import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Spieler spieler = new Spieler();
        Scanner sc = new Scanner(System.in);
        boolean ende = false;
        
        System.out.println("Willkommen zu unserem Game!");
        System.out.println("――――――――――――――――――――――――――");
        System.out.println("  ♥♦ Black Jack ♣♠");
        System.out.println("――――――――――――――――――――――――――");

        System.out.print("Gib deinen Spielernamen ein: ");
        spieler.name = sc.nextLine();
     
        System.out.println("\nWillkommen " + spieler.name + ", dein aktuelles Guthaben beträgt: €" + spieler.guthaben);
        System.out.println("Los gehts...");
        
        while (!ende) {
            Deck deck = new Deck();
            deck.erstelleDeck();
            deck.mischeDeck();
            //Test
            System.out.println("\nGib deinen Einsatz ein:");
            spieler.einsatz = sc.nextInt();
            
            while (spieler.einsatz < 1 || spieler.einsatz > spieler.guthaben) {
                System.out.println("\nDein Einsatz ist ungültig, gib ihn nochmal ein");
                spieler.einsatz = sc.nextInt();
            }
            
            Deck.Card spielerKarte1 = deck.zieheKarte();
            Deck.Card spielerKarte2 = deck.zieheKarte();
            Deck.Card dealerKarte1 = deck.zieheKarte();
            Deck.Card dealerKarte2 = deck.zieheKarte();
            
            int spielerTotal = kartenWert(spielerKarte1) + kartenWert(spielerKarte2);
            int dealerTotal = kartenWert(dealerKarte1) + kartenWert(dealerKarte2);
            
            System.out.println("\nDeine Karten: " + spielerKarte1.rank + spielerKarte1.suit + ", " + spielerKarte2.rank + spielerKarte2.suit);
            System.out.println("Dein Gesamtwert: " + spielerTotal);
            System.out.println("\nDealer Karte: " + dealerKarte1.rank + dealerKarte1.suit);
            
            boolean spielerAmZug = true;
            boolean verdoppelt = false;
            
            while (spielerAmZug) {
                System.out.println("\nMöchtest du noch eine Karte ziehen, verdoppeln oder aufhören? (z/v/a)");
                String antwort = sc.next();
                
                if (antwort.equalsIgnoreCase("z")) {
                    Deck.Card neueKarte = deck.zieheKarte();
                    spielerTotal += kartenWert(neueKarte);
                    System.out.println("Deine neue Karte: " + neueKarte.rank + neueKarte.suit);
                    System.out.println("Dein neuer Gesamtwert: " + spielerTotal);
                    
                    if (spielerTotal > 21) {
                        System.out.println("\nDu hast den Wert 21 überschritten. Du hast verloren.");
                        spieler.Verloren();
                        spielerAmZug = false;
                    }
                } else if (antwort.equalsIgnoreCase("v")) {
                    if (!verdoppelt) {
                        if (spieler.guthaben >= spieler.einsatz * 2) {
                            spieler.einsatz *= 2;
                            System.out.println("Dein Einsatz wurde verdoppelt: €" + spieler.einsatz);
                            Deck.Card neueKarte = deck.zieheKarte();
                            spielerTotal += kartenWert(neueKarte);
                            System.out.println("Deine neue Karte: " + neueKarte.rank + neueKarte.suit);
                            System.out.println("Dein neuer Gesamtwert: " + spielerTotal);
                            verdoppelt = true;
                            
                            if (spielerTotal > 21) {
                                System.out.println("Du hast den Wert 21 überschritten. Du hast verloren.");
                                System.out.println("");
                                spieler.Verloren();
                                spielerAmZug = false;
                            } else {
                                spielerAmZug = false;
                            }
                        } else {
                            System.out.println("Du hast nicht genügend Guthaben zum Verdoppeln.");
                        }
                    } else {
                        System.out.println("Du hast bereits verdoppelt.");
                    }
                } else {
                    spielerAmZug = false;
                }
            }
            
            if (spielerTotal <= 21) {
                System.out.println("\nDealer Karten: " + dealerKarte1.rank + dealerKarte1.suit + ", " + dealerKarte2.rank + dealerKarte2.suit);
                System.out.println("Dealer Gesamtwert: " + dealerTotal);
                
                while (dealerTotal < 17) {
                    Deck.Card neueKarte = deck.zieheKarte();
                    dealerTotal += kartenWert(neueKarte);
                    System.out.println("\nDealer zieht eine neue Karte: " + neueKarte.rank + neueKarte.suit);
                    System.out.println("Neuer Dealer Gesamtwert: " + dealerTotal);
                }
                
                if (dealerTotal > 21) {
                    System.out.println("\nDer Dealer hat den Wert 21 überschritten. Du hast gewonnen!");
                    spieler.Gewonnen();
                } else if (spielerTotal > dealerTotal) {
                    System.out.println("\nDu hast den Dealer geschlagen. Du hast gewonnen!");
                    spieler.Gewonnen();
                } else if (spielerTotal < dealerTotal) {
                    System.out.println("\nDer Dealer hat dich geschlagen. Du hast verloren.");
                    spieler.Verloren();
                } else {
                    System.out.println("\nEs ist ein Unentschieden.");
                }
            }
            
            System.out.println("Dein aktuelles Guthaben beträgt: €" + spieler.guthaben);
            
            if (spieler.guthaben <= 0) {
                System.out.println("Du hast kein Guthaben mehr. Das Spiel ist beendet.");
                ende = true;
            } else {
                System.out.println("\nMöchtest du noch eine Runde spielen? (j/n)");
                String antwort = sc.next();
                
                if (!antwort.equalsIgnoreCase("j")) {
                    ende = true;
                }
            }
        }
        
        System.out.println("Danke fürs Spielen. Bis zum nächsten Mal!");
        sc.close();
    }
    
    public static int kartenWert(Deck.Card karte) {
        if (karte.rank.equals("B") || karte.rank.equals("D") || karte.rank.equals("K")) {
            return 10;
        } else if (karte.rank.equals("A")) {
            return 11;
        } else {
            return Integer.parseInt(karte.rank);
        }
    }
}