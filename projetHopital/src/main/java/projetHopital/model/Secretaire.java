package projetHopital.model;

public class Secretaire extends Compte{

	public Secretaire(Integer compte_id, String login, String password) {
		super(compte_id, login, password, TypeCompte.S);
		// TODO Auto-generated constructor stub
	}
	
	public Secretaire(String login, String password) {
		super(login, password, TypeCompte.S);
		// TODO Auto-generated constructor stub
	}
	
	

}
