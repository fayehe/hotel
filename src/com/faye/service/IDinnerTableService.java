package com.faye.service;

import java.sql.SQLException;
import java.util.List;

import com.faye.entity.DinnerTable;

public interface IDinnerTableService {
	void save (DinnerTable dinnerTable) throws SQLException;
	void delete(int id) throws SQLException;
	void update(DinnerTable dinnerTable) throws SQLException;
	DinnerTable findById(int id) throws SQLException;
	List<DinnerTable> getAll() throws SQLException;
	List<DinnerTable> getAll(String tableName) throws SQLException;
}
