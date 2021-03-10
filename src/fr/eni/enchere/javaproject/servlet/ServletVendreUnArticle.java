package fr.eni.enchere.javaproject.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.javaproject.bll.ArticleManager;
import fr.eni.enchere.javaproject.bo.Article;
import fr.eni.enchere.javaproject.bo.Retrait;


/**
 * Servlet implementation class ServletVendreUnArticle
 */
@WebServlet("/connexion/ServletVendreUnArticle")
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vente/vendreArticle.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		Article article = new Article();
		ArticleManager articlemanager = new ArticleManager();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
		try {
			String nom_article = request.getParameter("nom_article");
			String description = request.getParameter("description");
			Date date_debut_encheres = sdf.parse(request.getParameter("date_debut_encheres"));
			Date date_fin_encheres = sdf.parse(request.getParameter("date_fin_encheres"));
			int prix_initial = Integer.parseInt(request.getParameter("prix_initial"));
			int no_categorie = Integer.parseInt(request.getParameter("no_categorie"));
			int no_retrait = Integer.valueOf(request.getParameter("no_retrait"));
			int no_utilisateur = Integer.valueOf(request.getParameter("no_utilisateur"));
			
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			//Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("ConnectedUser");
			
			Retrait retrait = new Retrait();
				
			
			articlemanager.insert(article, retrait);
				
			//RetraitManager.insert(retrait);				
			article.setNom_article(nom_article);
			article.setDescription(description);
			article.setDate_debut_encheres(date_debut_encheres);
			article.setDate_fin_encheres(date_fin_encheres);
			article.setPrix_initial(prix_initial);
			article.setNo_categorie(no_categorie);
			article.setNo_retrait(no_retrait);
			article.setNo_utilisateur(no_utilisateur);
			
			//retrait = new Retrait();
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);
			
			request.setAttribute("ArticleAffiche", article);
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil/ListeEnchereConnecte.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}