package com.faye.dao;

import java.sql.SQLException;
import java.util.List;

import com.faye.entity.Food;

public interface IFoodDao {
	void save (Food food) throws SQLException;
	void delete(int id) throws SQLException;
	void update(Food food) throws SQLException;
	Food findById(int id) throws SQLException;
	List<Food> getAll() throws SQLException;
	List<Food> getAll(String typeName) throws SQLException;
}
