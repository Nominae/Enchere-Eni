package fr.eni.enchere.javaproject.bll;



import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Enchere;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.EnchereDAO;
import fr.eni.enchere.javaproject.dal.GeneriqueDAO;
import fr.eni.enchere.javaproject.utils.BusinessException;

/**
 * Classe en charge d'acceder au methode de la dal liée aux encheres, elle verifie
 *  que les encheres sont conformes aux exigence du projet avant insertion ou
 * modification
 */
public class EnchereManager implements GeneriqueDAO<Enchere> {
	EnchereDAO enchereDao;

	/**
	 * Constructeur permetant d'obtenir une instance de EnchereDaoImpl en passant
	 * par le DAOFACTORY
	 */
	public EnchereManager() {
		this.enchereDao = DAOFactory.getEnchere();
	}


	@Override
	public void delete(int id) throws BusinessException {
		this.enchereDao.delete(id);
	}

	@Override
	public void insert(Enchere enchere) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerEncheres(enchere, businessException);
		if (!businessException.hasErreurs()) {
			this.enchereDao.insert(enchere);
		}

	}


	@Override
	public Enchere selectId(int id) throws BusinessException {
		Enchere enchere = this.enchereDao.selectId(id);
		return enchere;
	}


	@Override
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		encheres = this.enchereDao.selectAll();
		return encheres;
	}

	/**
	 * Méthode en charge de retourner une liste d'enchere liée à un article
	 */
	public List<Enchere> selectHistoriqueArticle(int noArticle) throws BusinessException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		encheres = this.enchereDao.selectHistoriqueArticle(noArticle);
		return encheres;
	}

	/**
	 * Méthode en charge de retourner une liste d'enchere de manière décroissante
	 * liée à un article
	 */
	public List<Enchere> selectHistoriqueArticleDecroissant(int noArticle) throws BusinessException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		encheres = this.enchereDao.selectHistoriqueArticleDecroissant(noArticle);
		return encheres;
	}

	@Override
	public void update(Enchere enchere, int id) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
		this.validerEncheres(enchere, businessException);
		if (!businessException.hasErreurs()) {
			this.enchereDao.update(enchere, id);
		}

	}

	/**
	 * Méthode en charge de verifier si l'enchere proposée respecte les condition de
	 * son insertion ou modification
	 
	 */
	private void validerEncheres(Enchere enchere, BusinessException businessException) {
		this.validerDateEnchere(enchere, businessException);
		this.validerMontantEnchere(enchere, businessException);
		this.validerNoArticle(enchere, businessException);
		this.validerNoUtilisateur(enchere, businessException);

	}

	/**
	 * Méthode en charge de verifier si le numero d'utilisateur est superieur à 0
	 */
	private void validerNoUtilisateur(Enchere enchere, BusinessException businessException) {
		if (enchere.getNo_utilisateur() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_UTILISATEUR_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de verifier si le numero d'article est superieur à 0
	 */
	private void validerNoArticle(Enchere enchere, BusinessException businessException) {
		if (enchere.getNo_article() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_ARTICLE_INVALIDE);
		}

	}

	/**
	 * Méthode en charge de verifier si le montant de l'enchere est superieur à 0
	 */
	private void validerMontantEnchere(Enchere enchere, BusinessException businessException) {
		if (enchere.getMontant_enchere() < 0) {
			businessException.ajouterErreur(CodeResultatBll.MONTANT_INVALIDE);
		}

	}

	/**
	 * Méthode en charge de verifier si la date de l'enchere est egale ou superieure à
	 * la date du jour
	 */
	private void validerDateEnchere(Enchere enchere, BusinessException businessException) {
		LocalDate localDate = LocalDate.now().minusDays(1);
		if (enchere.getDate_enchere().toLocalDate().isBefore(localDate)) {
			businessException.ajouterErreur(CodeResultatBll.DATE_DEBUT_INFERIEUR_JOUR);
		}
	}
}