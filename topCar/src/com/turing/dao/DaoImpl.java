package com.turing.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 数据库连接对象
 * @author 	wyh
 * @time	2019-05-11
 */
public class DaoImpl implements Dao{
	public String imgName=null;
	
	
	private String sDBDriver = "com.mysql.jdbc.Driver";
	private String sConnStr = "jdbc:mysql://localhost:3306/SECOND-hand-car?useUnicode=true&characterEncoding=utf-8";
    private String username = "root";  //登录数据库用户名
    private String password = "123456";   //登录数据库密码

    
    /**
     * 建立连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException{
    	
		 Class.forName(sDBDriver); //指定JDBC数据库驱动程序

		 return DriverManager.getConnection(sConnStr,username,password);
    }


	/**
     * 根据sql查询列表数据(查询一条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public Map<String, Object> executeQueryForMap(String sql)throws ClassNotFoundException, SQLException {
		System.err.println("查询一条："+sql);
    	Connection connect =this.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Map<String, Object>> list = this.rsToList(rs);
		System.out.println(list);
		if( !list.isEmpty() ){
			Map<String, Object> imgPath=list.get(0);
			imgName=(String) imgPath.get("manager_imgpath");
			System.out.println(imgName);
			return list.get(0);
		}
		this.releaseConnection(rs, stmt, connect);//关闭连接
		return null;
	}

	/**
	 * 根据sql查询列表数据(查询一条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param values
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Map<String, Object> executeQueryForMap(String sql, int[] types,Object[] values) throws ClassNotFoundException, SQLException {
		System.err.println("查询一条："+sql);
		this.print(values);
		Connection connect = this.getConnection();
		PreparedStatement pst =  connect.prepareStatement(sql);
		
		if( types != null ){
			for(int i=0;i<types.length;i++){
				switch( types[i] ){
				case Types.VARCHAR:
					pst.setString(i+1, String.valueOf( values[i] ) );
					break;
				case Types.INTEGER:
					pst.setInt(i+1, Integer.parseInt( String.valueOf( values[i] ) ));
					System.out.println("跳转失败");
					break;
				}
				
			}
		}

		ResultSet rs = pst.executeQuery();
		List<Map<String, Object>> list = this.rsToList(rs);
		if( !list.isEmpty() ){
			return list.get(0);
		}
		this.releaseConnection(rs, pst, connect);
		return null;
	}


	
    /**
     * 根据sql查询列表数据，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Map<String, Object>> executeQueryForList(String sql) throws ClassNotFoundException, SQLException{
    	System.err.println("查询多条："+sql);
    	Connection connect =this.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Map<String, Object>> list = this.rsToList(rs);
		this.releaseConnection(rs, stmt, connect);//关闭连接
		System.out.println(list);
		return list;
	}

    /**
     * 执行 增、删、改、等的操作，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public int executeUpdate(String sql) throws ClassNotFoundException, SQLException {
		System.err.println("更新："+sql);
		Connection connect=this.getConnection();
		Statement stmt=connect.createStatement();
		int count=stmt.executeUpdate(sql);

		this.releaseConnection(stmt, connect);//关闭连接
	
		return count;
	}
	
	
	/**
	 * 根据sql查询列表数据，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param values
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Map<String, Object>> executeQueryForList(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException{
		System.err.println("查询多条："+sql);	
		this.print(values);
		Connection connect = this.getConnection();
			PreparedStatement pst =  connect.prepareStatement(sql);
			
			if( types != null ){
				for(int i=0;i<types.length;i++){
					switch( types[i] ){
					case Types.VARCHAR:
						pst.setString(i+1, String.valueOf( values[i] ) );
						break;
					case Types.INTEGER:
						pst.setInt(i+1, Integer.parseInt( String.valueOf( values[i] ) ));
						break;
					}
					
				}
			}

			ResultSet rs = pst.executeQuery();
			List<Map<String, Object>> list = this.rsToList(rs);
			this.releaseConnection(rs, pst, connect);
			return list;
	}
	/**
	 * 预编译sql操作，   支持insert ， update  ， delete  语句
	 * @param sql
	 * @param types
	 * @param values
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public int executeUpdate(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException, IOException{
		System.err.println("更新："+sql);
		this.print(values);
		Connection connect = this.getConnection();
		PreparedStatement pst =  connect.prepareStatement(sql);
		
		if( types != null ){
			for(int i=0;i<types.length;i++){
				switch( types[i] ){
				case Types.VARCHAR:
					pst.setString(i+1, String.valueOf( values[i] ) );
					break;
				case Types.INTEGER:
					pst.setInt(i+1, Integer.parseInt( String.valueOf( values[i] ) ));
					break;
				case Types.BLOB:
					InputStream in = new FileInputStream( (File)values[i] );
					pst.setBinaryStream(i+1, in , in.available()  );
					break;
				}
			}
		}

		int count = pst.executeUpdate();
		this.releaseConnection(pst, connect);
		return count;  
	}
	
	/**
	 * 查询一个整数，例如记录总数（不支持预编译）
	 * @param sql
	 * @param types
	 * @param values
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int executeQueryForInt(String sql) throws ClassNotFoundException, SQLException{
		System.err.println("查询总数："+sql);
		
    	Connection connect =this.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			return rs.getInt(1);
		}
		this.releaseConnection(rs, stmt, connect);
		return 0; 
	}
	
	/**
	 * 查询一个整数，例如记录总数（支持预编译）
	 * @param sql
	 * @param types
	 * @param values
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int executeQueryForInt(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException{
		System.err.println("查询总数："+sql);
		this.print(values);
		Connection connect = this.getConnection();
		PreparedStatement pst =  connect.prepareStatement(sql);
		
		if( types != null ){
			for(int i=0;i<types.length;i++){
				switch( types[i] ){
				case Types.VARCHAR:
					pst.setString(i+1, String.valueOf( values[i] ) );
					break;
				case Types.INTEGER:
					pst.setInt(i+1, Integer.parseInt( String.valueOf( values[i] ) ));
					break;
				}
				
			}
		}

		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		this.releaseConnection(rs, pst, connect);
		return 0; 
	}
	/**
	 * 将ResultSet中的结果包装成list中装Map的结构
	 * @author 		wyh
	 * @time		2019-05-11
	 * @param		 rs
	 * @return
	 * @throws SQLException
	 */
	private List<Map<String, Object>> rsToList( ResultSet rs ) throws SQLException{
		List<Map<String, Object>> row = new ArrayList<Map<String, Object>>();
		 while (rs.next()) {
			 Map<String, Object> col = new HashMap<String, Object>();
			 for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				 //System.out.println(  rs.getMetaData().getColumnType(i)  );
				 switch( rs.getMetaData().getColumnType(i) ){
			 	 case Types.VARCHAR:
			 		col.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			 		break;
			 	 case Types.INTEGER:
				 	col.put(rs.getMetaData().getColumnName(i), rs.getInt(i));
				 	break;	
			 	 case Types.BLOB:
			 		InputStream in = rs.getBinaryStream(i);

				 	col.put(rs.getMetaData().getColumnName(i), in );
				 	break;	
				 default:
					 col.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				 	break;	
				 }
				 
			 }
			row.add(col);
		}
		 return row;
	}
	
	/**
	 * 打印出所有的参数值
	 * @param values
	 */
	private void print(Object[] values){
		if( values == null ) return;
		System.out.println("参数值：\t---------------------");
		for (int i = 0; i < values.length; i++) {
			System.out.println( "\t["+i+"]=["+values[i]+"]" );
		}
		System.out.println("\t---------------------");
	}
	
	
	@SuppressWarnings("unused")
	private void releaseConnection(Connection connect) throws SQLException{
	    try {
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	
	private void releaseConnection(Statement stmt, Connection connect) throws SQLException{
	    try {
	        if (stmt != null){
	        	stmt.close();
	        }
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	private void releaseConnection(PreparedStatement pst, Connection connect) throws SQLException{
	    try {
	        if (pst != null){
	            pst.close();
	        }
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	
	private void releaseConnection(ResultSet rs, Statement stmt, Connection connect) throws SQLException{
	    try {
	        if (rs != null){
	            rs.close();
	        }
	        if (stmt != null){
	        	stmt.close();
	        }
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	private void releaseConnection(ResultSet rs, PreparedStatement pst, Connection connect) throws SQLException{
	    try {
	        if (rs != null){
	            rs.close();
	        }
	        if (pst != null){
	            pst.close();
	        }
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	


		
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		DaoImpl dao = new DaoImpl();
		dao.executeQueryForList("select * from manager");
//		int[] types1 = {Types.VARCHAR};
//		String[] objValues1 = {"%"};
//		
//		
//		List<Map<String, Object>> list = dao.executeQueryForList("select * from  jobs where job_desc like ?", types1, objValues1);
//		
//		System.out.println("list.size()==="+list.size());
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(  list.get(i) );
//		}
///*		
		
//		int[] types2 = {Types.INTEGER,Types.VARCHAR,Types.INTEGER};
//		String[] objValues2 = {"50","%","10"};
//		
//		int p = dao.executeUpdate("update jobs set max_lvl = ?  where job_desc like ? and min_lvl = ?", types2, objValues2);
//		
//		System.out.println( "p==="+p );*/
	}

	
}
