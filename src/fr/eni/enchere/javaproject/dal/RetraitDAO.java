package fr.eni.enchere.javaproject.dal;

import fr.eni.enchere.javaproject.bo.Retrait;

public interface RetraitDAO {
	
	public void InsertRetrait(Retrait AjoutRetrait);
	public void updateRetrait(Retrait MajRetrait);
	public void deleteRetrait(int noArticle);
}
