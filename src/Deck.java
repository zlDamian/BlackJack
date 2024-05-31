import java.util.ArrayList;
import java.util.Collections;

class Deck {
//	private int spielerTotal;
//	private int dealerTotal;
//	
//	public int getSpielerTotal() {
//		return spielerTotal;
//	}
//	
//	public void setSpielerTotal(int spielerTotal) {
//		this.spielerTotal = spielerTotal;
//	}
//
//	public int getDealerTotal() {
//		return dealerTotal;
//	}
//	
//	public void setDealerTotal(int dealerTotal) {
//		this.dealerTotal = dealerTotal;
//	}
//	
	public ArrayList<Card> deck; // Initialisierung des Decks
	
    // Methode zum Erstellen des Kartendecks
    public void erstelleDeck() {
    	
    	deck = new ArrayList<>();
        String[] farbe = {"♠", "♥", "♦", "♣"};
        String[] wert = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "D", "K", "A"};
        
        for (String suit : farbe) {
            for (String rank : wert) {
                deck.add(new Card(rank, suit));
            }
        }
    }
    
    // Methode zum Mischen des Kartendecks
    public void mischeDeck() {
        Collections.shuffle(deck);
    }
    
    public Card zieheKarte() {
        if (!deck.isEmpty()) {
            return deck.remove(deck.size() - 1); // Zieht die oberste Karte vom Deck
        }
        return null; // Gibt null zurück, falls das Deck leer ist
    }
    
    
    
    
    
    public static int kartenWert(Deck.Card karte, int spielerTotal) {
        if (karte.rank.equals("B") || karte.rank.equals("D") || karte.rank.equals("K")) {
            return 10;
        } else if (karte.rank.equals("A")) {
            if (spielerTotal + 11 <= 21) {
                return 11;
            } else {
                return 1;
            }
        } else {
        	//KP was parseInt ist
            return Integer.parseInt(karte.rank);
        }
    }
    
    //Check ich nicht
    
    class Card {
        String rank;
        String suit;
        
        Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }
    }
}

