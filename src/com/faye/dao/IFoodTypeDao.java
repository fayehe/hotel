package com.faye.dao;

import java.sql.SQLException;
import java.util.List;

import com.faye.entity.FoodType;

/**
 * food type interface
 * @author Faye
 *
 */
public interface IFoodTypeDao  {
	void save (FoodType foodType) throws SQLException;
	void delete(int id) throws SQLException;
	void update(FoodType foodType) throws SQLException;
	FoodType findById(int id) throws SQLException;
	List<FoodType> getAll() throws SQLException;
	List<FoodType> getAll(String typeName) throws SQLException;
}
