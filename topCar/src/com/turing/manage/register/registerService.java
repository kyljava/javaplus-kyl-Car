package com.turing.manage.register;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

import com.turing.dao.Dao;
import com.turing.dao.DaoImpl;


public class registerService {

	
	Dao dao = new DaoImpl();
	
	/**
	 * desc 5.添加数据
	 * 
	 * @param manager_name
	 * @param manager_pass
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void save(String admin_name, String admin_pass,String virtualPath) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		String sql = " insert into admin values(?,?,?,?) ";
		
		int [] types = new int[4];
		types[0] = Types.VARCHAR;
		types[1] = Types.VARCHAR;
		types[2] = Types.VARCHAR;
		types[3] = Types.VARCHAR;
		
		Object [] values = new Object[4];
		values[0] = UUID.randomUUID().toString();
		values [1] = virtualPath;
		values [2] = admin_name;
		values [3] = admin_pass;
		
		dao.executeUpdate(sql, types, values);
	}



}
