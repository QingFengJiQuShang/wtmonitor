package smart.sys.platform.dao;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;

import smart.sys.platform.log.Log4jHelper;
import smart.sys.platform.springUtils.SpringBeanUtil;

public class DBEntity {
	private static Log4jHelper log = new Log4jHelper(DBEntity.class);
	private HibernateTemplate hibernateTemplate;
	private Connection conn;
	private static DBEntity instance;
	
	private DBEntity(){
		hibernateTemplate = (HibernateTemplate)SpringBeanUtil.getBean("hibernateTemplate");
		conn = hibernateTemplate.getSessionFactory().openSession().connection();
	}
	private HibernateTemplate getHibernateTemplate(){
		if(hibernateTemplate==null){
			hibernateTemplate = (HibernateTemplate)SpringBeanUtil.getBean("hibernateTemplate");
		}
		return hibernateTemplate;
	}
	public Connection getConnection() throws SQLException{
		if(conn==null||conn.isClosed()||(!conn.isValid(10))){
			conn = getHibernateTemplate().getSessionFactory().openSession().connection();
			System.out.println("连接已被关闭，重新取得连接");
		}
		return conn; 
	}
	
	public static DBEntity getInstance() {
		if(instance==null){
			instance = new DBEntity();
			log.info("\u65b0\u5efaDBEntity");	//新建DBEntity
		}
		return instance;
	}
	/**
	 * 查询sql 返回以列名(大写)为key的Map记录集
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public synchronized List<Map<String,Object>> queryBySql(String sql) throws Exception{
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		System.out.println("DBEntity将执行queryBySql(sql)==>"+sql);
		Statement stat = getConnection().createStatement();
		ResultSet set = stat.executeQuery(sql);
		
		ResultSetMetaData metaData = set.getMetaData();
		while(set.next()){
			Map<String,Object> map = new HashMap<String,Object>();
			for(int i=1;i<=metaData.getColumnCount();i++){
				String columnName = metaData.getColumnName(i);
				map.put(columnName, set.getObject(columnName));
			}
			dataList.add(map);
		}
		stat.close();
		return dataList;
	}
	
	/**
	 * 查询单列数据
	 * @param sql
	 * @param type	1:数字类型,2:字符类型(带单引号)
	 * @return 返回数据以逗号隔开
	 * @throws Exception
	 */
	public synchronized String querySingleColumn(String sql,int type) throws Exception{
		String str="";
		System.out.println("DBEntity将执行querySingleColumn(sql,type)==>"+sql);
		Statement stat = getConnection().createStatement();
		ResultSet set = stat.executeQuery(sql);
		
		ResultSetMetaData metaData = set.getMetaData();
		while(set.next()){
			for(int i=1;i<=metaData.getColumnCount();i++){
				String columnName = metaData.getColumnName(i);
				if(set.getObject(columnName)!=null){
					if(type==1){//数字类型
						str+=","+set.getObject(columnName);
					}
					if(type==2){//字符类型
						str+=","+"'"+set.getObject(columnName)+"'";
					}
					if(type==3){//字符类型
						str+=","+set.getObject(columnName);
					}
				}
			}
		}
		if(str!=null&&!"".equals(str)){
			str=str.substring(1);
		}
		stat.close();
		return str;
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public synchronized List<Object> querySingleColumn(String sql) throws Exception{
		List<Object> dataList = new ArrayList<Object>();
		System.out.println("DBEntity将执行querySingleColumn(sql)==>"+sql);
		Statement stat = getConnection().createStatement();
		ResultSet set = stat.executeQuery(sql);
		
		while(set.next()){
			dataList.add(set.getObject(1));
		}
		stat.close();
		return dataList;
	}
	
	/**
	 * 查询记条录数
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public synchronized int queryDataCount(String sql_count) throws Exception {
		int rs = 0;
		System.out.println("DBEntity将执行queryDataCount(sql_count)==>"+sql_count);
		Statement stat = getConnection().createStatement();
		ResultSet set = stat.executeQuery(sql_count);
		if(set.next()){
			rs = set.getInt(1);
		}
		stat.close();
		return rs;
	}
	
	/**
	 * 查询记条录数
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public synchronized int queryCount(String sql) throws Exception {
		int rs = 0;
		 sql="select count(a.id) from ("+sql+") a ";
		System.out.println("DBEntity将执行queryCount(sql)==>"+sql);
		Statement stat = getConnection().createStatement();
		ResultSet set = stat.executeQuery(sql);
		if(set.next()){
			rs = set.getInt(1);
		}
		stat.close();
		return rs;
	}
	/**
	 * 查询唯一的一个值
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public synchronized Object queryUniqueData(String sql) throws Exception {
		Object rs = null;
		System.out.println("DBEntity将执行queryUniqueData(sql)==>"+sql);
		Statement stat = getConnection().createStatement();
		ResultSet set = stat.executeQuery(sql);
		if(set.next()){
			rs = set.getObject(1);
		}
		stat.close();
		return rs;
	}
	/**
	 * 执行sql返回唯一的一行记录
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public synchronized Map<String,Object> queryUniqueResult(String sql) throws Exception {
		Map<String,Object> rs = new HashMap<String,Object>();
		System.out.println("DBEntity将执行queryUniqueResult(sql)==>"+sql);
		Statement stat = getConnection().createStatement();
		ResultSet set = stat.executeQuery(sql);
		
		ResultSetMetaData metaData = set.getMetaData();
		if(set.next()){
			for(int i=1;i<=metaData.getColumnCount();i++){
				String columnName = metaData.getColumnName(i);
				rs.put(columnName, set.getObject(columnName));
			}
		}
		stat.close();
		return rs;
	}
	/**
	 * 一对多数据列表
	 * @param sql 要执行的sql语句
	 * @param foreignColumnName 外键字段的列名（大写）
	 * @return
	 * @throws Exception
	 */
	public synchronized Map<Object,List<Map<String,Object>>> queryDataInMap(String sql,String foreignColumnName) throws Exception{
		List<Map<String,Object>> allDatas = this.queryBySql(sql);
		Map<Object,List<Map<String,Object>>> dataMap = new HashMap<Object,List<Map<String,Object>>>();
		for(int i=0;i<allDatas.size();i++){
			Map<String,Object> data = allDatas.get(i);
			List<Map<String,Object>> list = dataMap.get(data.get(foreignColumnName));
			if(list==null){
				list = new ArrayList<Map<String,Object>>();
				dataMap.put(data.get(foreignColumnName), list);
			}
			list.add(data);
		}
		return dataMap;
	}
	/**
	 * 执行sql语句,插入，修改，删除操作
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean executeSql(String sql) throws Exception{
		System.out.println("DBEntity将执行executeSql(sql)==>"+sql);
		Statement stmt = getConnection().createStatement();
		int rs = stmt.executeUpdate(sql);
		stmt.close();
		
		if(rs<=0) return false;
		return true;
	}
	/**
	 * 执行sql语句,插入，修改，删除操作
	 * @param sql 以?号为参数
	 * @param param 参数列表，以问号位置(Integer)为key,参数值为value
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean executeSql(String sql,Map<Integer,Object> param)throws Exception{
		System.out.println("DBEntity将执行executeSql(sql,param)==>"+sql);
		PreparedStatement ps = getConnection().prepareStatement(sql);
		for(int i=0;i<param.size();i++){
			ps.setObject(i+1, param.get(i+1));
		}
		
		int rs = ps.executeUpdate();
		ps.close();
		if(rs<=0) return false;
		return true;
	}
	/**
	 * 执行sql语句,插入，修改，删除操作
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean executeSql(String sql,Object[] params)throws Exception{
		if(sql==null||sql.trim().toLowerCase().startsWith("select")){
			throw new RuntimeException("DBEntity.executeSql(sql,params)\u53c2\u6570sql\u5fc5\u987b\u4ee5select\u5f00\u59cb");	//DBEntity.saveMuitls(insert_sql,dataList)参数insert_sql必须以insert开始
		}
		System.out.println("DBEntity将执行executeSql(sql,param)==>"+sql);
		PreparedStatement ps = getConnection().prepareStatement(sql);
		if(params!=null)
		for(int i=0;i<params.length;i++){
			ps.setObject(i+1, params[i]);
		}
		
		int rs = ps.executeUpdate();
		ps.close();
		if(rs<=0) return false;
		return true;
	}
	/**
	 * 执行批量数据的插入语句
	 * @param insert_sql 如：inert into $tablename values(?,?,?)
	 * @param dataList 如：{['1','张三']['2','男']['3','22']}
	 * @return
	 * @throws Exception
	 */
	public synchronized int[] saveMuitls(String insert_sql,List<Map<String, Object>> dataList) throws Exception {
		if(insert_sql==null||insert_sql.trim().toLowerCase().indexOf("insert")<0){
			throw new RuntimeException("DBEntity.saveMuitls(insert_sql,dataList)\u53c2\u6570insert_sql\u5fc5\u987b\u4ee5insert\u5f00\u59cb");	//DBEntity.saveMuitls(insert_sql,dataList)参数insert_sql必须以insert开始
		}
		System.out.println("DBEntity将执行saveMuitls(insert_sql,dataList)==>"+insert_sql);
		PreparedStatement ps = getConnection().prepareStatement(insert_sql);
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> data = dataList.get(i);
			for(int j=0;j<data.size();j++){
				ps.setObject(j+1, data.get(""+(j+1)));
			}
			ps.addBatch();
		}
		int [] rs = ps.executeBatch();
		ps.close();
		return rs;
	}
	/**
	 * 将Clob类型的数据转成字符串，如果不是clob类型则返回原对象
	 * @param in
	 * @return
	 */
	public static Object clobToString(Object in){
		System.out.println("clob转化成String中……");
		try{
			if ("oracle.sql.CLOB".equals(in.getClass().getName())){
				String rtn = "";
				oracle.sql.CLOB clob = (oracle.sql.CLOB)in;
				InputStream input = clob.getAsciiStream();
				int len = (int)clob.length();
				byte[] by = new byte[len];
				int i ;
				while(-1 != (i = input.read(by, 0, by.length))) {
					input.read(by, 0, i);
				}
				rtn = new String(by);
				rtn=clob.getSubString((long)1,(int)clob.length());
				return rtn;
			}else if ("weblogic.jdbc.wrapper.Clob_oracle_sql_CLOB".equals(in.getClass().getName())){
				String rtn = "";
				Method method = in.getClass().getMethod("getVendorObj",new Class[]{});
				oracle.sql.CLOB clob = (oracle.sql.CLOB)method.invoke(in);
				InputStream input = clob.getAsciiStream();
				int len = (int)clob.length();
				byte[] by = new byte[len];
				int i ;
				while(-1 != (i = input.read(by, 0, by.length))) {
					input.read(by, 0, i);
				}
				rtn = new String(by);
				rtn=clob.getSubString((long)1,(int)clob.length());
				return rtn;
			}else{
				return in;
			}
		}catch (Exception e){
			e.printStackTrace();
			return in;
		}
	} 
}
