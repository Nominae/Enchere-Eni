<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Insert title here</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  			<a class="navbar-brand" href="<%=request.getContextPath()%>/AcceuilConnecte">ENI-Encheres</a>
		</nav>
		<br>
		<br>
		<h2>Mon profil</h2>
		<br>
		<div>
			<form action="<%=request.getContextPath()%>/ModificationProfil" method="post" class="form">
				<input type="hidden" name="noUtilisateur" value="${utilisateur.noUtilisateur}">
				
			    <label for="pseudo">Pseudo : </label>
			    <input type="text" value="${utilisateur.pseudo}" name="pseudo" id="pseudo">
			    
			    <label for="nom">Nom : </label>
			    <input type="text" value="${utilisateur.nom}" name="nom" id="nom"><br>
			    
			    <label for="prenom">Prénom : </label>
			    <input type="text" value="${utilisateur.prenom}" name="prenom" id="prenom">
			    
			    <label for="email">Email : </label>
			    <input type="email" value="${utilisateur.email}" name="email" id="email"><br>
			    
			    <label for="telephone">Téléphone : </label>
			    <input type="tel" value="${utilisateur.telephone}" name="telephone" id="telephone">
			    
			    <label for="rue">Rue : </label>
			    <input type="text" value="${utilisateur.rue}" name="rue" id="rue"><br>
			    
			    <label for="codePostal">Code postal : </label>
			    <input type="number" value="${utilisateur.codePostal}" name="codePostal" id="codePostal">
			    
			    <label for="ville">Ville : </label>
			    <input type="text" value="${utilisateur.ville}" name="ville" id="ville"><br>
			    
			    <label for="motDePasse">Nouveau mot de passe : </label>
			    <input type="password" value="${utilisateur.motDePasse}" name="motDePasse" id="motDePasse">
			    
			    <label for="verifMdp">Confirmation : </label>
			    <input type="password" value="${utilisateur.motDePasse}" name="verifMdp" id="verifMdp"><br>
			    
			    <p>Crédit<b> ${utilisateur.credit}</b></p>
			    
			    <input type="hidden" name="noUtilisateur" value="${utilisateur.noUtilisateur}">
			    <p>${utilisateur.noUtilisateur}</p>
			    
			    <a href="<%=request.getContextPath()%>/AfficherProfil"><input type="submit" value="Enregistrer"></a>
			    <a href="<%=request.getContextPath()%>/DeleteUser">Supprimer mon compte</a>
			    
			</form>
		</div>
		<footer class="text-center">To be determined</footer>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>