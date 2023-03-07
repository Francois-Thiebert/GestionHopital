package projetHopital.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Compte {
	private Integer compte_id;
	private String login;
	private String password;
	private TypeCompte typeCompte;
	protected List<Patient> fileAttente= new LinkedList<>(); 
	
	public Compte(Integer compte_id, String login, String password, TypeCompte typeCompte) {
		this.compte_id = compte_id;
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
	}
	
	public Compte(Integer compte_id, String login, String password) {
		this.compte_id = compte_id;
		this.login = login;
		this.password = password;
	}

	public Compte(String login, String password, TypeCompte typeCompte) {
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
	}

	public Integer getCompte_id() {
		return compte_id;
	}

	public void setCompte_id(Integer compte_id) {
		this.compte_id = compte_id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TypeCompte getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(TypeCompte typeCompte) {
		this.typeCompte = typeCompte;
	}

	@Override
	public int hashCode() {
		return Objects.hash(compte_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(compte_id, other.compte_id);
	}
	
}
