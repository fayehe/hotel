package com.faye.utils;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	//初始化连接池
	private static DataSource dataSource;
	public static DataSource getDataSource() {
		return dataSource;
	}


	static {
		dataSource = new ComboPooledDataSource();
	}
	
	
	/**
	 * 创建DbUtils工具类
	 */
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource);
	}
}
