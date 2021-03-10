package fr.eni.enchere.javaproject.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Article;
import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.utils.BusinessException;




	public interface ArticleDAO extends GeneriqueDAO<Article> {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void delete(int id) throws BusinessException;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void insert(Article t) throws BusinessException;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Article selectId(int id) throws BusinessException;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public List<Article> selectAll() throws BusinessException;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void update(Article t, int id) throws BusinessException, SQLException;

	
		public List<Article> selectCategorie(int noCategorie) throws BusinessException;

	
		public List<Article> selectRechercher(String rechercher) throws BusinessException;

	
		public List<Article> selectCategorieRechercher(String rechercher, int noCategorie) throws BusinessException;

	
		public List<Article> selectAchatAll(int noUtilisateur) throws BusinessException;

		
		public void updatePrixVente(int prixDeVente, int id) throws BusinessException, SQLException;

		
		public List<Article> selectAchatEnchereEnCour(int noUtilisateur) throws BusinessException;

	
		public List<Article> selectAchatEnchereRemporte(int noUtilisateur) throws BusinessException;

		
		public List<Article> selectVenteAll(int noUtilisateur) throws BusinessException;

		
		public List<Article> selectVenteTermine(int noUtilisateur) throws BusinessException;

		
		public List<Article> selectVenteEnCour(int noUtilisateur) throws BusinessException;

	
		//public List<Article> selectVenteNonDebute(int noUtilisateur) throws BusinessException;

		
		public void insertAvecCheminImg(Article article) throws BusinessException;
	}

