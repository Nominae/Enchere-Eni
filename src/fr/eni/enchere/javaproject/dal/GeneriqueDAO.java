package fr.eni.enchere.javaproject.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.javaproject.utils.BusinessException;

/**
 * Classe en charge
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 14 janv. 2021 - 13:38:23
 * @param <T>
 */
public interface GeneriqueDAO<T> {

	/**
	 * Méthode en charge de supprimer en bd un objet
	 * 
	 * @param id de l'objet à supprimer
	 * @throws BusinessException
	 */
	public void delete(int id) throws BusinessException;

	/**
	 * Méthode en charge d'inserer en bd un objet
	 * 
	 * @param l'objet
	 * @throws BusinessException
	 */
	public void insert(T t) throws BusinessException;

	/**
	 * Méthode en charge de selectionner un objet
	 * 
	 * @param id de l'objet
	 * @return l'objet
	 * @throws BusinessException
	 */
	public T selectId(int id) throws BusinessException;

	/**
	 * Méthode en charge de selectionner tout les objets en bd
	 * 
	 * @return la liste d'objet
	 * @throws BusinessException
	 */
	public List<T> selectAll() throws BusinessException;

	/**
	 * Méthode en charge d'update un objet
	 * 
	 * @param    l'objet à update
	 * @param id de l'objet
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void update(T t, int id) throws BusinessException, SQLException;
}