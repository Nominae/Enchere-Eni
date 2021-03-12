package fr.eni.enchere.javaproject.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Article;
import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.dal.ArticleDAO;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.utils.BusinessException;

/**
 * Classe en charge d'acceder aux differentes methode de la dal lié aux article,
 * elle fait des verifications de conformité des articles avant insertion et
 * l'update
 */

public class ArticleManager {
	private ArticleDAO articleDao;

	
	/**
	 * Constructeur qui permet d'obtenir une instance de articleDaoImpl en passant
	 * par DaoFactory
	 */
	public ArticleManager() {
		this.articleDao = DAOFactory.getArticleDAO();
	}

	/**
	 * M�thode en charge de deleguer à la dal la suppresion de l'article
	 */
	public void delete(int id) throws BusinessException {
		this.articleDao.delete(id);

	}

	/**
	 * Méthode en charge de deleguer l'insertion d'un retrait, puis d'un article. La
	 * methode qui est appelée pour l'insertion du retrait, verifie si c'est un
	 * nouveau retrait ou si il est deja en bdd, et retourne dans tout les cas un
	 * retrait qui permet de recuperer le numero de retrait lié a l'article a mettre
	 * en vente
	 */
	public void insert(Article article, Retrait retrait) throws BusinessException {
		BusinessException businessException = new BusinessException();
		RetraitManager retraitManageur = new RetraitManager();
		retrait = retraitManageur.insert(retrait);
			this.validerArticle(article, businessException);
			if (!businessException.hasErreurs()) {
				article.setEtatVente(false);
				article.setNo_retrait(retrait.getNoRetrait());
				this.articleDao.insert(article);
			} else {
				this.validerArticle(article, businessException);
				if (!businessException.hasErreurs()) {
					article.setEtatVente(false);
					article.setNo_retrait(retrait.getNoRetrait());		
				}
			}
	}

	/**
	 * Méthode en charge de selectionner un article par son id
	 */
	public Article selectId(int id) throws BusinessException {
		Article article = new Article();
		article = this.articleDao.selectId(id);
		return article;
	}

	/**
	 * Méthode en charge de selectionner les articles en fonction d'une categorie
	 */
	public List<Article> selectCategorie(int noCategorie) throws BusinessException {
		List<Article> articles = new ArrayList<>();
		articles = this.articleDao.selectCategorie(noCategorie);
		return articles;
	}

	/**
	 * Méthode en charge de selectionner les articles en fonction d'une categorie
	 */
	public List<Article> selectRechercher(String rechercher) throws BusinessException {
		List<Article> articles = new ArrayList<>();
		articles = this.articleDao.selectRechercher(rechercher);
		return articles;
	}

	/**
	 * Méthode en charge de selectionner les articles par mot clef et no de categorie
	 */
	public List<Article> selectCategorieRechercher(String rechercher, int noCategorie) throws BusinessException {
		List<Article> article = new ArrayList<>();
		article = this.articleDao.selectCategorieRechercher(rechercher, noCategorie);
		return article;
	}

