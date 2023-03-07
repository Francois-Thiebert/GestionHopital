package projetHopital.Dao;

import java.util.List;

import projetHopital.model.Visite;

public interface DaoVisite extends DaoGeneric <Visite,Integer>{

	public List<Visite> findPatientByKey(Integer key);
}
