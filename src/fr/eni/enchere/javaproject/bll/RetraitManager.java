package fr.eni.enchere.javaproject.bll;

import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO RetraitDAO;
	
	public RetraitManager() {
		
		this.RetraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public void InsertRetrait(int noArticle, String Rue, String CodePostal, String Ville ) {
		Retrait retrait = new Retrait();
		retrait.setNoArticle(noArticle);
		retrait.setRue(Rue);
		retrait.setCodePostal(CodePostal);
		retrait.setVille(Ville);
		this.RetraitDAO.InsertRetrait(retrait);
	}
		
	public void updateRetrait(Retrait MajRetrait) {
		this.RetraitDAO.updateRetrait(MajRetrait);
	}
	
	public void deleteRetrait(int noArticle) {
		this.RetraitDAO.deleteRetrait(noArticle);
	}
}
