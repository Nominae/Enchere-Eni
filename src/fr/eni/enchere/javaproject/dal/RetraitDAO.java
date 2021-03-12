package fr.eni.enchere.javaproject.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.utils.BusinessException;


public interface RetraitDAO {
	
	
	public Retrait insert(Retrait retrait) throws BusinessException;
	public Retrait selectId(int id) throws BusinessException;
	public void update(Retrait retrait, int id) throws BusinessException, SQLException;
	public void delete(int id) throws BusinessException;
	public List<Retrait> selectAll() throws BusinessException;


}
