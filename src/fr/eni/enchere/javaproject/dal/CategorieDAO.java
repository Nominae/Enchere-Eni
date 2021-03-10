package fr.eni.enchere.javaproject.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Categorie;
import fr.eni.enchere.javaproject.utils.BusinessException;

public interface CategorieDAO {

	public void insertCategorie(Categorie categorie) throws DALException;
	List<Categorie> selectAllcat() throws DALException;
	public void deleteCategorie(int noCategorie) throws DALException;
	public void deleteCategorie(String libelle) throws DALException;
	public Categorie selectByIdCat(int noCategorie) throws DALException;
	
	//10/03
	public void update(Categorie categorie) throws DALException ;
	

	
		

}