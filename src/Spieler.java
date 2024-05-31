
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
		guthaben += einsatz / 2;
	}
	

}