	/**
	 * Méthode en charge de selectionner tout les articles en bdd
	 */
	public List<Article> selectAll() throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAll();
		return articles;
	}

	/**
	 * Méthode en charge de selectionner les articles des autres utilisateurs
	 */
	public List<Article> selectAchatAll(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAchatAll(noUtilisateur);
		return articles;
	}

	/**
	 * Méthode en charge de selectionner les articles sur lequel l'utilisateur a
	 * encheri
	 */
	public List<Article> selectAchatEnchereEnCour(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAchatEnchereEnCour(noUtilisateur);
		return articles;
	}

	/**
	 * Méthode en charge de selectionner les encheres remportés par l'utilisateur
	 */
	public List<Article> selectAchatEnchereRemporte(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAchatEnchereRemporte(noUtilisateur);
		return articles;
	}

	/**
	 * Méthode en charge de selectionner tout les articles que l'utilisateur � mis
	 * en vente
	 */
	public List<Article> selectVenteAll(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectVenteAll(noUtilisateur);
		return articles;
	}

	/**
	 * Méthode en charge de selectionner tout les articles mise en vente par
	 * l'utilisateur lorsque la date de fin d'enchere est passée
	 */
	public List<Article> selectVenteTermine(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectVenteTermine(noUtilisateur);
		return articles;
	}

	/**
	 * Méthode en charge de selectionner tout les articles mise en vente
	 * par l'utilisateur lorsque l'enchere est en cours
	 */
	public List<Article> selectVenteEnCour(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectVenteEnCour(noUtilisateur);
		return articles;
	}

	/**
	 * Méthode en charge de selectionner tout les articles mise en vente par
	 * l'utilisateur lorsque l'enchere n'a pas debutée
NON IMPLEMENTEE, NE MARCHE PAS
	 
	public List<Article> selectVenteNonDebute(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectVenteNonDebute(noUtilisateur);
		return articles;
	}
	*/

	/**
	 * Méthode en charge d'update un article
	 */
	public void update(Article article, int id) throws BusinessException, SQLException {

		BusinessException businessException = new BusinessException();

		this.validerArticle(article, businessException);
		if (!businessException.hasErreurs()) {
			this.articleDao.update(article, id);
		} else {
			throw businessException;
		}
	}

	/**
	 * Méthode en charge d'update le prix de vente d'un article
	 */
	public void updatePrixVente(int id, int prixDeVente) throws BusinessException, SQLException {

		BusinessException businessException = new BusinessException();

		this.validerArticleModifPrixDeVente(id, prixDeVente, businessException);
		if (!businessException.hasErreurs()) {
			this.articleDao.updatePrixVente(id, prixDeVente);
		} else {
			throw businessException;
		}
	}

	/**
	 * Méthode en charge de verifier que l'enchere proposé est plus elevé que le
	 * prix de vente
	 */
	private void validerArticleModifPrixDeVente(int id, int prixDeVente, BusinessException businessException)
			throws BusinessException {
		Article article = this.selectId(id);
		if (prixDeVente < article.getPrix_vente()) {
			businessException.ajouterErreur(CodeResultatBll.ENCHERE_INFERIEUR_PRIX_DE_VENTE);
		}

	}

	/**
	 * Méthode en charge de verifier si l'article est conforme
	 */
	private void validerArticle(Article article, BusinessException businessException) {
		this.validerNomArticle(article, businessException);
		this.validerDescription(article, businessException);
		this.validerDebutEnchere(article, businessException);
		this.validerFinEnchere(article, businessException);
		this.validerPrixInitial(article, businessException);
		this.validerPrixVente(article, businessException);
		this.validerNoUtilisateur(article, businessException);
		this.validerNoCategorie(article, businessException);
		this.validerNoRetrait(article, businessException);

	}

	/**
	 * Méthode en charge de verifier si le numero de retrait est supérieur à
	 * 0
	 */
	private void validerNoRetrait(Article article, BusinessException businessException) {
		if (article.getNo_retrait() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_RETRAIT_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de verifier si le numero de categorie est supérieur
	 *à 0
	 */
	private void validerNoCategorie(Article article, BusinessException businessException) {
		if (article.getNo_categorie() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_CATEGORIE_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de valider le numero d'utilisateur il doit etre superieur à
	 * 0
	 */
	private void validerNoUtilisateur(Article article, BusinessException businessException) {
		if (article.getNo_utilisateur() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_UTILISATEUR_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de valider le prix de vente, la methode verifie que le prix
	 * de vente est superieur à 0 et est superieur au prix initial
	 */
	private void validerPrixVente(Article article, BusinessException businessException) {
		if (article.getPrix_vente() < 0) {
			businessException.ajouterErreur(CodeResultatBll.PRIX_VENTE_INVALIDE);
		}
		if (article.getPrix_initial() > article.getPrix_vente()) {
			businessException.ajouterErreur(CodeResultatBll.PRIX_VENTE_INITIAL_INFERIEUR);
		}

	}

	/**
	 * Méthode en charge de verifier que le prix initial est superieur à 0
	 */
	private void validerPrixInitial(Article article, BusinessException businessException) {
		if (article.getPrix_initial() < 0) {
			businessException.ajouterErreur(CodeResultatBll.PRIX_VENTE_INITIAL_INVALIDE);
		}

	}

	/**
	 * Méthode en charge de verifier que la date de fin d'enchere est superieur à la
	 * date de debut d'enchere
	 */
	private void validerFinEnchere(Article article, BusinessException businessException) {
		if (article.getDate_debut_encheres().after(article.getDate_fin_encheres())) {
			businessException.ajouterErreur(CodeResultatBll.DATE_DEBUT_INFERIEUR);
		}

	}

	/**
	 * Méthode en charge de verifier que la date de debut n'est pas avant la date du
	 * jour
	 */
	private void validerDebutEnchere(Article article, BusinessException businessException) {
		LocalDate localDate = LocalDate.now().minusDays(1);
		if (article.getDate_debut_encheres().equals(new Date())
				|| article.getDate_debut_encheres().toLocalDate().isBefore(localDate)) {
			businessException.ajouterErreur(CodeResultatBll.DATE_DEBUT_INFERIEUR_JOUR);
		}
	}

	/**
	 * Méthode en charge de verifier que la description est ni null ni superieur à
	 * 300
	 */
	private void validerDescription(Article article, BusinessException businessException) {
		if (article.getDescription().length() > 300 || article.getDescription() == null) {
			businessException.ajouterErreur(CodeResultatBll.DESCRITPION_INVALIDE);
		}

	}

	/**
	 * Méthode en charge de verifier que le nom de l'article n'est pas superieur à
	 * 30 lettres
	 */
	private void validerNomArticle(Article article, BusinessException businessException) {
		if (article.getNom_article().length() > 30) {
			businessException.ajouterErreur(CodeResultatBll.ARTICLE_NOM_INVALIDE);
		}
	}
}