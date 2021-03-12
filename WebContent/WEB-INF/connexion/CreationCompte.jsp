<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.javaproject.bo.Utilisateurs"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Création de compte</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  			<a class="navbar-brand" href="<%=request.getContextPath()%>/NonConnecte">ENI-Encheres</a>
		</nav>
		<br>
		<br>
		<h2>Mon profil</h2>
		<br>
		
		<form action="<%=request.getContextPath()%>/creationcompte" method="post">
  			<div class="form-row">
    			<div class="form-group col-md-4">
     				<label for="email">Email</label>
      				<input type="email" class="form-control" placeholder="Email" name="email" id="email" required>
    			</div>
   				 <div class="form-group col-md-4">
      				<label for="motDePasse">Mot de passe</label>
      				<input type="password" class="form-control" id="motDePasse" placeholder="Mot de passe" name="motDePasse" required>
    			</div>
    			 <div class="form-group col-md-4">
      				<label for="confirmMotDePasse">Confirmation mot de passe</label>
      				<input type="password" class="form-control" id="confirmMotDePasse" placeholder="Mot de passe" name="confirmMotDePasse" required>
    			</div>
  			</div>
  			<div class="form-row">
    			<div class="form-group col-md-4">
     				<label for="pseudo">Pseudo</label>
      				<input type="text" class="form-control" placeholder="Pseudo" name="pseudo" id="pseudo" required>
    			</div>
   				 <div class="form-group col-md-4">
      				<label for="nom">Nom</label>
      				<input type="text" class="form-control" id="nom" placeholder="Nom" name="nom" required>
    			</div>
    			 <div class="form-group col-md-4">
      				<label for="prenom">Prenom</label>
      				<input type="text" class="form-control" id="prenom" placeholder="Prenom" name="prenom" required>
    			</div>
  			</div>
  			<div class="form-group">
    			<label for="rue">Rue</label>
    			<input type="text" class="form-control" id="rue" placeholder="Rue" name="rue" required>
  			</div>
  			<div class="form-row">
    			<div class="form-group col-md-4">
      				<label for="codePostal">Code postal</label>
      				<input type="number" class="form-control" id="codePostal" name="codePostal" placeholder="Code postal" required>
    			</div>
    			<div class="form-group col-md-6">
      				<label for="Ville">Ville</label>
      				<input id="ville" class="form-control" id="ville" name="ville" placeholder="Ville" required>
    			</div>
    			<div class="form-group col-md-2">
      				<label for="telephone">Telephone</label>
      				<input type="tel" class="form-control" id="telephone" name="telephone" placeholder="Telephone" required>
    			</div>
  			</div>
  			<div>
  				<button type="submit" class="btn btn-primary">Créer</button>
  				<a href="<%=request.getContextPath()%>/NonConnecte"><input class="btn btn-primary" type="reset" value="Annuler" ></a>
  			</div>
		</form>
		
		
		
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>