import java.util.ArrayList;
import java.util.Collections;

class Deck {

	public static ArrayList<Card> deck; // Initialisierung des Decks
	
    // Methode zum Erstellen des Kartendecks
    public void erstelleDeck() {
    	
    	deck = new ArrayList<>();
        String[] typ = {"♠", "♥", "♦", "♣"};
        String[] wert = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "D", "K", "A"};
        
        for (String suit : typ) {
            for (String rank : wert) {
                deck.add(new Card(rank, suit));
            }
        }
    }
    
    // Methode zum Mischen des Kartendecks
    public void mischeDeck() {
        Collections.shuffle(deck);
    }
    
    public void gebeKarte() {

    }
    
    class Card {
        String rank;
        String suit;
        
        Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }
    }
}

