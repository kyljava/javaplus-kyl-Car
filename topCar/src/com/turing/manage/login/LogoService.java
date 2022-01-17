package com.turing.manage.login;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import com.turing.dao.DaoImpl;

public class LogoService {
	DaoImpl dao=new DaoImpl();

	public Map<String, Object> quearLogo(String name, String pass) throws ClassNotFoundException, SQLException {
		String sql = "select  * from admin  where  admin_name=? and admin_password=?";
		// 给sql中的name pass 指定类型 预编译
		int[] types = new int[2];
		types[0] = Types.VARCHAR;
		types[1] = Types.VARCHAR;

		Object[] values = new Object[2];
		values[0] = name;
		values[1] = pass;
//		return dao.executeQueryForMap("select  * from manager  where  manager_name='"+name+"' and  manager_pass='"+pass+"'");
		return dao.executeQueryForMap(sql, types, values);
	}
}
