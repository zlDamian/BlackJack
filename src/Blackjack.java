import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Spieler spieler = new Spieler();
		Scanner sc = new Scanner(System.in);
		boolean beenden = false;

		// Start des Spiels
		System.out.println("Willkommen zu unserem Game!");
		System.out.println("――――――――――――――――――――――――――");
		System.out.println("  ♥♦ Black Jack ♣♠");
		System.out.println("――――――――――――――――――――――――――");

		// Abfrage nach Namen
		System.out.print("Gib deinen Spielernamen ein: ");
		spieler.name = sc.nextLine();

		// Spiel Menü
		while (!beenden) {
			System.out.println("――――――――――――――――――――――――――");
			System.out.println("Menü:");
			System.out.println("1. Guthaben abfragen");
			System.out.println("2. Geld aufladen");
			System.out.println("3. Spielen");
			System.out.println("4. Spiel verlassen");
			System.out.println("Wähle eine Option: ");
			System.out.println("――――――――――――――――――――――――――");
			int option = sc.nextInt();

			// Auswählmöglichkeiten
			switch (option) {
			case 1:
				// Ausgabe des aktuellen Guthabens
				System.out.println("\nDein aktuelles Guthaben beträgt: €" + spieler.guthaben);
				break;
			case 2:
				// Aufladen des Guthabens
				System.out.print("\nGib den Betrag ein, den du aufladen möchtest: €");
				int betrag = sc.nextInt();
				if (betrag >= 0) {
					spieler.guthaben += betrag;
					System.out.println("Dein Guthaben wurde um €" + betrag + " aufgeladen.");
				} else {
					System.out.println("\nUngültiger Betrag.");
				}
				break;
			case 3:
				// Spiel starten
				spielen(spieler, sc);
				break;
			case 4:
				// Spiel beenden
				beenden = true;
				break;
			default:
				System.out.println("Ungültige Option. Bitte versuche es erneut.");
				break;
			}
		}

		// Abschied
		sc.close();
		System.out.println("――――――――――――――――――――――――――");
		System.out.println("Vielen Dank fürs Spielen. Auf Wiedersehen!");
		System.out.println("――――――――――――――――――――――――――");
	}

	public static void spielen(Spieler spieler, Scanner sc) {
		boolean ende = false;

		// Willkommensnachricht
		System.out.println("\nWillkommen " + spieler.name + ", dein aktuelles Guthaben beträgt: €" + spieler.guthaben);
		System.out.println("Los gehts...");

		while (!ende && spieler.guthaben > 0) {
			// Deck erstellen und mischen
			Deck deck = new Deck();
			deck.erstelleDeck();
			deck.mischeDeck();

			// Abfrage und Überprüfung des Einsatzes
			System.out.println("\nGib deinen Einsatz ein:");
			spieler.einsatz = sc.nextInt();
			spieler.checkEinsatz(sc);

			// Verteilung der Karten
			Deck.Karte spielerKarte1 = deck.zieheKarte();
			System.out.println("\n――――――――――――――――――――――――――");
			System.out.println("Deine Karten werden ausgeteilt...");
			pausieren(1000); // Pause für 1 Sekunde
			System.out.println("Deine erste Karte: " + spielerKarte1.rank + spielerKarte1.suit);

			Deck.Karte spielerKarte2 = deck.zieheKarte();
			pausieren(1000); // Pause für 1 Sekunde
			System.out.println("Deine zweite Karte: " + spielerKarte2.rank + spielerKarte2.suit);

			Deck.Karte dealerKarte1 = deck.zieheKarte();
			System.out.println("――――――――――――――――――――――――――");
			System.out.println("Die Karten des Dealers werden ausgeteilt");
			pausieren(1000); // Pause für 1 Sekunde
			System.out.println("Dealer Karte: " + dealerKarte1.rank + dealerKarte1.suit);

			Deck.Karte dealerKarte2 = deck.zieheKarte();
			pausieren(1000); // Pause für 1 Sekunde
			System.out.println("Die zweite Karte des Dealers bleibt verdeckt.");

			// Gesamtwert der Karten
			int spielerTotal = Deck.kartenWert(spielerKarte1, 0)
					+ Deck.kartenWert(spielerKarte2, Deck.kartenWert(spielerKarte1, 0));
			int dealerTotal = Deck.kartenWert(dealerKarte1, 0)
					+ Deck.kartenWert(dealerKarte2, Deck.kartenWert(dealerKarte1, 0));

			// Übersicht der Karten
			System.out.println("――――――――――――――――――――――――――");
			System.out.println("\n\nDeine Karten: " + spielerKarte1.rank + spielerKarte1.suit + ", "
					+ spielerKarte2.rank + spielerKarte2.suit);
			System.out.println("Dein Gesamtwert: " + spielerTotal);
			System.out.println("\nDealer sichtbare Karte: " + dealerKarte1.rank + dealerKarte1.suit);
			System.out.println("\n\n――――――――――――――――――――――――――");

			boolean spielerAmZug = true;
			boolean verdoppelt = false;
			boolean spielerAufgegeben = false;

			// Prüfung auf Black jack und ausgabe falls Blackjack + Abfrage des nächsten
			// Schrittes
			if (spielerTotal == 21) {
				System.out.println("\nBLACKJACK!");
				spielerAmZug = false;
				
			} else {
				while (spielerAmZug) {
					System.out.println("\nWas möchstest du machen? (s)tehen, (k)arte, (v)erdoppeln, (a)ufgeben");
					String antwort = sc.next();

					// Gibt dir eine neue Karte und Überprüft das du nicht Verdoppelt hast
					if (antwort.equalsIgnoreCase("k") && !verdoppelt) {
						
						Deck.Karte neueKarte = deck.zieheKarte();
						spielerTotal += Deck.kartenWert(neueKarte, spielerTotal);
						System.out.println("Eine Karte wird für dich ausgeteilt...");
						pausieren(1000); // Pause für 1 Sekunde
						System.out.println("Deine neue Karte: " + neueKarte.rank + neueKarte.suit);
						pausieren(1000); // Pause für 1 Sekunde
						System.out.println("Dein neuer Gesamtwert: " + spielerTotal);
						System.out.println("――――――――――――――――――――――――――");

						// Prüft nach dem ziehen das du nicht über 21 bist und wenn ja gibt es die
						// Nachricht aus
						if (spielerTotal > 21) {
							System.out.println("\nDu hast den Wert 21 überschritten. Du hast verloren.");
							spieler.Verloren();
							spielerAmZug = false;
						}
						// Verdoppelt: Einsatz wird 2x und du bekommst eine neue Karte
					} else if (antwort.equalsIgnoreCase("v") && !verdoppelt) {
						
						if (spieler.guthaben >= spieler.einsatz * 2) {
							spieler.einsatz *= 2;
							System.out.println("Dein Einsatz wurde verdoppelt: €" + spieler.einsatz);
							Deck.Karte neueKarte = deck.zieheKarte();
							spielerTotal += Deck.kartenWert(neueKarte, spielerTotal);
							System.out.println("Eine Karte wird für dich ausgeteilt...");
							pausieren(1000); // Pause für 1 Sekunde
							System.out.println("Deine neue Karte: " + neueKarte.rank + neueKarte.suit);
							System.out.println("Dein neuer Gesamtwert: " + spielerTotal);
							verdoppelt = true;

							// Prüft nach dem Verdoppeln das du nicht über 21 bist und wenn ja gibt es die
							// Nachricht aus
							if (spielerTotal > 21) {
								System.out.println("\nDu hast den Wert 21 überschritten. Du hast verloren.");
								spieler.Verloren();
								spielerAmZug = false;
							}
						} else {
							System.out.println("Du hast nicht genügend Guthaben zum Verdoppeln.");
						}
						// Aufgeben: Du gibst auf
					} else if (antwort.equalsIgnoreCase("a")) {
						
						System.out.println("Du hast Aufgegeben. Die Hälfte deines Einsatzes wird zurückerstattet.");
						spieler.Aufgeben();
						spielerAmZug = false; // Spieler gibt auf und beendet den Zug
						spielerAufgegeben = true;
						
						// Stehen: Setzt den wert von spielerAmZug auf false und verlässt somit die Schleife					
					} else if (antwort.equalsIgnoreCase("s")) {
						spielerAmZug = false;
					} else {
						System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
					}
				}
			}

			// Überprüfen, ob der Spieler bereits verloren hat
			if (spielerTotal > 21) {
				if (rundeBeenden(spieler, dealerTotal, spielerTotal, sc)) {
					return; // Methode verlassen, um ins Hauptmenü zurückzukehren
				}
				continue; // Neue Runde starten
			}

			// Aufdecken der Dealer Karten
			System.out.println("\nDealer Karten: " + dealerKarte1.rank + dealerKarte1.suit + ", " + dealerKarte2.rank
					+ dealerKarte2.suit);
			System.out.println("Dealer Gesamtwert: " + dealerTotal);
			System.out.println("――――――――――――――――――――――――――");

			// Wenn der Dealer unter 17 ist, ziehe noch eine Karte
			while (dealerTotal < 17) {
				Deck.Karte neueKarte = deck.zieheKarte();
				dealerTotal += Deck.kartenWert(neueKarte, dealerTotal);
				pausieren(2000); // Pause für 2 Sekunde
				System.out.println("\nDealer zieht eine neue Karte: " + neueKarte.rank + neueKarte.suit);
				System.out.println("Neuer Dealer Gesamtwert: " + dealerTotal);
				System.out.println("――――――――――――――――――――――――――");
			}

			// Gibt deinen Wert aus und die des Dealers
			if (dealerTotal >= 17) {
				System.out.println("\nDein Gesamtwert: " + spielerTotal + " Punkte.");
				System.out.println("Dealer steht bei " + dealerTotal + " Punkten.");
			}

			// Mögliche Ausgänge des Spiels und deren Ausgabe 
			if (!spielerAufgegeben) {
				if (spielerTotal == 21 && dealerTotal != 21) {
					System.out.println("\nGlückwunsch! Du hast mit Blackjack gewonnen!");
					spieler.BlackJack();
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
			}

			// Aufruf von rundeBeenden() am Ende der Methode spielen()
			if (rundeBeenden(spieler, dealerTotal, spielerTotal, sc)) {
				return; // Methode verlassen, um ins Hauptmenü zurückzukehren
			}
		}
	}

	public static boolean rundeBeenden(Spieler spieler, int dealerTotal, int spielerTotal, Scanner sc) {
		// Wert des aktuellen Guthabens + weiterspielen falls noch genug Geld
		pausieren(2000); // Pause für 2 Sekunden
		System.out.println("Dein aktuelles Guthaben beträgt: €" + spieler.guthaben);

		if (spieler.guthaben <= 0) {
			System.out.println("\n――――――――――――――――――――――――――");
			System.out.println("Du hast kein Guthaben mehr. Das Spiel ist beendet.");
			return true; // Signalisiert, dass das Spiel beendet ist
		} else {
			
			System.out.println("\nMöchtest du noch eine Runde spielen? (j/n)");
			String antwort = sc.next();
			if (antwort.equalsIgnoreCase("n")) {
				System.out.println("Zurück zum Hauptmenü...");
				return true; // Signalisiert, dass ins Hauptmenü zurückgekehrt werden soll
			}
		}
		return false; // Direkt eine neue Runde starten
	}

	public static void pausieren(long millisekunden) {
		try {
			Thread.sleep(millisekunden);
		} catch (InterruptedException ignored) {
			// InterruptedException ignorieren
		}
	}
}