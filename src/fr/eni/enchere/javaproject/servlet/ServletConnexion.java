package fr.eni.enchere.javaproject.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.javaproject.bll.UtilisateursManager;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/Connexion.jsp");
		
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateursManager newUser = new UtilisateursManager();
		
		//String pseudo = null;
		String email = null;
		String motDePasse = null;
		
		//pseudo = request.getParameter("pseudo").trim();
		email = request.getParameter("email").trim();
		motDePasse = request.getParameter("motDePasse");
		
		//TODO: verif email verif mdp (select mdp where email=?) affichage du compte
		
		
		doGet(request, response);
	}

}
