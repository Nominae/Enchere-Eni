<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Vendre un article</title>

</head>
<body>
<nav>
	<a href="<%=request.getContextPath()%>/AcceuilConnecte">ENI-Encheres</a>
</nav>	
<h1>Nouvelle vente</h1>
<main class="container">
<div class=" container-fluid text-center">
	<img class="container col-md-6" alt="Photo de l'article mis en vente." width="255" height="200"  src="https://media.giphy.com/media/l2JdUrmFPxNZZiWYM/giphy.gif">
</div>
<div class="container col-md-6">
<form class="container form-horizontal" action="<%=request.getContextPath()%>/VenteArticle" method="post"> 
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="article">Article</label>
	      <input type="text" class="form-control" name="article" placeholder="article">
	    </div>
     </div>
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="description">Description</label>
	      <textarea id="description" name="description" rows="4" cols="60"></textarea>
	    </div>
     </div>
     <div class="mr-sm-3">
	<label for="categorie">Categorie : </label>
	<select name="categorie">
		<c:forEach var="categories" items="${categories}">
			<option value="${categories.noCategorie}">${categories.libelle}</option>
		</c:forEach>
	</select>
	</div>  
	 <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="photo">Photo de l'article</label>
	     <input  type="file"  name="photo" value="uploader" accept="image/png, image/jpeg">
	    </div>
     </div>
  		 <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="prixInitial">Mise à prix</label>
	      <input type="number" id="prixInitial" name="prixInitial" min="0" >
	    </div>
     </div>
     <div class="control-group">
        <label class="control-label input-label" for="debutEnchere">Début de l'enchère</label>
        <div>
            <input type="date" id="debutEnchere" name="debutEnchere" required/>
            <i class="icon-time"></i>
        </div>
    </div>
     <div class="control-group">
        <label class="control-label input-label" for="finEnchere">Fin de l'enchère</label>
        <div>
            <input type="date" id="finEnchere" name="finEnchere" required/>
            <i class="icon-time"></i>
        </div>
    </div>
<fieldset>
    <legend>Retrait</legend>
 	<div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="rue">Rue</label>
	      <input type="text" class="form-control" name="rue" value="${ utilisateurs.rue }">
	    </div>
     </div>
     <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="codePostal">Code postal</label>
	      <input type="text" class="form-control" name="codePostal" value="${ utilisateurs.codePostal }">
	    </div>
     </div>
          <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="ville">Ville</label>
	      <input type="text" class="form-control" name="ville" value="${ utilisateurs.ville }">
	    </div>
     </div>
</fieldset>
     <div class="form-row align-self-center">
     <input type="hidden" name="numeroUtilisateur" value="${ utilisateur.noUtilisateur }">
		  <button type="submit" class="btn btn-primary">Enregistrer</button>
		  <button type="reset" class="btn btn-primary"><a href="<%=request.getContextPath()%>/AcceuilConnecte">Annuler</a></button>
	   </div>
</form>
</div>
<p> ${ erreurs } </p>
</main>
</body>
</html>-->