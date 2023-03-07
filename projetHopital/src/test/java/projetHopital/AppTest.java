package projetHopital;

import java.time.LocalDate;
import java.util.Date;

import projetHopital.Dao.Context;
import projetHopital.Dao.DaoCompte;
import projetHopital.Dao.DaoPatient;
import projetHopital.Dao.DaoVisite;
import projetHopital.model.Bureau;
import projetHopital.model.Medecin;
import projetHopital.model.Patient;
import projetHopital.model.Secretaire;
import projetHopital.model.Visite;

public class AppTest {
	public static void main(String[] args) {
		DaoVisite daoVisite = Context.getDaoVisite();
		DaoCompte daoCompte = Context.getDaoCompte();
		DaoPatient daoPatient = Context.getDaoPatient();
		
		Secretaire secretaireTest = new Secretaire("LoginS", "PasswordS");
		Medecin medecinTest = new Medecin("LoginM", "PasswordM",Bureau.S1);
		Medecin medecinTest2 = new Medecin("LoginM2", "Password2",Bureau.S2);
		Patient patientTest = new Patient("NomTest", "PrenomTest");
		
		System.out.println(daoCompte.findAllMedecin());
		
		Visite visiteTest = new Visite(3, 2, 20, medecinTest.getBureau(), LocalDate.now());
		
		
		
		
//		System.out.println(patientTest.getPatient_id());
//		
//		System.out.println(daoPatient.findByKey(3).getPatient_id());
		
//		daoCompte.insert(medecinTest2);
//		daoCompte.insert(secretaireTest);
//		daoCompte.insert(medecinTest);
//		daoVisite.insert(visiteTest);
//		daoPatient.insert(patientTest);
		
//		daoPatient.insert(patientTest);
//		Visite visiteTest = new Visite(null, null, 0, null, null)
	}	
}
