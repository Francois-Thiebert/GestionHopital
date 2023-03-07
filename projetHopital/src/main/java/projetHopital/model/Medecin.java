package projetHopital.model;

public class Medecin extends Compte{
	
	private Bureau bureau;

	public Medecin(Integer compte_id, String login, String password, Bureau bureau) {
		super(compte_id, login, password, TypeCompte.M);
		this.bureau=bureau;
	}
	
	public Medecin(String login, String password, Bureau bureau) {
		super(login, password, TypeCompte.M);
		this.bureau=bureau;
	}

	public Bureau getBureau() {
		return bureau;
	}

	public void setBureau(Bureau bureau) {
		this.bureau = bureau;
	}
	
}
