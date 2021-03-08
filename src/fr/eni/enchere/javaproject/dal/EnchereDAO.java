package fr.eni.enchere.javaproject.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Enchere;
import fr.eni.enchere.javaproject.utils.BusinessException;

public interface EnchereDAO extends GeneriqueDAO<Enchere> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Enchere enchere) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enchere selectId(int id) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Enchere> selectAll() throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Enchere enchere, int id) throws BusinessException, SQLException;

	/**
	 * Méthode en charge de selectionner toute les encheres faites sur un article
	 * 
	 * @param noArticle numero de l'article
	 * @return une liste d'enchere filtré
	 * @throws BusinessException
	 */
	public List<Enchere> selectHistoriqueArticle(int noArticle) throws BusinessException;

	/**
	 * Méthode en charge de selectionner toute les encheres faites sur un article
	 * dans l'ordre decroissant du montant des encheres
	 * 
	 * @param noArticle numero de l'article
	 * @return une liste d'enchere filtré
	 * @throws BusinessException
	 */
	public List<Enchere> selectHistoriqueArticleDecroissant(int noArticle) throws BusinessException;
}