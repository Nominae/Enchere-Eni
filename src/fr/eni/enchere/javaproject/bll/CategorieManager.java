package fr.eni.enchere.javaproject.bll;

import java.util.List;

import fr.eni.enchere.javaproject.bo.Categorie;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.CategorieDAO;
import fr.eni.enchere.javaproject.dal.DALException;




public class CategorieManager {

	private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		
		this.categorieDAO = DAOFactory.getCategorieDAO();
		
	}
	
	public void insertCategorie(int noCategorie, String libelle) throws DALException {
		Categorie categorie = new Categorie();
		categorie.setNoCategorie(noCategorie);
		categorie.setLibelle(libelle);
		this.categorieDAO.insertCategorie(categorie);
	}
	
	public List<Categorie> selectAllCat() throws DALException {
		return categorieDAO.selectAllcat();
	}
	
	public void deleteCategorie(int noCategorie) throws DALException {
		this.categorieDAO.deleteCategorie(noCategorie);
	}
	
	public void deleteCategorie(String libelle) throws DALException {
		this.categorieDAO.deleteCategorie(libelle);
	}
}