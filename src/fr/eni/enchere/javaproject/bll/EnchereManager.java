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
 * Classe en charge d'acceder au methode de la dal li� au enchere, elle verifie
 * aussi que les encheres sont conforme au exigence du projet avant insertion ou
 * modification
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 15 janv. 2021 - 12:31:30
 */
public class EnchereManager implements GeneriqueDAO<Enchere> {
	EnchereDAO enchereDao;

	/**
	 * Constructeur permetant d'obtenir une instance de EnchereDaoImpl en passant
	 * pas le Dao factory
	 */
	public EnchereManager() {
		this.enchereDao = DAOFactory.getEnchere();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws BusinessException {
		this.enchereDao.delete(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Enchere enchere) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerEncheres(enchere, businessException);
		if (!businessException.hasErreurs()) {
			this.enchereDao.insert(enchere);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enchere selectId(int id) throws BusinessException {
		Enchere enchere = this.enchereDao.selectId(id);
		return enchere;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		encheres = this.enchereDao.selectAll();
		return encheres;
	}

	/**
	 * M�thode en charge de retourner une liste d'enchere li� � un article
	 * 
	 * @param noArticle numero de l'article
	 * @return liste d'enchere filtr�
	 * @throws BusinessException
	 */
	public List<Enchere> selectHistoriqueArticle(int noArticle) throws BusinessException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		encheres = this.enchereDao.selectHistoriqueArticle(noArticle);
		return encheres;
	}

	/**
	 * M�thode en charge de retourner une liste d'enchere de maniere decroissante
	 * li� � un article
	 * 
	 * @param noArticle numero de l'article
	 * @return liste d'enchere filtr�
	 * @throws BusinessException
	 */
	public List<Enchere> selectHistoriqueArticleDecroissant(int noArticle) throws BusinessException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		encheres = this.enchereDao.selectHistoriqueArticleDecroissant(noArticle);
		return encheres;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Enchere enchere, int id) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
		this.validerEncheres(enchere, businessException);
		if (!businessException.hasErreurs()) {
			this.enchereDao.update(enchere, id);
		}

	}

	/**
	 * M�thode en charge de verifier si l'enchere propos� respecte les condition �
	 * son insertion ou modification
	 * 
	 * @param enchere           enchere a verifier
	 * @param businessException
	 */
	private void validerEncheres(Enchere enchere, BusinessException businessException) {
		this.validerDateEnchere(enchere, businessException);
		this.validerMontantEnchere(enchere, businessException);
		this.validerNoArticle(enchere, businessException);
		this.validerNoUtilisateur(enchere, businessException);

	}

	/**
	 * M�thode en charge de verifier si le numero d'utilisateur est superieur � 0
	 * 
	 * @param enchere           enchere � verifier
	 * @param businessException
	 */
	private void validerNoUtilisateur(Enchere enchere, BusinessException businessException) {
		if (enchere.getNo_utilisateur() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_UTILISATEUR_INVALIDE);
		}
	}

	/**
	 * M�thode en charge de verifier si le numero d'article est superieur � 0
	 * 
	 * @param enchere           enchere � verifier
	 * @param businessException
	 */
	private void validerNoArticle(Enchere enchere, BusinessException businessException) {
		if (enchere.getNo_article() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_ARTICLE_INVALIDE);
		}

	}

	/**
	 * M�thode en charge de verifier si le montant de l'enchere est superieur � 0
	 * 
	 * @param enchere           enchere � verifier
	 * @param businessException
	 */
	private void validerMontantEnchere(Enchere enchere, BusinessException businessException) {
		if (enchere.getMontant_enchere() < 0) {
			businessException.ajouterErreur(CodeResultatBll.MONTANT_INVALIDE);
		}

	}

	/**
	 * M�thode en charge de verifier si la date de l'enchere est egal ou superieur �
	 * la date du jour
	 * 
	 * @param enchere           enchere � verifier
	 * @param businessException
	 */
	private void validerDateEnchere(Enchere enchere, BusinessException businessException) {
		LocalDate localDate = LocalDate.now().minusDays(1);
		if (enchere.getDate_enchere().toLocalDate().isBefore(localDate)) {
			businessException.ajouterErreur(CodeResultatBll.DATE_DEBUT_INFERIEUR_JOUR);
		}
	}
}