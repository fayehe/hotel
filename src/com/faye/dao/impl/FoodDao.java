package com.faye.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.faye.dao.IFoodDao;
import com.faye.entity.Food;
import com.faye.utils.JdbcUtils;

public class FoodDao implements IFoodDao  {

	@Override
	public void save(Food food) throws SQLException {
		String sql = "insert into food(foodName, foodType_id, price, mprice, remark, img) values(?, ?, ?, ?, ?, ?)";
		JdbcUtils.getQueryRunner().update(sql, food.getFoodName(), food.getFoodType_id(), food.getPrice(), food.getMprice(), food.getRemark(), food.getImg());
	}

	@Override
	public void delete(int id) throws SQLException  {
		String sql = "delete from food where id=?";
		JdbcUtils.getQueryRunner().update(sql, id);
	}

	@Override
	public void update(Food food) throws SQLException{
		String sql = "update food set foodName=?, foodType_id=?, price=?, mprice=?, remark=?, img=? where id=?";
		JdbcUtils.getQueryRunner().update(sql, food.getFoodName(), food.getFoodType_id(), food.getPrice(), food.getMprice(), food.getRemark(), food.getImg(), food.getId());
	}

	@Override
	public Food findById(int id) throws SQLException{
		String sql = "select * from food where id=?";
		return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Food>(Food.class), id);
	}

	@Override
	public List<Food> getAll() throws SQLException{
		String sql = "select * from food";
		return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class));
	}

	@Override
	public List<Food> getAll(String foodName) throws SQLException{
		String sql = "select * from food where foodName like ?";
		return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class), "%" + foodName+ "%");
	}

}
