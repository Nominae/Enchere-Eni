package fr.eni.javaproject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.javaproject.bll.UtilisateursManager;

/**
 * Servlet implementation class ServletUtilisateurs
 */
@WebServlet("/ServletUtilisateurs")
public class ServletUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateursManager usermana = new UtilisateursManager();
		usermana.insertUser(0, "MARIO", "Mario", "Kart", "mk17@gmail.com", "0123456789", "Rue de Peach", "77000", "paris", "azerty", 100, false);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
