package com.turing.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/************************************
 * 数据库连接对象
 * @author 	wyh
 * @time	2019-05-11
 ************************************/
public interface Dao {

	/**
     * 根据sql查询列表数据(查询一条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Map<String, Object> executeQueryForMap(String sql) throws ClassNotFoundException, SQLException;
	/**
	 * 根据sql查询列表数据(查询一条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Map<String, Object> executeQueryForMap(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException ;
    
	  /**
     * 根据sql查询列表数据(查询多条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Map<String, Object>> executeQueryForList(String sql) throws ClassNotFoundException, SQLException;
	/**
	 * 根据sql查询列表数据(查询多条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Map<String, Object>> executeQueryForList(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException ;
    
	

    /**
     * 执行 增、删、改、等的操作，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public int executeUpdate(String sql) throws ClassNotFoundException, SQLException ;
	/**
	 * 预编译sql操作，   支持insert ， update  ， delete  语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public int executeUpdate(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException ;
	
	/**
	 * 预编译sql操作，   支持  select 语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int executeQueryForInt(String sql ) throws ClassNotFoundException, SQLException ;
	/**
	 * 预编译sql操作，   支持  select 语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int executeQueryForInt(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException ;

}
