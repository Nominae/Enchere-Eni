package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.javaproject.bll.EnchereManager;
import fr.eni.enchere.javaproject.bll.UtilisateursManager;
import fr.eni.enchere.javaproject.bo.Article;
import fr.eni.enchere.javaproject.bo.Enchere;
import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.utils.BusinessException;



public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String DELETE = "delete from ARTICLES_VENDUS where no_article=?";
	
	private static final String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait, etatVente) "
			+ "values(?,?,?,?,?,?,?,?,?,?)";
	
	private static final String INSERT_AVEC_CHEMIN_IMG = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait, etatVente) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_ID = "select * from ARTICLES_VENDUS where no_article=?";
	
	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS";
	
	private static final String SELECT_NO_CATEGORIE = "select * from ARTICLES_VENDUS where no_categorie=? and etatVente=0";
	
	private static final String SELECT_RECHERCHER = "select * from ARTICLES_VENDUS where nom_article like '%' + ? + '%' and etatVente=0";
	
	private static final String SELECT_RECHERCHER_CATEGORIE = "select * from ARTICLES_VENDUS where nom_article like '%' + ? + '%' and no_categorie=? and etatVente=0";
	
	private static final String SELECT_ACHAT_ALL = "select * from ARTICLES_VENDUS where no_utilisateur not in (?) and etatVente=0";
	
	private static final String SELECT_ACHAT_ENCHERE_EN_COURS = "select ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,ARTICLES_VENDUS.no_categorie,ARTICLES_VENDUS.no_retrait,etatVente"
			+ " from ARTICLES_VENDUS inner join ENCHERES  on ARTICLES_VENDUS.no_article=ENCHERES.no_article where ENCHERES.no_utilisateur=? and etatVente=0 and ARTICLES_VENDUS.no_utilisateur not in (?)"
			+ " group by ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,ARTICLES_VENDUS.no_categorie,ARTICLES_VENDUS.no_retrait,etatVente";
	
	private static final String SELECT_ACHAT_ENCHERE_REMPORTE = "select ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,ARTICLES_VENDUS.no_categorie,ARTICLES_VENDUS.no_retrait,etatVente"
			+ " from ARTICLES_VENDUS inner join ENCHERES  on ARTICLES_VENDUS.no_article=ENCHERES.no_article where ENCHERES.no_utilisateur=? and etatVente=1 and ARTICLES_VENDUS.no_utilisateur not in (?)"
			+ " group by ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,ARTICLES_VENDUS.no_categorie,ARTICLES_VENDUS.no_retrait,etatVente";
	
	private static final String SELECT_VENTE_ALL = "select * from ARTICLES_VENDUS where no_utilisateur=?";
	
	private static final String SELECT_VENTE_EN_COURS = "select * from ARTICLES_VENDUS where no_utilisateur=? and etatVente=0";
	
	private static final String SELECT_VENTE_TERMINE = "select * from ARTICLES_VENDUS where no_utilisateur=? and etatVente=1";
	
	private static final String UPDATE = "update ARTICLES_VENDUS Set nom_article= ?,description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?,prix_vente=?, no_utilisateur=?,no_categorie=?, no_retrait=? where no_article=?";
	
	private static final String UPDATE_PRIX_DE_VENTE = "update ARTICLES_VENDUS Set  prix_vente=? where no_article=?";
	
	private static final String UPDATE_ETAT_VENTE = "update ARTICLES_VENDUS Set  etatVente=? where no_article=?";

	
	@Override
	public void delete(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE);
			throw businessException;
		}

	}


	@Override
	public void insert(Article article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNom_article());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, article.getDate_debut_encheres());
			pstmt.setDate(4, article.getDate_fin_encheres());
			pstmt.setInt(5, article.getPrix_initial());
			pstmt.setInt(6, article.getPrix_vente());
			pstmt.setInt(7, article.getNo_utilisateur());
			pstmt.setInt(8, article.getNo_categorie());
			pstmt.setInt(9, article.getNo_retrait());
			pstmt.setBoolean(10, article.getEtatVente());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				article.setNo_utilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERTION_ARTICLE);
			throw businessException;
		}
	}

	
	/**@Override
	public void insertAvecCheminImg(Article article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_AVEC_CHEMIN_IMG,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNom_article());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, article.getDate_debut_encheres());
			pstmt.setDate(4, article.getDate_fin_encheres());
			pstmt.setInt(5, article.getPrix_initial());
			pstmt.setInt(6, article.getPrix_vente());
			pstmt.setInt(7, article.getNo_utilisateur());
			pstmt.setInt(8, article.getNo_categorie());
			pstmt.setInt(9, article.getNo_retrait());
			pstmt.setBoolean(10, article.getEtatVente());
			pstmt.setString(11, article.getCheminImg());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				article.setNo_utilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERTION_ARTICLE);
			throw businessException;
		}
	}**/

	
	@Override
	public Article selectId(int id) throws BusinessException {
		Article article = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				article = this.articleConstructeur(rs);
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ID_INEXISTANT);
				throw businessException;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return article;
	}

	
	@Override
	public List<Article> selectCategorie(int noCategorie) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_NO_CATEGORIE);
			pstmt.setInt(1, noCategorie);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	
	@Override
	public List<Article> selectRechercher(String rechercher) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RECHERCHER);
			pstmt.setString(1, rechercher);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	
	@Override
	public List<Article> selectCategorieRechercher(String rechercher, int noCategorie) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RECHERCHER_CATEGORIE);
			pstmt.setString(1, rechercher);
			pstmt.setInt(2, noCategorie);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	@Override
	public List<Article> selectAchatAll(int noutilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ACHAT_ALL);
			pstmt.setInt(1, noutilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	
	@Override
	public List<Article> selectAchatEnchereEnCour(int noutilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ACHAT_ENCHERE_EN_COURS);
			pstmt.setInt(1, noutilisateur);
			pstmt.setInt(2, noutilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	@Override
	public List<Article> selectAchatEnchereRemporte(int noutilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		EnchereManager EnchereManager = new EnchereManager();
		Article article = null;
		List<Enchere> encheres = new ArrayList<>();
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ACHAT_ENCHERE_REMPORTE);
			pstmt.setInt(1, noutilisateur);
			pstmt.setInt(2, noutilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				article = this.articleConstructeur(rs);
				encheres = EnchereManager.selectHistoriqueArticle(article.getNo_article());
				if (!encheres.isEmpty()) {
					enchere = encheres.get(encheres.size() - 1);
					if (enchere.getNo_utilisateur() == noutilisateur) {
						articles.add(article);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}


	@Override
	public List<Article> selectVenteAll(int noutilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_VENTE_ALL);
			pstmt.setInt(1, noutilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	
	@Override
	public List<Article> selectVenteEnCour(int noutilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_VENTE_EN_COURS);
			pstmt.setInt(1, noutilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * 
	 
	@Override
	public List<Article> selectVenteNonDebute(int noutilisateurs) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		Article articleCourant = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_VENTE_EN_COURS);
			pstmt.setInt(1, noutilisateurs);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleCourant = this.articleConstructeur(rs);
				if (articleCourant.getDate_debut_encheres().after(new Date())) {
					articles.add(articleCourant);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}
	 */

	
	@Override
	public List<Article> selectVenteTermine(int noutilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_VENTE_TERMINE);
			pstmt.setInt(1, noutilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	
	@Override
	public List<Article> selectAll() throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		Article articleCourant = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleCourant = this.articleConstructeur(rs);
				if (!articleCourant.getEtatVente()) {
					articles.add(articleCourant);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	
	@Override
	public void update(Article article, int id) throws BusinessException, SQLException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, article.getNom_article());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, article.getDate_debut_encheres());
			pstmt.setDate(4, article.getDate_fin_encheres());
			pstmt.setInt(5, article.getPrix_initial());
			pstmt.setInt(6, article.getPrix_vente());
			pstmt.setInt(7, article.getNo_utilisateur());
			pstmt.setInt(8, article.getNo_categorie());
			pstmt.setInt(9, article.getNo_retrait());
			pstmt.setInt(10, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updatePrixVente(int id, int prixDeVente) throws BusinessException, SQLException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_PRIX_DE_VENTE);
			pstmt.setInt(1, prixDeVente);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode en charge d'update l'etat de vente de l'article en bdd, de
	 * vente à vendu
	 */

	public void updateEtatVente(Article article) throws BusinessException, SQLException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ETAT_VENTE);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, article.getNo_article());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode en charge de constuire les articles après extraction de la bd. la
	 * methode verifie aussi que la date de fin d'enchere a expiré, si c'est le cas
	 * elle update en bdd le statut de la vente et verifie si une enchere a été passée
	 * sur l'article. Auquel cas elle credite le vendeur du montant de l'offre
	 */
	private Article articleConstructeur(ResultSet rs) throws SQLException, BusinessException {
		Article article = new Article();
		article.setNo_article(rs.getInt("no_article"));
		article.setNom_article(rs.getString("nom_article"));
		article.setDescription(rs.getString("description"));
		article.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
		article.setDate_fin_encheres(rs.getDate("date_fin_encheres"));
		article.setPrix_initial(rs.getInt("prix_initial"));
		article.setPrix_vente(rs.getInt("prix_vente"));
		article.setNo_utilisateur(rs.getInt("no_utilisateur"));
		article.setNo_categorie(rs.getInt("no_categorie"));
		article.setNo_retrait(rs.getInt("no_retrait"));
		article.setEtatVente(rs.getBoolean("etatVente"));
		if (article.getDate_fin_encheres().toLocalDate().isBefore(LocalDate.now().plusDays(1))) {
			this.updateEtatVente(article);
			article.setEtatVente(true);
			EnchereManager EnchereManager = new EnchereManager();
			List<Enchere> encheres = EnchereManager.selectHistoriqueArticle(article.getNo_article());
			if (!encheres.isEmpty()) {
				UtilisateursManager utilisateursManager = new UtilisateursManager();
				Utilisateurs utilisateurs = null;
				utilisateurs = utilisateursManager.selectId(article.getNo_utilisateur());
				utilisateursManager.AjouterCredit(utilisateurs, article.getPrix_vente());
				//Ajouter methode ajouterCredit
			}
		}
		return article;
	}

	
}