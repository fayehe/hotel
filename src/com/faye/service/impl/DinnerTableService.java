package com.faye.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.faye.dao.IDinnerTableDao;
import com.faye.dao.impl.DinnerTableDao;
import com.faye.entity.DinnerTable;
import com.faye.factory.BeanFactory;
import com.faye.service.IDinnerTableService;

public class DinnerTableService implements IDinnerTableService{
	
	private IDinnerTableDao dinnerTableDao = BeanFactory.GetInstance("dinnerTableDao", DinnerTableDao.class);
	
	@Override
	public void save(DinnerTable dinnerTable) throws SQLException {
		dinnerTableDao.save(dinnerTable);		
	}

	@Override
	public void delete(int id) throws SQLException {
		dinnerTableDao.delete(id);		
	}

	@Override
	public void update(DinnerTable dinnerTable) throws SQLException {
		dinnerTableDao.update(dinnerTable);		
	}

	@Override
	public DinnerTable findById(int id) throws SQLException {
		return dinnerTableDao.findById(id);
	}

	@Override
	public List<DinnerTable> getAll() throws SQLException {
		return dinnerTableDao.getAll();
	}

	@Override
	public List<DinnerTable> getAll(String tableName) throws SQLException {
		return dinnerTableDao.getAll(tableName);
	}

}
