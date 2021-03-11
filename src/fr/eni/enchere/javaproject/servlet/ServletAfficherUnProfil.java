package fr.eni.enchere.javaproject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.javaproject.bll.UtilisateursManager;
import fr.eni.enchere.javaproject.bo.Utilisateurs;

/**
 * Servlet implementation class ServletAfficherUnProfil
 */
@WebServlet("/AfficherProfil")
public class ServletAfficherUnProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateursManager utilisateursManager = null;

	@Override
	public void init() throws ServletException {
		super.init();
		utilisateursManager = UtilisateursManager.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		response.getWriter().append("Served at: ").append(request.getContextPath());

		Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateur");
		if (utilisateur == null) {
			response.sendRedirect("/encheres/error?error=NotConnected");
		} else {
			request.getRequestDispatcher("/WEB-INF/connexion/MonProfil.jsp").forward(request, response);
			System.out.println(utilisateur);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
