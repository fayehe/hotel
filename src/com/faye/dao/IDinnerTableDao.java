package com.faye.dao;

import java.sql.SQLException;
import java.util.List;

import com.faye.entity.DinnerTable;

/**
 * dinner table interface
 * @author Faye
 *
 */
public interface IDinnerTableDao  {
	void save (DinnerTable dinnerTable) throws SQLException;
	void delete(int id) throws SQLException;
	void update(DinnerTable dinnerTable) throws SQLException;
	DinnerTable findById(int id) throws SQLException;
	List<DinnerTable> getAll() throws SQLException;
	List<DinnerTable> getAll(String tableName) throws SQLException;
}
