package com.turing.user.register;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

import com.turing.dao.Dao;
import com.turing.dao.DaoImpl;
import com.turing.utils.Dates;

public class registerService {
	Dao  dao=new DaoImpl();
	public void save(String user_name, String user_password, String user_sex, String user_age, String user_adress) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql="insert into user values(?,?,?,?,?,?)";
		
		 int []types=new int[6];
		 types[0]=Types.VARCHAR;
		 types[1]=Types.VARCHAR;
		 types[2]=Types.VARCHAR;
		 types[3]=Types.VARCHAR;
		 types[4]=Types.VARCHAR;
		 types[5]=Types.VARCHAR;
		 
		 Object []values=new Object[6];
		 values[0]=UUID.randomUUID().toString();
		 values[1]=user_name;
		 values[2]=user_password;
		 values[3]=user_sex;
		 values[4]=user_age;
		 values[5]=user_adress;
		 
		dao.executeUpdate(sql, types, values);
		
	}

}
