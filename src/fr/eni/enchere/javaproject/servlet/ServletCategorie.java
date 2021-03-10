package fr.eni.enchere.javaproject.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.javaproject.bll.CategorieManager;
import fr.eni.enchere.javaproject.bo.Categorie;

/**
 * Servlet implementation class ServletCategorie
 */
@WebServlet("/connexion")
public class ServletCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/Categorie.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieManager cm = new CategorieManager();
		String param = request.getParameter("ajoutcategorie");
		String nouvelleCategorie = request.getParameter("nouvelleCategorie");
		
			//ajout d'une cat�gorie
		if (param.equals("AJOUTER") && !nouvelleCategorie.equals("indiquez la nouvelle catégorie à ajouter")) {
			cm.insertCategorie(0, nouvelleCategorie);
		}
			
			//supprimer une cat�gorie
		if (param.equals("Lister toutes les catégories") || param.equals("Supprimer une catégorie")) {
			List<Categorie> listeCategorie = cm.selectAllCat();
			
			List<String> categorie = new ArrayList<>();
			for (Categorie cat : listeCategorie) {
				categorie.add(cat.getLibelle());
			}
			
			request.setAttribute("listeCategorie", categorie);
		}
		
					
		if (param.equals("SUPPRIMER")) {
			String cat = (String) request.getParameter("Catégories");
			cm.deleteCategorie(cat);
		
		}
				
		doGet(request, response);
	}

}