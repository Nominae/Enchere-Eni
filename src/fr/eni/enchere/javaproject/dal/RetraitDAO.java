package fr.eni.enchere.javaproject.dal;

import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.utils.BusinessException;


public interface RetraitDAO {
	
	public Retrait InsertRetrait(Retrait AjoutRetrait) throws DALException;
	public void updateRetrait(Retrait MajRetrait) throws DALException;
	public void deleteRetrait(int noArticle);
	
	public Retrait insert(Retrait retrait) throws BusinessException;
	public Retrait selectRetraitById(int noArticle) throws DALException;
	void deleteRetrait(Retrait DeleteRetrait) throws DALException;


}
