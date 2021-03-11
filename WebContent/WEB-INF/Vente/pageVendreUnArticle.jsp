<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Vendre un article</title>

</head>
<body>
<h1>Nouvelle vente</h1>
<main >

<div >
<form  action="<%=request.getContextPath()%>/VenteArticle" method="post"> 
	  <div >
	    <div >
	      <label for="article">Article</label>
	      <input type="text"  name="article" placeholder="article">
	    </div>
     </div>
	  <div >
	    <div >
	      <label for="description">Description</label>
	      <textarea id="description" name="description" rows="4" cols="60"></textarea>
	    </div>
     </div>
     <div >
	<label for="categorie">Categorie : </label>
	<SELECT name="categorie" id="categorie">
		<c:forEach var="categories" items="${categories}">
			<OPTION value="${categories.noCategorie}">${categories.libelle}</OPTION>
		</c:forEach>
	</SELECT>
	</div>  
  		 <div >
	    <div >
	      <label for="photo">Mise à prix</label>
	      <input type="number" id="prixInitial" name="prixInitial" min="0" >
	    </div>
     </div>
     <div >
        <label  for="debutEnchere">Début de l'enchère</label>
        <div>
            <input type="date" id="debutEnchere" name="debutEnchere" required/>
        </div>
    </div>
     <div >
        <label  for="finEnchere">Fin de l'enchère</label>
        <div>
            <input type="date" id="finEnchere" name="finEnchere" required/>
        </div>
    </div>
<fieldset>
    <legend>Retrait</legend>
 	<div >
	    <div>
	      <label for="rue">Rue</label>
	      <input type="text"  name="rue" value="${ utilisateur.rue }">
	    </div>
     </div>
     <div >
	    <div >
	      <label for="codePostal">Code postal</label>
	      <input type="text"  name="codePostal" value="${ utilisateur.codePostal }">
	    </div>
     </div>
          <div >
	    <div class="form-group col-md-6">
	      <label for="ville">Ville</label>
	      <input type="text"  name="ville" value="${ utilisateur.ville }">
	    </div>
     </div>
</fieldset>
     <div >
     <input type="hidden" name="numeroUtilisateur" value="${ utilisateur.noUtilisateur }">
		  <button type="submit" >Enregistrer</button>
		  <button type="reset" >Annuler</button>
	   </div>
</form>
</div>
<p> ${ erreurs } </p>
</main>
</body>
</html>