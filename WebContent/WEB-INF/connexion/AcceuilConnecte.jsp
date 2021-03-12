<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

		<title>Accueil Connecté</title>
	</head>
	<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  		<a class="navbar-brand" href="<%=request.getContextPath()%>/AcceuilConnecte">ENI-Encheres</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>
  		<div class="collapse navbar-collapse" id="navbarText">
    		<ul class="navbar-nav mr-auto">
     			<li class="nav-item active">
        			<a class="nav-link" href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageListeEncheresConnecte.html">Enchères  <span class="sr-only">(current)</span></a>
      			</li>
      			<li class="nav-item">
        			<a class="nav-link" href="<%=request.getContextPath()%>/VenteArticle">Vendre un article</a>
      			</li>
				<li class="nav-item">
        			<a class="nav-link" href="<%=request.getContextPath()%>/AfficherProfil">Mon profil</a>
      			</li>
				<li class="nav-item">
        			<a class="nav-link" href="<%=request.getContextPath()%>/NonConnecte">Déconnexion</a>
      			</li>
    		</ul>
    		<span class="navbar-text">
      		Bienvenue sur le site enchère v1.0
    		</span>
  		</div>
	</nav>


	
	    <h1 class="text-center">Liste des enchères</h1>
		<br>
		<br>
		<div class="col-auto">
			<form class="row g-3 justify">
				<div class="col-md-5">
					<label class="col-md-5 row" for="site-search" >Filtres :</label><br>
					<div class="plaintext col-auto">
						<input class="form-control row" placeholder="Le nom de l'article contient" type="search" id="site-search" name="recherche" aria-label="Le nom de l'article contient">
					</div>
					<div class="col-md-5">
						<button class="col-mb-5 btn btn-primary mb-3 row">Rechercher</button>
					</div>
				</div>
			</form>	
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
	        <form action="" method="get" class="achatvente">
		       	<fieldset>
					<legend>
		                 <input type="radio" id="achat" name="achat" value="achat" checked> Achats :
		            </legend>
						<fieldset>
							<div>
							   <input type="checkbox" id="ouvertes" name="ouvertes" value="Encheres ouvertes" checked>
							   <label for="ouvertes">Enchères ouvertes</label>
							 </div>
							 <div>
							   <input type="checkbox" id="encheres" name="encheres" value="Mes encheres">
							   <label for="encheres">Mes enchères</label>
							 </div>
							 <div>
							   <input type="checkbox" id="remportees" name="remportees" value="Mes encheres remportées">
							   <label for="remportees">Mes encheres remportées</label>
							 </div>
				      	</fieldset>
		         	<legend>
		                  <input type="radio" id="ventes" name="ventes" value="Ventes"> Mes ventes :
		            </legend>
		       			<fieldset>
				            <div>
				                <input type="checkbox" id="encours" name="encours" value="Mes ventes en cours">
				                <label for="encours">mes ventes en cours</label>
				            </div>
				            <div>
				                <input type="checkbox" id="nondebutees" name="nondebutees" value="Ventes non débutées">
				                <label for="nondebutees">ventes non débutées</label>
				            </div>
				            <div>
				                <input type="checkbox" id="terminees" name="terminees" value="Ventes terminées">
				                <label for="terminees">ventes terminées</label>
				            </div>
		        		</fieldset>
		        </fieldset>
		    </form>
	        <br>
	        
	        <div>
	            <img src="../WebContent/images/FUJITSU.jpg.png"    alt="Exemple de produit - #">
	            <div>
	                <h4>PIOU PIOU <a href="/Eclipse/Enchere-Eni/WebContent/Page Vente/pageEncherir.html"></a></h4>
	                <p> Prix : tbd </p>
	                <p> Fin de l'enchère : JJ/MM/AAAA </p>
	                <p> Vendeur : <a href="/Eclipse/Enchere-Eni/WebContent/Page Connexion/pageProfil.html">JOJO44</a></p>
	            </div>
	        </div>

		<footer class="text-center">To be determined</footer>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>