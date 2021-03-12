package fr.eni.enchere.javaproject.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.javaproject.bll.ArticleManager;
import fr.eni.enchere.javaproject.bll.CategorieManager;
import fr.eni.enchere.javaproject.bll.UtilisateursManager;
import fr.eni.enchere.javaproject.bo.Article;
import fr.eni.enchere.javaproject.bo.Categorie;
import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.message.LecteurMessage;
import fr.eni.enchere.javaproject.utils.BusinessException;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/VenteArticle")

public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String uploadPath;
	UtilisateursManager utilisateurManager = null;
	Article unArticle = null;
	Retrait unRetrait = null;
	ArticleManager articleManager = null;
	CategorieManager categoriesManager = null;

	@Override
	public void init() throws ServletException {
		utilisateurManager = new UtilisateursManager();
		unArticle = new Article();
		unRetrait = new Retrait();
		articleManager = new ArticleManager();
		categoriesManager = new CategorieManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Categorie> categories = categoriesManager.selectAll();
			request.setAttribute("categories", categories);
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/Vente/pageVendreUnArticle.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateur");

		// Recuperer les parametres

		String article = request.getParameter("article");
		String description = request.getParameter("description");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		int miseAPrix = Integer.parseInt(request.getParameter("prixInitial"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String debutEnchere = request.getParameter("debutEnchere");
		String finEnchere = request.getParameter("finEnchere");
		int numeroUtilisateur = Integer.parseInt(request.getParameter("numeroUtilisateur"));
		Date dateDebutEnchere = Date.valueOf(debutEnchere);
		Date dateFinEnchere = Date.valueOf(finEnchere);

		// Je recup les données saisies dans le formulaire et je les attributs à l'objet
		unArticle.setNom_article(article);
		unArticle.setDescription(description);
		unArticle.setDate_debut_encheres(dateDebutEnchere);
		unArticle.setDate_fin_encheres(dateFinEnchere);
		unArticle.setPrix_initial(miseAPrix);
		unArticle.setPrix_vente(miseAPrix);
		unArticle.setNo_utilisateur(numeroUtilisateur);
		unArticle.setNo_categorie(categorie);

		unRetrait.setRue(rue);
		unRetrait.setVille(ville);
		unRetrait.setCodePostal(codePostal);
		try {
			articleManager.insert(unArticle, unRetrait);
		} catch (BusinessException e) {
			e.printStackTrace();
			List<Integer> codeErreur = e.getListeCodesErreur();
			List<String> messageErreur = new ArrayList<String>();
			if (!codeErreur.isEmpty()) {
				for (Integer code : codeErreur) {
					messageErreur.add(LecteurMessage.getMessageErreur(code));
				}
				request.setAttribute("erreurs", messageErreur);
			}
			// this.getServletContext().getRequestDispatcher("/VenteArticle").forward(request,
			// response);

			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/NonConnecte");

			rd.forward(request, response);

		}
		System.out.println("YESSSSSS");
		String MESSAGEREUSSITE = "Nouvelle article mis en vente avec succès";
		request.setAttribute("réussite", MESSAGEREUSSITE);
		// Afficher les articles de la base de données
		ArticleManager articleManager = new ArticleManager();
		List<Article> listeArticle = null;
		try {
			listeArticle = articleManager.selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("listeArticle", listeArticle);
		
		//RequestDispatcher rd = request.getRequestDispatcher("/PageAccueil");
		//rd.forward(request, response);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/AcceuilConnecte");
		rd.forward(request, response);

	}
}