import java.util.ArrayList;
import java.util.Collections;

class Deck {

	public ArrayList<Karte> deck; // Initialisierung des Decks

	// Methode zum Erstellen des Kartendecks
	public void erstelleDeck() {

		deck = new ArrayList<>();
		String[] farbe = { "♠", "♥", "♦", "♣" };
		String[] wert = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "D", "K", "A" };

		for (String suit : farbe) {
			for (String rank : wert) {
				deck.add(new Karte(rank, suit));
			}
		}
	}

	// Methode zum Mischen des Kartendecks
	public void mischeDeck() {
		Collections.shuffle(deck);
	}

	public Karte zieheKarte() {
		if (!deck.isEmpty()) {
			return deck.remove(deck.size() - 1); // Zieht die oberste Karte vom Deck
		}
		return null; // Gibt null zurück, falls das Deck leer ist
	}

	public static int kartenWert(Deck.Karte karte, int spielerTotal) {
		// Überprüfung der Karte nach Bube, Dame, König wenn ja gib wert 10
		if (karte.rank.equals("B") || karte.rank.equals("D") || karte.rank.equals("K")) {
			return 10;
			// Überprüft und Teilt das Ass dem Wert zu wenn es über 21 kommt dann gib es 1 und wenn nicht dann 11
		} else if (karte.rank.equals("A")) {
			if (spielerTotal + 11 <= 21) {
				return 11;
			} else {
				return 1;
			}
		} else {
			// Wandelt den String der Karte in einen Int um und teilt diesen dem Wert auch zu
			return Integer.parseInt(karte.rank);
		}
	}

	// Werte der Karte werden hinterlegt für das Deck

	class Karte {
		String rank;
		String suit;

		Karte(String rank, String suit) {
			this.rank = rank;
			this.suit = suit;
		}
	}
}
