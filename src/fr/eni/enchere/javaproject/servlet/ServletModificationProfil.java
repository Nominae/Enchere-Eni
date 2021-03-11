package fr.eni.enchere.javaproject.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.javaproject.bll.UtilisateursManager;
import fr.eni.enchere.javaproject.bo.Utilisateurs;

/**
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/ModificationProfil")
public class ServletModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UtilisateursManager utilisateursManager =  null;
    private String message = "";
    private String error= "";

    @Override
    public void init() throws ServletException {
        
    	super.init();
        utilisateursManager = UtilisateursManager.getInstance();
        
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateur");
        request.getRequestDispatcher("/WEB-INF/connexion/ModifierProfil.jsp").forward(request,response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String pseudo = request.getParameter("pseudo");
	String nom = request.getParameter("nom");
	String prenom = request.getParameter("prenom");
	String email = request.getParameter("email");
	String telephone = request.getParameter("telephone");
	String rue = request.getParameter("rue");
	String codePostal = request.getParameter("codePostal");
	String ville = request.getParameter("ville");
	String motDePasse = request.getParameter("motDePasse");
	String verifMdp = request.getParameter("verifMdp");
	
	Utilisateurs utilisateurModif = null;
	
	if (motDePasse.equals(verifMdp)) {
		Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse, 0, false);
		try {
			utilisateurModif = utilisateursManager.update(utilisateur);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (Exception e) {
			error = e.getMessage();
			e.printStackTrace();
		}
		if (utilisateurModif == null) {
			request.setAttribute("message", error);
			request.setAttribute("utilisateur", utilisateur);
			request.getRequestDispatcher("/WEB-INF/connexion/ModifierProfil.jsp").forward(request, response);
		} else {
			message = "Mise à jour reussi.";
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateurModif);
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/connexion/MonProfil.jsp").forward(request, response);
		}
	} else {
		message = "Les mots de passe ne correspondent pas.";
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/Pages/ModifierProfil.jsp").forward(request, response);
	}
		
	}

}
