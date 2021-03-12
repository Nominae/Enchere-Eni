package fr.eni.enchere.javaproject.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.javaproject.utils.BusinessException;


public interface GeneriqueDAO<T> {

	/**
	 * M�thode en charge de supprimer en bd un objet
	 */
	public void delete(int id) throws BusinessException;

	/**
	 * M�thode en charge d'inserer en bd un objet
	 */
	public void insert(T t) throws BusinessException;

	/**
	 * M�thode en charge de selectionner un objet
	 */
	public T selectId(int id) throws BusinessException;

	/**
	 * M�thode en charge de selectionner tout les objets en bd
	 */
	public List<T> selectAll() throws BusinessException;

	/**
	 * M�thode en charge d'update un objet
	 */
	public void update(T t, int id) throws BusinessException, SQLException;
}