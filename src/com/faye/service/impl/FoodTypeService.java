package com.faye.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.faye.dao.IFoodTypeDao;
import com.faye.dao.impl.FoodTypeDao;
import com.faye.entity.FoodType;
import com.faye.factory.BeanFactory;
import com.faye.service.IFoodTypeService;

public class FoodTypeService implements IFoodTypeService{
	//对象的创建不能写死
	/**
	 * 判断一个类是否为单例——这个类中不含有全局变量
	 */
	private IFoodTypeDao foodTypeDao = BeanFactory.GetInstance("foodTypeDao", FoodTypeDao.class); 
	
	@Override
	public void save(FoodType foodType) throws SQLException {
		foodTypeDao.save(foodType);
	}

	@Override
	public void delete(int id) throws SQLException {
		foodTypeDao.delete(id);
	}

	@Override
	public void update(FoodType foodType) throws SQLException {
		foodTypeDao.update(foodType);
		
	}

	@Override
	public FoodType findById(int id) throws SQLException {
		return foodTypeDao.findById(id);
	}

	@Override
	public List<FoodType> getAll() throws SQLException {
		return foodTypeDao.getAll();
	}

	@Override
	public List<FoodType> getAll(String typeName) throws SQLException {
		return foodTypeDao.getAll(typeName);
	}

}
