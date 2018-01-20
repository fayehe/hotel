package com.faye.service;

import java.sql.SQLException;
import java.util.List;

import com.faye.entity.FoodType;

public interface IFoodTypeService {
	void save (FoodType foodType) throws SQLException;
	void delete(int id) throws SQLException;
	void update(FoodType foodType) throws SQLException;
	FoodType findById(int id) throws SQLException;
	List<FoodType> getAll() throws SQLException;
	List<FoodType> getAll(String typeName) throws SQLException;
}
