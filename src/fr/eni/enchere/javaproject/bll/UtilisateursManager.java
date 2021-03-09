package fr.eni.enchere.javaproject.bll;

import java.util.ArrayList;
import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.UtilisateursDAO;

public class UtilisateursManager {
	
	private UtilisateursDAO utilisateursDAO;
		
	public UtilisateursManager() {
		
		this.utilisateursDAO = DAOFactory.getUtilisateursDAO();
		
	}
	
	public Utilisateurs insertUser(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		
		Utilisateurs utilisateurs = new Utilisateurs();
	
		utilisateurs.setPseudo(pseudo);
		utilisateurs.setNom(nom);
		utilisateurs.setPrenom(prenom);
		utilisateurs.setEmail(email);
		utilisateurs.setTelephone(telephone);
		utilisateurs.setRue(rue);
		utilisateurs.setCodePostal(codePostal);
		utilisateurs.setVille(ville);
		utilisateurs.setMotDePasse(motDePasse);
		utilisateurs.setCredit(0);
		utilisateurs.setAdministrateur(false);
		if(verifMail(email) == true && verifPseudo(pseudo) == true) {
			this.utilisateursDAO.insertUser(utilisateurs); 
			System.out.println("CPALAMERDE");
			
		}else {
			//throw new BusinessException("L'email est déjà utilisé, veuillez en utiliser un autre.");
			System.out.println("CLAMERDE");
		}
		return utilisateurs;
		
	}

	public Boolean verifMail (String mail) {
		
		ArrayList<String> listMail = utilisateursDAO.selectAllEmail();
		
		for (String email : listMail) {
			
			if(mail.equals(email)) return false;
				
		}
		
		return true;
		
	}
	
	public Boolean verifPseudo (String pseudo2) {
		
		ArrayList<String> listPseudo = utilisateursDAO.selectAllPseudo();
		
		for (String pseudo : listPseudo) {
			
			if(pseudo2.equals(pseudo)) return false;
				
		}
		
		return true;
		
	}
	
}
