package projetHopital.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Visite {
	private Integer visite_id;
	private Integer patient_id;
	private Integer medecin_id;
	private double visite_cout = 20;
	private Bureau salle;
	private Date date;
	
	public Visite(Integer visite_id, Integer patient_id, Integer medecin_id, double visite_cout, Bureau salle,
			Date date) {
		this.visite_id = visite_id;
		this.patient_id = patient_id;
		this.medecin_id = medecin_id;
		this.visite_cout = visite_cout;
		this.salle = salle;
		this.date = date;
	}

	public Visite(Integer patient_id, Integer medecin_id, double visite_cout, Bureau salle, Date date) {
		super();
		this.patient_id = patient_id;
		this.medecin_id = medecin_id;
		this.visite_cout = visite_cout;
		this.salle = salle;
		this.date = date;
	}

	public Integer getVisite_id() {
		return visite_id;
	}

	public void setVisite_id(Integer visite_id) {
		this.visite_id = visite_id;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public Integer getMedecin_id() {
		return medecin_id;
	}

	public void setMedecin_id(Integer medecin_id) {
		this.medecin_id = medecin_id;
	}

	public double getVisite_cout() {
		return visite_cout;
	}

	public void setVisite_cout(double visite_cout) {
		this.visite_cout = visite_cout;
	}

	public Bureau getSalle() {
		return salle;
	}

	public void setSalle(Bureau salle) {
		this.salle = salle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(visite_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visite other = (Visite) obj;
		return Objects.equals(visite_id, other.visite_id);
	}

}
