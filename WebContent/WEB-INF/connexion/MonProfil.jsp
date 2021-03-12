<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Mon Profil</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  			<a class="navbar-brand" href="<%=request.getContextPath()%>/AcceuilConnecte">ENI-Encheres</a>
		</nav>
		<div>
			<p>Pseudo : <span>${utilisateur.pseudo}</span></p>
			<p>Nom : <span>${utilisateur.nom}</span></p>
			<p>Prénom : <span>${utilisateur.prenom}</span></p>
			<p>Email : <span>${utilisateur.email}</span></p>
			<p>Teléphone : <span>${utilisateur.telephone}</span></p>
			<p>Rue : <span>${utilisateur.rue}</span></p>
			<p>Code Postal : <span>${utilisateur.codePostal}</span></p>
			<p>Ville : <span>${utilisateur.ville}</span></p>
			<p>Crédit : <span>${utilisateur.credit}</span></p>
		</div>
		<br>
		<a href="<%=request.getContextPath()%>/ModificationProfil">Modifier</a>
		<footer class="text-center">To be determined</footer>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>