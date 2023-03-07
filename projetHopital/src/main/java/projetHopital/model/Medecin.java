package projetHopital.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import cdTheque.CD;
import projetHopital.Dao.Closer;
import projetHopital.Dao.Context;
import projetHopital.Dao.DaoVisiteImpl;

public class Medecin extends Compte{
	
	private Bureau bureau;
	private List<Visite> visites;

	public Medecin(Integer compte_id, String login, String password, Bureau bureau) {
		super(compte_id, login, password, TypeCompte.M);
		this.bureau=bureau;
	}
	
	public Medecin (Integer compte_id, String login, String password) {
		super(compte_id, login, password);
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
	
	//rendre salle dispo
	public void salleDispo() {
		Patient nextPatient=null;
		nextPatient = fileAttente.get(0);
		fileAttente.remove(0);
		Visite nextVisite=new Visite(nextPatient.getPatient_id(), getCompte_id(), getBureau(), LocalDate.now());
		visites.add(nextVisite);
	}
	
	//visualiser la liste d'attente
	public List<Patient> visualiserFile (){
		System.out.println("Votre liste d'attente actuelle : ");
		for (Patient p : fileAttente) {
			System.out.println(p.getPatient_id() + " "+ p.getNom() +" "+ p.getPrenom());
		}
		Patient nextPatient=null;
		nextPatient = fileAttente.get(0);
		System.out.println("Votre prochain patient est : "+nextPatient.getPatient_id()+" "+ nextPatient.getNom()+" "+nextPatient.getPrenom());
		return fileAttente;
	}
	
	//sauvegarde des visites
	public void sauvegardeVisite () {
		for (Visite v : visites) {
			PreparedStatement ps = null;
			Connection connection = null;
			try {
				connection = Context.getContext().getConnection();
				ps = connection.prepareStatement(
						"insert into visite(patient_id,medecin_id,visite_cout,visite_salle,visite_date) values(?,?,20,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, v.getPatient_id());
				ps.setInt(2, v.getMedecin_id());
				ps.setString(3, v.getSalle().toString()); 
				ps.setDate(4, Date.valueOf(v.getDate()));
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					v.setVisite_id(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Closer.close(ps);
				Context.destroy();
			}
			visites=null;
		}
		
	}
	
}
