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
           //Pause.pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Deine erste Karte: " + spielerKarte1.rank + spielerKarte1.suit);

            Deck.Card spielerKarte2 = deck.zieheKarte();
            //Pause.pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Deine zweite Karte: " + spielerKarte2.rank + spielerKarte2.suit);

            Deck.Card dealerKarte1 = deck.zieheKarte();
            System.out.println("\nDie Karten des Dealers werden ausgeteilt");
            //Pause.pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Dealer Karte: " + dealerKarte1.rank + dealerKarte1.suit);

            Deck.Card dealerKarte2 = deck.zieheKarte();
            //Pause.pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Die zweite Karte des Dealers bleibt verdeckt.");

            int spielerTotal = Deck.kartenWert(spielerKarte1, 0) + Deck.kartenWert(spielerKarte2, Deck.kartenWert(spielerKarte1, 0));
            int dealerTotal = Deck.kartenWert(dealerKarte1, 0) + Deck.kartenWert(dealerKarte2, Deck.kartenWert(dealerKarte1, 0));

            System.out.println("\nDeine Karten: " + spielerKarte1.rank + spielerKarte1.suit + ", " + spielerKarte2.rank + spielerKarte2.suit);
            System.out.println("Dein Gesamtwert: " + spielerTotal);
            System.out.println("\nDealer sichtbare Karte: " + dealerKarte1.rank + dealerKarte1.suit);

            
            
            
            boolean spielerAmZug = true;
            boolean verdoppelt = false;

            while (spielerAmZug) {
                System.out.println("\nMöchtest du noch eine Karte ziehen, verdoppeln oder aufhören? (z/v/a)");
                String antwort = sc.next();

                if (antwort.equalsIgnoreCase("z")) {
                    Deck.Card neueKarte = deck.zieheKarte();
                    spielerTotal += Deck.kartenWert(neueKarte, spielerTotal);
                    System.out.println("Deine neue Karte: " + neueKarte.rank + neueKarte.suit);
                    System.out.println("Dein neuer Gesamtwert: " + spielerTotal);
                    
                    if (spielerTotal > 21) {
                    	
                        System.out.println("\nDu hast den Wert 21 überschritten. Du hast verloren.");
                        spieler.Verloren();
                        spielerAmZug = false;
                    } else if (spielerTotal == 21) {
                        System.out.println("\nDu hast Blackjack erreicht!");
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
            
            if (spielerTotal == 21) {
                System.out.println("\nDealer Karten: " + dealerKarte1.rank + dealerKarte1.suit + ", " + dealerKarte2.rank + dealerKarte2.suit);
                System.out.println("Dealer Gesamtwert: " + dealerTotal);
                
                if (dealerTotal == 21) {
                    System.out.println("\nUnentschieden! Beide haben Blackjack.");
                } else {
                    System.out.println("\nGlückwunsch! Du hast mit Blackjack gewonnen!");
                    spieler.BlackJack();
                }
            } else if (spielerTotal <= 21) {
                System.out.println("\nDealer Karten: " + dealerKarte1.rank + dealerKarte1.suit + ", " + dealerKarte2.rank + dealerKarte2.suit);
                System.out.println("Dealer Gesamtwert: " + dealerTotal);
                
                while (dealerTotal < 17) {
                    Deck.Card neueKarte = deck.zieheKarte();
                    dealerTotal += Deck.kartenWert(neueKarte, dealerTotal);
                    System.out.println("\nDealer zieht eine neue Karte: " + neueKarte.rank + neueKarte.suit);
                    System.out.println("Neuer Dealer Gesamtwert: " + dealerTotal);
                }
                
                if (dealerTotal > 21) {
                    System.out.println("\nDealer hat den Wert 21 überschritten. Du hast gewonnen!");
                    spieler.Gewonnen();
                } else if (dealerTotal == spielerTotal) {
                    System.out.println("\nUnentschieden! Beide haben den gleichen Gesamtwert.");
                } else if (dealerTotal > spielerTotal) {
                    System.out.println("\nDealer hat einen höheren Gesamtwert. Du hast verloren.");
                    spieler.Verloren();
                } else {
                    System.out.println("\nDu hast einen höheren Gesamtwert. Du hast gewonnen!");
                    spieler.Gewonnen();
                }
            }

            Pause.pausieren(2000); // Pause für 2 Sekunden
            System.out.println("Dein aktuelles Guthaben beträgt: €" + spieler.guthaben);

            if (spieler.guthaben <= 0) {
                System.out.println("Du hast kein Guthaben mehr. Das Spiel ist beendet.");
                ende = true;
            } else {
                if (dealerTotal == 21 && spielerTotal == 21) {
                    System.out.println("\nUnentschieden! Beide haben Blackjack.");
                    System.out.println("Dein Einsatz wird zurückerstattet.");
                    spieler.guthaben += spieler.einsatz;
                }
                
                System.out.println("\nMöchtest du noch eine Runde spielen? (j/n)");
                String antwort = sc.next();
                
                if (antwort.equalsIgnoreCase("j")) {
                    // Neues Spiel starten
                    continue;
                } else {
                    ende = true;
                }
            }

            if (ende) {
                System.out.println("Vielen Dank fürs Spielen. Auf Wiedersehen!");
                break;
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
}