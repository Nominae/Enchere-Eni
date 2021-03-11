package fr.eni.enchere.javaproject.bll;

import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.RetraitDAO;
import fr.eni.enchere.javaproject.utils.BusinessException;
import fr.eni.enchere.javaproject.dal.DALException;


public class RetraitManager {

	private RetraitDAO RetraitDAO;
	
	public RetraitManager() {
		
		this.RetraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public void InsertRetrait(int noArticle, String Rue, String CodePostal, String Ville ) throws DALException {
		Retrait retrait = new Retrait();
		retrait.setNoArticle(noArticle);
		retrait.setRue(Rue);
		retrait.setCodePostal(CodePostal);
		retrait.setVille(Ville);
		this.RetraitDAO.InsertRetrait(retrait);
	}
		
	public void updateRetrait(Retrait MajRetrait)  throws DALException {
		this.RetraitDAO.updateRetrait(MajRetrait);
	}
	
	public void deleteRetrait(int noArticle) {
		this.RetraitDAO.deleteRetrait(noArticle);
	}
	
	public Retrait insert(Retrait retrait) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerRetrait(retrait, businessException);
		if (!businessException.hasErreurs()) {
			retrait = this.RetraitDAO.insert(retrait);
		}
		return retrait;
	}
	/**
	 * Méthode en charge de valider le retrait
	 */
	private void validerRetrait(Retrait retrait, BusinessException businessException) {
		this.validerRue(retrait, businessException);
		this.validerCodePostal(retrait, businessException);
		this.validerVille(retrait, businessException);
	}

	/**
	 * Méthode en charge de valider que l'attribut rue n'est pas null et qu'il ne
	 * fait pas plus de 30 lettres
	 */
	private void validerRue(Retrait retrait, BusinessException businessException) {
		if (retrait.getRue() == null || retrait.getRue().length() > 30) {
			businessException.ajouterErreur(CodeResultatBll.RUE_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de verifier que le code postal est composé de 5 chiffres
	 */
	private void validerCodePostal(Retrait retrait, BusinessException businessException) {
		if (retrait.getCodePostal() == null || !(retrait.getCodePostal().length() == 5)
				|| !retrait.getCodePostal().matches("-?\\d+(\\.\\d+)?")) {
			businessException.ajouterErreur(CodeResultatBll.CODE_POSTAL_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de verifier que la ville
	 */
	private void validerVille(Retrait retrait, BusinessException businessException) {
		if (retrait.getVille() == null || retrait.getVille().length() > 30) {
			businessException.ajouterErreur(CodeResultatBll.VILLE_INVALIDE);
		}
	}
}
