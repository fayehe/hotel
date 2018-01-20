package com.faye.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.faye.dao.IDinnerTableDao;
import com.faye.entity.DinnerTable;
import com.faye.utils.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao{

	@Override
	public void save(DinnerTable dinnerTable) throws SQLException {
		String sql = "insert into dinnertable(tableName) values(?)";
		JdbcUtils.getQueryRunner().update(sql, dinnerTable.getTableName());
	}

	@Override
	public void delete(int id) throws SQLException  {
		String sql = "delete from dinnertable where id=?";
		JdbcUtils.getQueryRunner().update(sql, id);
	}

	@Override
	public void update(DinnerTable dinnerTable) throws SQLException{
		String sql = "update dinnertable set tableStatus=?, orderDate=? where id=?";
		JdbcUtils.getQueryRunner().update(sql, dinnerTable.getTableStatus(), dinnerTable.getOrderDate(), dinnerTable.getId());
	}

	@Override
	public DinnerTable findById(int id) throws SQLException{
		String sql = "select * from dinnertable where id=?";
		return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
	}

	@Override
	public List<DinnerTable> getAll() throws SQLException{
		String sql = "select * from dinnertable";
		return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
	}

	@Override
	public List<DinnerTable> getAll(String tableName) throws SQLException{
		String sql = "select * from dinnertable where tableName like ?";
		return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), "%" + tableName + "%");
	}
}
