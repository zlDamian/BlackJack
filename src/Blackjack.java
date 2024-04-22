import java.util.Scanner;
import java.util.Random;

public class Blackjack {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean nochmal = true;
		int guthaben = 100;
		
		System.out.println("Willkommen zu unserem!");
		System.out.println(
	                "\n――――――――――――――――――――――――――\n  " +
	                "♥♦ Black Jack ♣♠" +
	                "\n――――――――――――――――――――――――――\n");
		System.out.print("Gib deinen Namen ein: ");
		String player = new String(sc.nextLine());
		
		System.out.println();
		System.out.println("Willkommen " + player + "! Dein aktuelles Guthaben beträgt: €" + guthaben);
		System.out.println("\nLos gehts...");
		System.out.println();	
		
		
		while (nochmal == true) {
			
			int einsatz = 0;
			System.out.println("Gib deinen Einsatz ein:");
			einsatz = sc.nextInt();
			
			 while(einsatz < 1 || einsatz > guthaben) {
				 
					System.out.println("Dein Einsatz ist ungültig, gib es nochmal ein");
					einsatz = sc.nextInt();
			 }
			
			
			
			
			Random karte = new Random();
			
		}
	                
	                
	                
	                
	                
	                
	                
	                
	}

}
