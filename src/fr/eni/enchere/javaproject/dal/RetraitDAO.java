package fr.eni.enchere.javaproject.dal;

import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.utils.BusinessException;


public interface RetraitDAO {
	
	public void InsertRetrait(Retrait AjoutRetrait);
	public void updateRetrait(Retrait MajRetrait);
	public void deleteRetrait(int noArticle);
	
	public Retrait insert(Retrait retrait) throws BusinessException;

}
