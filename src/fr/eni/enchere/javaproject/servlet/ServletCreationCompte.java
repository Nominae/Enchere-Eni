package fr.eni.enchere.javaproject.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.javaproject.bll.UtilisateursManager;
import fr.eni.enchere.javaproject.bo.Utilisateurs;

/**
 * Servlet implementation class ServletUtilisateurs
 */
@WebServlet("/creationcompte")
public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/CreationCompte.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateursManager newUser = new UtilisateursManager();
		
		String pseudo = null;
		String nom = null;
		String prenom = null;
		String email = null;
		String telephone = null;
		String rue = null;
		String codePostal = null;
		String ville = null;
		String motDePasse = null;
		String confirmMotDePasse = null;
		
		pseudo = request.getParameter("pseudo").trim();
		nom = request.getParameter("nom").trim();
		prenom = request.getParameter("prenom").trim();
		email = request.getParameter("email").trim();
		telephone = request.getParameter("telephone");
		rue = request.getParameter("rue").trim();
		codePostal = request.getParameter("codePostal");
		ville = request.getParameter("ville").trim();
		motDePasse = request.getParameter("motDePasse").trim();
		confirmMotDePasse = request.getParameter("confirmMotDePasse").trim();
		
		Utilisateurs user = new Utilisateurs(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 0, false); 
		try {
			
			newUser.insertUser(0, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, confirmMotDePasse, 0, false);
			
			request.setAttribute("user", user);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/AcceuilConnecte.jsp");
			rd.forward(request, response);
			
		}catch (Exception e) {
			
			//request.setAttribute("Erreur", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/connexion/CreationCompte.jsp");
			
		} 
			
			//TODO: ecrire front le message erreur mail
			
	}

}
