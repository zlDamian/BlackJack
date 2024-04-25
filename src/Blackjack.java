import java.util.Scanner;
import java.util.Random;

public class Blackjack {

	public static void main(String[] args) {
		
//Spieler Klasse importieren
		Spieler spieler;
		spieler = new Spieler();
		
		Scanner sc = new Scanner(System.in);
		boolean ende = false;
//Willkommens Nachricht	
		System.out.println("Willkommen zu unserem Game!");
		System.out.println(
	                "\n――――――――――――――――――――――――――\n  " +
	                "♥♦ Black Jack ♣♠" +
	                "\n――――――――――――――――――――――――――\n");
		System.out.println("TEST");
		
		System.out.print("Gib deinen Spielernamen ein: ");
		spieler.name = sc.nextLine();
		
		System.out.println("\nWillkommen " + spieler.name + " dein aktuelles Guthaben beträgt: €" + spieler.guthaben);
		System.out.println("Los gehts...");	
		
//Prüfe ob nochmal gespielt wird
		while (ende == false) {
			System.out.println("\nGib deinen Einsatz ein:");
			spieler.einsatz = sc.nextInt();
			
//Prüfe das Einsatz zwischen 1 und Guthaben ist
			 while(spieler.einsatz < 1 || spieler.einsatz > spieler.guthaben) {
				 
					System.out.println("Dein Einsatz ist ungültig, gib ihn nochmal ein");
					spieler.einsatz = sc.nextInt();
			 }
			 
			 
			 
//Random Test
		Random karten = new Random();  	
		String[] Zehner = new String[] { "10", "Jack", "Queen", "King" };
		System.out.println("Test");
		
			 
			 
			 
			 
			 
		}
	                
	                
	                
	                
	                
	                
	                
	                
	}

}
