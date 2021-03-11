package fr.eni.enchere.javaproject.bll;

import java.util.ArrayList;
import java.sql.SQLException;


import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.UtilisateursDAO;
import fr.eni.enchere.javaproject.utils.BusinessException;


public class UtilisateursManager {
	
	private static UtilisateursManager instance;
	
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
	
	public Utilisateurs getUtilisateursLogin(String EmailouPseudo, String motDePasse) {
		
		Utilisateurs utilisateur = null;
		
		
		try {
			
			utilisateur = utilisateursDAO.selectLogin(EmailouPseudo, motDePasse);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		return utilisateur;
			
	}
		
	

	public boolean verifUtilisateurLogin(String EmailouPseudo) {
	    
		boolean utilisateurExist= false;
	
		boolean verifEmail;
		boolean verifPseudo;
		
	    try {
	    	
	    	verifEmail = utilisateursDAO.verifEmail(EmailouPseudo, 0);
	        verifPseudo = utilisateursDAO.verifPseudo(EmailouPseudo, 0);
	    	
	        if((verifEmail & !verifPseudo)||(!verifEmail & verifPseudo)){
	           
	        	utilisateurExist= true;
	         
	        }
	
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        
	    }
	    
	    return utilisateurExist;
	    
	}
	
	public Utilisateurs selectId(int id) throws BusinessException {
		Utilisateurs utilisateur = null;
		utilisateur = this.utilisateursDAO.selectId(id);
		return utilisateur;
	}
		
		public Utilisateurs VerifConnexion(String pseudo, String mdp) throws BusinessException {
			Utilisateurs utilisateur = null;
			BusinessException businessException = new BusinessException();
			boolean connexion = false;
			utilisateur = this.selectPseudo(pseudo);
			if (!utilisateur.getPseudo().isEmpty()) {
				if (utilisateur.getMotDePasse().equals(mdp)) {
					connexion = true;

				}
			} else {
				businessException.ajouterErreur(CodeResultatBll.PSEUDO_INEXISTANT);
			}
			if (!connexion) {
				businessException.ajouterErreur(CodeResultatBll.ECHEC_CONNEXION_MDP_INCORRECT);
				utilisateur = null;
				return null;
			} else {
				return utilisateur;
			}
		}
		
		public Utilisateurs selectPseudo(String pseudo) throws BusinessException {
			Utilisateurs utilisateur = null;
			utilisateur = this.utilisateursDAO.selectPseudo(pseudo);
			return utilisateur;
		}
		
		public void AjouterCredit(Utilisateurs utilisateur, int debit) throws BusinessException, SQLException {
			BusinessException businessException = new BusinessException();
			int credit = utilisateur.getCredit() + debit;
			utilisateur.setCredit(credit);
			this.validerCredit(utilisateur, businessException);

			if (!businessException.hasErreurs()) {

				this.utilisateursDAO.updateCredit(utilisateur);

			} else {
				throw businessException;
			}

		}
		
		private void validerCredit(Utilisateurs utilisateur, BusinessException businessException) {
			if (utilisateur.getCredit() < 0) {
				businessException.ajouterErreur(CodeResultatBll.CREDIT_INVALIDE);
			}
		}

		public static UtilisateursManager getInstance() {
			if (instance == null) {
	            return new UtilisateursManager();
	        }
	        return instance;
		}
		
		public Utilisateurs update(Utilisateurs utilisateur) throws Exception {
	        
			Utilisateurs utilisateurReturn = null;
	        
	        boolean verifEmail = utilisateursDAO.verifEmail(utilisateur.getEmail(), utilisateur.getNoUtilisateur());
	        boolean verifPseudo = utilisateursDAO.verifPseudo(utilisateur.getPseudo(), utilisateur.getNoUtilisateur());
	        
	        if ((verifEmail) & (verifPseudo)) {
	            throw new Exception("L'email et le pseudo sont déjà présent en base");
	        } else if ((verifEmail) & (!verifPseudo)) {
	            throw new Exception("L'email saisi est déjà utilisé");
	        } else if ((!verifEmail) & (verifPseudo)) {
	            throw new Exception("Le pseudo est déjà pris");
	        } else {
	            try {
	            	
	                utilisateurReturn = utilisateursDAO.updateUser(utilisateur);
	                
	            } catch (Exception e) {
	            	
	            	e.printStackTrace();
	            	
	            }
	        }
	        return utilisateurReturn;
	    }

	}
	

