package projetHopital.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import projetHopital.Dao.Context;
import projetHopital.Dao.DaoCompte;
import projetHopital.Dao.DaoPatient;
import projetHopital.Dao.DaoVisite;
import projetHopital.model.Bureau;
import projetHopital.model.Compte;
import projetHopital.model.Medecin;
import projetHopital.model.TypeCompte;

public class App {
	public static void main(String[] args) {

		DaoVisite daoVisite = Context.getDaoVisite();
		DaoCompte daoCompte = Context.getDaoCompte();
		DaoPatient daoPatient = Context.getDaoPatient();

		List<Compte> comptes = new LinkedList<>();

		comptes = daoCompte.findAll();

		Scanner sc = new Scanner(System.in);
		int choix = 0;
		while (choix != 5) {
			System.out.println("Veuillez entrer votre id :");
			Integer id = sc.nextInt();
			while (!verifId(comptes, id)) {
				System.out.println("ID invalide");
				id = sc.nextInt();
			}

			sc = new Scanner(System.in);
			System.out.println("Veuillez entrer votre mot de passe :");
			String mdp = sc.nextLine();
			while (!verifMdp(comptes, mdp, id)) {
				System.out.println("Mot de passe invalide");
				mdp = sc.nextLine();
			}

			if (daoCompte.findByKey(id).getTypeCompte().equals("M")) {
				System.out.println("Choisissez votre salle : (S1 ou S2)");
				String salle = sc.nextLine();
				Bureau bureau = null;
				if (salle.equals("S1")) {
					bureau = bureau.S1;
				} else if (salle.equals("S2")) {
					bureau = bureau.S2;
				}
				Medecin medecin = daoCompte.findMedecinByKey(id);
				medecin.setBureau(bureau);

				choix=0;
				while (choix != 4 && choix != 5) {
					System.out.println(
							"Que voulez vous faire ? \n (1) Rendre salle disponible \n (2) Visualiser la liste d'attente \n (3) Sauvegarder les visites "
									+ "\n (4) Se déconnecter \n (5) Quitter l'application");
					choix = sc.nextInt();
					if (choix == 1) {
						medecin.salleDispo();
					} else if (choix == 2) {
						medecin.visualiserFile();
					} else if (choix == 3) {
						medecin.sauvegardeVisite();
					} else {
						choix=5;
					};
				}
				System.out.println("Déconnecté !");
			}
		}
	}

	static boolean verifId(List<Compte> compte, Integer id) {
		for (int i = 0; i < compte.size(); i++) {
			if (compte.get(i).getCompte_id().equals(id)) {
				return true;
			}
		}
		return false;
	}

	static boolean verifMdp(List<Compte> compte, String mdp, Integer id) {
		for (int i = 0; i < compte.size(); i++) {
			if (compte.get(i).getCompte_id().equals(id)) {
				if (compte.get(i).getPassword().equals(mdp)) {
					return true;
				}
			}
		}
		return false;
	}

}
