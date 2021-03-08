package fr.eni.enchere.javaproject.bll;

import java.util.List;

import fr.eni.enchere.javaproject.bo.Categorie;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.CategorieDAO;



public class CategorieManager {

	private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		
		this.categorieDAO = DAOFactory.getCategorieDAO();
		
	}
	
	public void insertCategorie(int noCategorie, String libelle) {
		Categorie categorie = new Categorie();
		categorie.setNoCategorie(noCategorie);
		categorie.setLibelle(libelle);
		this.categorieDAO.insertCategorie(categorie);
	}
	
	public List<Categorie> selectAllCat() {
		return categorieDAO.selectAllCat();
	}
	
	public void deleteCategorie(int noCategorie) {
		this.categorieDAO.deleteCategorie(noCategorie);
	}
	
	public void deleteCategorie(String libelle) {
		this.categorieDAO.deleteCategorie(libelle);
	}
}