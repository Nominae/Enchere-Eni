package fr.eni.enchere.javaproject.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.javaproject.bll.UtilisateursManager;

/**
 * Servlet implementation class ServletDelete
 */
@WebServlet("/DeleteUser")
public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UtilisateursManager utilisateurManager =  null;
    private String error = "";
	
    @Override
    public void init() throws ServletException {
        super.init();
        utilisateurManager = UtilisateursManager.getInstance();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/NonConnecte");
		
		rd.forward(request, response);
		
		//TODO: NoUtilisateur a href ca passe par doget
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		System.out.println("Numero utilisateur avant supp" + noUtilisateur);
		boolean verifDelete = false;
		System.out.println(noUtilisateur);
		try {
			
			verifDelete = utilisateurManager.deleteUser(noUtilisateur);
			
		} catch (Exception e) {

			e.printStackTrace();
			error = e.getMessage();
			
		}
		if(verifDelete){
			System.out.println("CSUPP'MEC");
			session.invalidate();
            request.getRequestDispatcher("/NonConnecte").forward(request,response);
            System.out.println("CSUPP'MEC");
        }else{
            request.setAttribute("message", error);
            request.setAttribute("utilisateur", session);
            request.getRequestDispatcher("/ModificationProfil").forward(request,response);
        }
		
	}

}
