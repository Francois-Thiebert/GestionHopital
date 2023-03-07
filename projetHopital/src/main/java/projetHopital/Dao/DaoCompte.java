package projetHopital.Dao;

import java.util.List;

import projetHopital.model.Compte;
import projetHopital.model.Medecin;
import projetHopital.model.Secretaire;

public interface DaoCompte extends DaoGeneric <Compte,Integer>{
	List<Secretaire> findAllSecretaire();
	List<Medecin> findAllMedecin();
	public Secretaire findSecretaireByKey(Integer key);
	public Medecin findMedecinByKey(Integer key);
}
