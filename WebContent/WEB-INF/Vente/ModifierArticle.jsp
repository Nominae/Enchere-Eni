+<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Modifier une vente</title>
</head>
<body>
<main>
<div>
<form action="<c:url value =" ServletModifierArticle "/>" method="post">
	  <div>
	    <div>
	      <label for="article">Article</label>
	      <input type="text" name="article" value="${article.nom_article}">
	    </div>
     </div>
	  <div>
	    <div>
	      <label for="description">Description</label>
	      <textarea id="description" name="description" rows="4" cols="60">${article.description}</textarea>
	    </div>
     </div>
     <div>
	<label for="categorie">Categorie : </label>
	<SELECT name="categorie">
	<OPTION value="1" ${article.no_categorie =='1'? 'selected="true"' : '' }>Informatique </OPTION>
	<OPTION value="2" ${article.no_categorie =='2'? 'selected="true"' : '' } >Ameublement </OPTION>
	<OPTION value="3" ${article.no_categorie =='3'? 'selected="true"' : '' }>Vetement </OPTION>
	<OPTION value="4" ${article.no_categorie =='4'? 'selected="true"' : '' }>Sport&Loisirs </OPTION>
	</SELECT>
	</div> 
  		 <div >
	    <div >
	      <label for="photo">Mise à prix</label>
	      <input type="number" id="prixInitial" name="prixInitial" min="0" value="${article.prix_initial}">
	    </div>
     </div>
     <div>
        <label for="debutEnchere">Début de l'enchère</label>
        <div>
            <input type="date" id="debutEnchere" name="debutEnchere" value="${article.date_debut_encheres}" required/>
        </div>
    </div>
     <div>
        <label  for="finEnchere">Fin de l'enchère</label>
        <div>
            <input type="date" id="finEnchere" name="finEnchere" value="${article.date_fin_encheres}" required/>
        </div>
    </div>
<fieldset>
    <legend>Retrait</legend>
 	<div>
	    <div>
	      <label for="rue">Rue</label>
	      <input type="text" name="rue" value="${ retrait.rue }">
	    </div>
     </div>
     <div >
	    <div >
	      <label for="codePostal">Code postal</label>
	      <input type="text" name="codePostal" value="${ retrait.code_postal }">
	    </div>
     </div>
          <div>
	    <div>
	      <label for="ville">Ville</label>
	      <input type="text"  name="ville" value="${ retrait.ville }">
	    </div>
     </div>
     <input type="hidden" name="idArticle" value="${article.no_article}">
</fieldset>
     <div>
     <input type="hidden" name="numeroUtilisateur" value="${ utilisateur.no_utilisateur }">
		  <button type="submit">Enregistrer</button>
		  <button type="reset" >Annuler</button>
	   </div>
</form>
</div>
</main>
</body>
</html>