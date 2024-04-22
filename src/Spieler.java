
public class Spieler {
	
	String name;
	int guthaben = 100;
	int einsatz = 0;
	
	
	public void BlackJack(){
		
	}
	
	public void Gewonnen(){
		guthaben += einsatz;
	}
	
	public void Verloren(){
		guthaben -= einsatz; 
	}

}
