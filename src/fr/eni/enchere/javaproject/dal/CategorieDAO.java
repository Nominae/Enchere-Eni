package fr.eni.enchere.javaproject.dal;

import java.util.List;

import fr.eni.enchere.javaproject.bo.Categorie;

public interface CategorieDAO {

	public void insertCategorie(Categorie categorie);
	public List<Categorie> selectAllCat();
	public void deleteCategorie(int noCategorie);
	public void deleteCategorie(String libelle);
}