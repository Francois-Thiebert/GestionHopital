package projetHopital.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Secretaire extends Compte implements Serializable {

	public Secretaire(Integer compte_id, String login, String password) {
		super(compte_id, login, password, "S");
		// TODO Auto-generated constructor stub
	}

	public Secretaire(String login, String password) {
		super(login, password, "S");
	}

	public void ajoutPatientFile(Patient patient) {
		fileAttente.add(patient);
	}

	public void afficheFileAttente() {
		fileAttente.forEach(patient -> {
			System.out.println(
					patient.getPatient_id() + ", prenom : " + patient.getPrenom() + ", nom : " + patient.getNom());
		});
	}

	public void partEnPause() {
		try {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\pierr\\OneDrive\\Documents\\Depot_Git\\ProjetJavaHopital\\GestionHopital\\fileAttente");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(fileAttente);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void revientDePause() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					"C:\\Users\\pierr\\OneDrive\\Documents\\Depot_Git\\ProjetJavaHopital\\GestionHopital\\fileAttente"));
			Object obj = ois.readObject();
			fileAttente = (List<Patient>) obj;
			fileAttente.forEach(personne -> System.out.println(personne.getPrenom() + ", " + personne.getNom()));
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
