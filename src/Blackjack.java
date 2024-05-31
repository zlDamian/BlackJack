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
        

        
        while (!ende && spieler.guthaben > 0) {
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
            System.out.println("\n――――――――――――――――――――――――――");
            System.out.println("Deine Karten werden ausgeteilt...");
            pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Deine erste Karte: " + spielerKarte1.rank + spielerKarte1.suit);

            Deck.Card spielerKarte2 = deck.zieheKarte();
            pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Deine zweite Karte: " + spielerKarte2.rank + spielerKarte2.suit);

            Deck.Card dealerKarte1 = deck.zieheKarte();
            System.out.println("――――――――――――――――――――――――――");
            System.out.println("Die Karten des Dealers werden ausgeteilt");
            pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Dealer Karte: " + dealerKarte1.rank + dealerKarte1.suit);

            Deck.Card dealerKarte2 = deck.zieheKarte();
            pausieren(1000); // Pause für 1 Sekunde
            System.out.println("Die zweite Karte des Dealers bleibt verdeckt.");

            int spielerTotal = Deck.kartenWert(spielerKarte1, 0) + Deck.kartenWert(spielerKarte2, Deck.kartenWert(spielerKarte1, 0));
            int dealerTotal = Deck.kartenWert(dealerKarte1, 0) + Deck.kartenWert(dealerKarte2, Deck.kartenWert(dealerKarte1, 0));

            System.out.println("――――――――――――――――――――――――――");
            System.out.println("\n\nDeine Karten: " + spielerKarte1.rank + spielerKarte1.suit + ", " + spielerKarte2.rank + spielerKarte2.suit);
            System.out.println("Dein Gesamtwert: " + spielerTotal);
            System.out.println("\nDealer sichtbare Karte: " + dealerKarte1.rank + dealerKarte1.suit);
            System.out.println("\n\n――――――――――――――――――――――――――");


            
            
            boolean spielerAmZug = true;
            boolean verdoppelt = false;

            while (spielerAmZug) {
                if (spielerTotal == 21) {
                    System.out.println("\nBLACKJACK!");
                    spielerAmZug = false;
                } else {
                    System.out.println("\nWas möchstest du machen? (s)tehen, (k)arte, (v)erdoppeln, (a)ufgeben");
                    String antwort = sc.next();
                    
                 	

                    
                    if (antwort.equalsIgnoreCase("k") && !verdoppelt) {
                        Deck.Card neueKarte = deck.zieheKarte();
                        spielerTotal += Deck.kartenWert(neueKarte, spielerTotal);
                        System.out.println("Eine Karte wird für dich ausgeteilt...");
                        pausieren(1000); // Pause für 1 Sekunde
                        System.out.println("Deine neue Karte: " + neueKarte.rank + neueKarte.suit);
                        pausieren(1000); // Pause für 1 Sekunde
                        System.out.println("Dein neuer Gesamtwert: " + spielerTotal);
                        System.out.println("――――――――――――――――――――――――――");

                        if (spielerTotal > 21) {
                            System.out.println("\nDu hast den Wert 21 überschritten. Du hast verloren.");
                            spieler.Verloren();
                            spielerAmZug = false;
                        }             
                        
                	

                    } else if (antwort.equalsIgnoreCase("v") && !verdoppelt) {
                        if (spieler.guthaben >= spieler.einsatz * 2) {
                            spieler.einsatz *= 2;
                            System.out.println("Dein Einsatz wurde verdoppelt: €" + spieler.einsatz);
                            Deck.Card neueKarte = deck.zieheKarte();
                            spielerTotal += Deck.kartenWert(neueKarte, spielerTotal);
                            System.out.println("Eine Karte wird für dich ausgeteilt...");
                            pausieren(1000); // Pause für 1 Sekunde
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
                    } else if (antwort.equalsIgnoreCase("a")) {
                        System.out.println("Du hast Aufgegeben. Die Hälfte deines Einsatzes wird zurückerstattet.");
                        spieler.Aufgeben();
                        rundeBeenden(spieler, dealerTotal, spielerTotal, sc);
                        
                    } else if (antwort.equalsIgnoreCase("s")) {
                        spielerAmZug = false;
                        
                    } else {
                        System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
                    }
                }
            }


            
            System.out.println("\nDealer Karten: " + dealerKarte1.rank + dealerKarte1.suit + ", " + dealerKarte2.rank + dealerKarte2.suit);
            System.out.println("Dealer Gesamtwert: " + dealerTotal);
            System.out.println("――――――――――――――――――――――――――");

            while (dealerTotal < 17) {
                Deck.Card neueKarte = deck.zieheKarte();
                dealerTotal += Deck.kartenWert(neueKarte, dealerTotal);
                pausieren(2000); // Pause für 2 Sekunde
                System.out.println("\nDealer zieht eine neue Karte: " + neueKarte.rank + neueKarte.suit);
                System.out.println("Neuer Dealer Gesamtwert: " + dealerTotal);
                System.out.println("――――――――――――――――――――――――――");
            }

            if (dealerTotal >= 17) {
            	System.out.println("\nDein Gesamtwert: " + spielerTotal + " Punkte.");
                System.out.println("Dealer steht bei " + dealerTotal + " Punkten.");
            }

            if (spielerTotal == 21) {
                if (dealerTotal == 21) {
                    System.out.println("\nUnentschieden! Beide haben Blackjack.");
                } else {
                    System.out.println("\nGlückwunsch! Du hast mit Blackjack gewonnen!");
                    spieler.BlackJack();
                }
            } else {
                if (dealerTotal > 21) {
                    System.out.println("\nDealer hat den Wert 21 überschritten. Du hast gewonnen!");
                    spieler.Gewonnen();
                } else if (dealerTotal == spielerTotal) {
                    System.out.println("\nUnentschieden! Beide haben den gleichen Gesamtwert.");
                    spieler.Unentschieden();
                } else if (dealerTotal > spielerTotal) {
                    System.out.println("\nDealer hat einen höheren Gesamtwert. Du hast verloren.");
                    spieler.Verloren();
                } else {
                    System.out.println("\nDu hast einen höheren Gesamtwert. Du hast gewonnen!");
                    spieler.Gewonnen();
                }
            }

            rundeBeenden(spieler, dealerTotal, spielerTotal, sc);   
        }
      
        sc.close();
    }
    

    
    public static void rundeBeenden(Spieler spieler, int dealerTotal, int spielerTotal, Scanner sc) {
    	
        boolean ende = false;

        pausieren(2000); // Pause für 2 Sekunden
        System.out.println("Dein aktuelles Guthaben beträgt: €" + spieler.guthaben);

        if (spieler.guthaben <= 0) {
            System.out.println("\n――――――――――――――――――――――――――");
            System.out.println("Du hast kein Guthaben mehr. Das Spiel ist beendet.");
            ende = true;
        } else {
            if (dealerTotal == 21 && spielerTotal == 21) {
                System.out.println("\n――――――――――――――――――――――――――");
                System.out.println("Unentschieden! Beide haben Blackjack.");
                System.out.println("Dein Einsatz wird zurückerstattet.");
                spieler.guthaben += spieler.einsatz;
            }

            System.out.println("\nMöchtest du noch eine Runde spielen? (j/n)");
            String antwort = sc.next();

            if (antwort.equalsIgnoreCase("j")) {
                // Neues Spiel starten
            } else {
                ende = true;
            }
        }

        if (ende) {
            System.out.println("――――――――――――――――――――――――――");
            System.out.println("Vielen Dank fürs Spielen. Auf Wiedersehen!");
            System.out.println("――――――――――――――――――――――――――");
        }
    }
    

    
    public static void pausieren(long millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (InterruptedException ignored) {
            // InterruptedException ignorieren
        }
    }
}