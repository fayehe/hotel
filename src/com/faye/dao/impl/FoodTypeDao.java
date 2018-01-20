package com.faye.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.faye.dao.IFoodTypeDao;
import com.faye.entity.FoodType;
import com.faye.utils.JdbcUtils;

public class FoodTypeDao implements IFoodTypeDao  {

	@Override
	public void save(FoodType foodType) throws SQLException {
		String sql = "insert into foodtype(typeName) values(?)";
		JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName());
	}

	@Override
	public void delete(int id) throws SQLException  {
		String sql = "delete from foodtype where id=?";
		JdbcUtils.getQueryRunner().update(sql, id);
	}

	@Override
	public void update(FoodType foodType) throws SQLException{
		String sql = "update foodtype set typeName=? where id=?";
		JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName(), foodType.getId());
	}

	@Override
	public FoodType findById(int id) throws SQLException{
		String sql = "select * from foodtype where id=?";
		return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<FoodType>(FoodType.class), id);
	}

	@Override
	public List<FoodType> getAll() throws SQLException{
		String sql = "select * from foodtype";
		return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
	}

	@Override
	public List<FoodType> getAll(String typeName) throws SQLException{
		String sql = "select * from foodtype where typeName like ?";
		return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class), "%" + typeName+ "%");
	}

}
