package projetHopital.model;

import java.util.Objects;

public class Patient {
	private Integer patient_id;
	private String nom;
	private String prenom;
	
	public Patient(Integer patient_id, String nom, String prenom) {
		this.patient_id = patient_id;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Patient(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(patient_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(patient_id, other.patient_id);
	}
	
}
