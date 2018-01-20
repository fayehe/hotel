package com.faye.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.faye.dao.IFoodDao;
import com.faye.dao.impl.FoodDao;
import com.faye.entity.Food;
import com.faye.factory.BeanFactory;
import com.faye.service.IFoodService;

public class FoodService implements IFoodService{
	
	private IFoodDao foodDao = BeanFactory.GetInstance("foodDao", FoodDao.class); 
	
	@Override
	public void save(Food food) throws SQLException {
		foodDao.save(food);
	}

	@Override
	public void delete(int id) throws SQLException {
		foodDao.delete(id);
	}

	@Override
	public void update(Food food) throws SQLException {
		foodDao.update(food);
		
	}

	@Override
	public Food findById(int id) throws SQLException {
		return foodDao.findById(id);
	}

	@Override
	public List<Food> getAll() throws SQLException {
		return foodDao.getAll();
	}

	@Override
	public List<Food> getAll(String typeName) throws SQLException {
		return foodDao.getAll(typeName);
	}

}
