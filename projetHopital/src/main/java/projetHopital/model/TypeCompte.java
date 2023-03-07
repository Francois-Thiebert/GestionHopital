package projetHopital.model;

public enum TypeCompte {
	S("Secretaire"),M("Medecin");
	
	private String type;

	private TypeCompte(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
