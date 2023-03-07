package projetHopital.model;

public enum Bureau {
	S1("Salle 1"),S2("Salle 2");
	
	private String salle;

	private Bureau(String salle) {
		this.salle = salle;
	}

	public String getSalle() {
		return salle;
	}
}
