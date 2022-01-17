package com.turing.user.login;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import com.turing.dao.Dao;
import com.turing.dao.DaoImpl;

public class LogoService {

	Dao dao = new DaoImpl();
	
	public Map<String, Object> queryByNameAndPass(String name, String pass) throws ClassNotFoundException, SQLException {
		String sql = " select * from user where user_name=? and user_password=? ";
		// 1.给sql语句中的?指定数据类型
		int[] types = new int[2];
		types[0] = Types.VARCHAR;
		types[1] = Types.VARCHAR;

		// 2.给sql语句中的?指定具体的值
		Object[] values = new Object[2];
		values[0] = name;
		values[1] = pass;

		return dao.executeQueryForMap(sql, types, values);
	}
	
}
