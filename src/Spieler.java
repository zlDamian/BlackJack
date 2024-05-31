import java.util.Scanner;

public class Spieler {
	
	String name;
	int guthaben = 100;
	int einsatz;
	
	
	public void BlackJack(){
		guthaben += einsatz * 1.5;
	}
	
	public void Gewonnen(){
		guthaben += einsatz;
	}
	
	public void Verloren(){
		guthaben -= einsatz; 
	}
	
	public void Unentschieden(){
		
	}
	
	public void Aufgeben(){
		guthaben = einsatz / 2;
	}
    
	public void checkEinsatz(Scanner sc){
	while (einsatz < 1 || einsatz > guthaben) {
        System.out.println("\nDein Einsatz ist ungültig, gib ihn nochmal ein");
        einsatz = sc.nextInt();
    }
	}
	
	
}
