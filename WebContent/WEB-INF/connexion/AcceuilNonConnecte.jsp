<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Accueil Non Connecté</title>
	</head>
	<body>
		<nav class="navbar navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/NonConnecte">ENI-Encheres</a>
				<p class="navbar-brand"><a href="<%=request.getContextPath()%>/creationcompte">S'incrire</a> - <a href="<%=request.getContextPath()%>/Validation">Se connecter</a></p>
			</div>
		</nav>
		<h1>Liste des enchères</h1>
		<br>
		<br>
		<div>
			<label for="site-search">Filtres :</label>
			<input type="search" id="site-search" name="recherche" aria-label="Le nom de l'article contient">
			<button>Rechercher</button>
		</div>
		<br>
		<div>
			<label for="Selection-categorie">Catégorie :</label>
			<select name="Categorie" id="Selection-categorie">
				<option value="Toutes">Toutes </option>
				<option value="Informatique">Informatique </option>
				<option value="Ameublement">Ameublement </option>
				<option value="Vêtement">Vêtement </option>
				<option value="Sport&Loisirs">Sport et Loisirs </option>
			</select>
		</div>
		<br>
		<br>
		<div>
			<img src="../WebContent/images/casqueTNBnoir.jpg"	alt="Exemple de produit - casque audio">
			<div>
				<h4>CASQUE AUDIO</h4>
				<p> Prix : tbd </p>
				<p> Fin de l'enchère : JJ/MM/AAAA </p>
				<p> Vendeur : Nom du vendeur </p>
			</div>
		</div>
		<br>
		<div>
			<img src="#"	alt="Exemple de produit - #">
			<div>
				<h4>PIOU PIOU</h4>
				<p> Prix : tbd </p>
				<p> Fin de l'enchère : JJ/MM/AAAA </p>
				<p> Vendeur : Nom du vendeur </p>
			</div>
		</div>
		<footer>To be determined</footer>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>