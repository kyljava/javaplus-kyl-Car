package com.turing.utils;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.turing.dao.Dao;
import com.turing.dao.DaoImpl;

/**********************************************
 * 
 * @author wyh
 * @time   2019-2-3上午09:08:31
 **********************************************/
public class BuildCardNo {

	
	/**
	 * 计算准考证号
	 * @param classs_id		班级ID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static String buildCardNo(String classs_id) throws ClassNotFoundException, SQLException, ParseException{
		String newCardNo = "";
			
		Dao dao = new DaoImpl();
		Map<String,Object> classs = dao.executeQueryForMap("select * from classs where classs_id='"+classs_id+"'");
		String classs_time = (String)classs.get("classs_time");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date rtndate = sdf.parse(classs_time);
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String newDate = df.format(rtndate);
		
		Map<String,Object> examinee = dao.executeQueryForMap("select * from examinee where examinee_id like '"+newDate+"%' order by examinee_id desc limit 0,1");
		
		if( examinee == null ){
			newCardNo = newDate+"001";
		}else{
			String examinee_id = (String)examinee.get("examinee_id");
			String no = examinee_id.substring(examinee_id.length()-3);

			int i = Integer.parseInt(no)+1;

			for (int j = 0; j < 3-String.valueOf(i).length(); j++) {
				newDate = newDate + "0";
			}
			newDate = newDate + i;

			newCardNo = newDate;
		}
		

		return newCardNo;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		String newCardNo = BuildCardNo.buildCardNo("c3");
		
		System.out.println( newCardNo );
	}
}
