package fr.eni.enchere.javaproject.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.RetraitDAO;
import fr.eni.enchere.javaproject.utils.BusinessException;


public class RetraitManager {

	private RetraitDAO RetraitDAO;
	
	public RetraitManager() {
		
		this.RetraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public void delete(int id) throws BusinessException {

		this.RetraitDAO.delete(id);
	}
	
	public Retrait selectId(int id) throws BusinessException {
		Retrait retrait = this.RetraitDAO.selectId(id);
		return retrait;
	}
	
	public void update(Retrait retrait, int id) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
		this.validerRetrait(retrait, businessException);
		if (!businessException.hasErreurs()) {
			this.RetraitDAO.update(retrait, id);
		}
	}
	
	public List<Retrait> selectAll() throws BusinessException {
		List<Retrait> retraits = new ArrayList<Retrait>();
		retraits = this.RetraitDAO.selectAll();
		return retraits;
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
