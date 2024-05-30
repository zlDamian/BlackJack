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
            
            System.out.println("\nGib deinen Einsatz ein:");
            spieler.einsatz = sc.nextInt();
            
            while (spieler.einsatz < 1 || spieler.einsatz > spieler.guthaben) {
                System.out.println("\nDein Einsatz ist ungültig, gib ihn nochmal ein");
                spieler.einsatz = sc.nextInt();
            }
            
            Deck.Card spielerKarte1 = deck.zieheKarte();
            System.out.println("\nDeine Karten werden ausgeteilt...");
            Pause.pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Deine erste Karte: " + spielerKarte1.rank + spielerKarte1.suit);

            Deck.Card spielerKarte2 = deck.zieheKarte();
            Pause.pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Deine zweite Karte: " + spielerKarte2.rank + spielerKarte2.suit);

            Deck.Card dealerKarte1 = deck.zieheKarte();
            System.out.println("\nDie Karten des Dealers werden ausgeteilt");
            Pause.pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Dealer Karte: " + dealerKarte1.rank + dealerKarte1.suit);

            Deck.Card dealerKarte2 = deck.zieheKarte();
            Pause.pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Die zweite Karte des Dealers bleibt verdeckt.");

            int spielerTotal = Deck.kartenWert(spielerKarte1, 0) + Deck.kartenWert(spielerKarte2, Deck.kartenWert(spielerKarte1, 0));
            int dealerTotal = Deck.kartenWert(dealerKarte1, 0) + Deck.kartenWert(dealerKarte2, Deck.kartenWert(dealerKarte1, 0));

            System.out.println("\nDeine Karten: " + spielerKarte1.rank + spielerKarte1.suit + ", " + spielerKarte2.rank + spielerKarte2.suit);
            System.out.println("Dein Gesamtwert: " + spielerTotal);
            System.out.println("\nDealer sichtbare Karte: " + dealerKarte1.rank + dealerKarte1.suit);

            
            if (spielerTotal == 21) {
                System.out.println("\nGlückwunsch! Du hast Blackjack und gewinnst das Spiel!");
                spieler.BlackJack();
                Pause.pausieren(2000); // Pause für 2 Sekunden
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
                
                continue; // Springe zur nächsten Iteration der while-Schleife
            }
            
            boolean spielerAmZug = true;
            boolean verdoppelt = false;
            
            while (spielerAmZug) {
                System.out.println("\nMöchtest du noch eine Karte (z)iehen, (v)erdoppeln oder (a)ufhören?");
                String antwort = sc.next();
                
                if (antwort.equalsIgnoreCase("z")) {
                	Deck.Card neueKarte = deck.zieheKarte();
                	spielerTotal += Deck.kartenWert(neueKarte, spielerTotal);
                	System.out.println("Eine Karte wird für dich ausgeteilt...");
                	Pause.pausieren(1000); // Pause für 1 Sekunde
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
                            spielerTotal += Deck.kartenWert(neueKarte, spielerTotal);
                        	System.out.println("Eine Karte wird für dich ausgeteilt...");
                        	Pause.pausieren(1000); // Pause für 1 Sekunde
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
                if (spielerTotal == 21) {
                    System.out.println("Glückwunsch! Du hast Blackjack erreicht und erhältst 1,5-fachen Gewinn!");
                    spieler.BlackJack();
                }
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
    public class Pause {
        public static void pausieren(int millisekunden) {
            try {
                Thread.sleep(millisekunden);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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