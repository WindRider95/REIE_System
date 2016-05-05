package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DAO {
	public static final String url = "jdbc:mysql://127.0.0.1/reie?useUnicode=true&characterEncoding=utf8";  
	public static final String name = "com.mysql.jdbc.Driver";  
	public static final String user = "root";  
	public static final String password = "wangyili";  
	public Connection conn = null;  
	public PreparedStatement pst = null; 
	public ResultSet ret =null;
	public static void main(String args[])
	{
		/*Map m=new HashMap();
		m.put("title", "aaa");
		m.put("publish", "bbb");
		m.put("time", "2014-05-03");
		m.put("link", "ccc");
		List<Map<String,String>> l=new LinkedList<Map<String, String>>();
		l.add(m);
		DAO d=new DAO();
		d.insert(l);*/
		/*DAO d=new DAO();
		d.find("","新","","","");*/
	}
	//search
	//查询方式：前后模糊查询
	//查询类型：1.titile2.publish3.time4.url
	public List<Map<String,String>> find(String title,String publish,String time1,String time2,String link,String tabletitle)
	{
		
			  try {
				Class.forName(name);
				conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
				 String sql = null;
				if(!time1.equals("")&&!time2.equals(""))
					sql="select title,publish,time,link from "+tabletitle+" where title like '%"+title+"%' and publish like '%"+publish+"%' and time >'"+time1+"' and time <'"+time2+"' and link like'%"+link+"%'";
				else if(time1.equals("")&&!time2.equals(""))
					sql="select title,publish,time,link from "+tabletitle+" where title like '%"+title+"%' and publish like '%"+publish+"%' and time <'"+time2+"' and link like'%"+link+"%'";
				else if(!time1.equals("")&&time2.equals(""))
					sql="select title,publish,time,link from "+tabletitle+" where title like '%"+title+"%' and publish like '%"+publish+"%' and time >'"+time1+"' and link like'%"+link+"%'";
				else if(time1.equals("")&&time2.equals(""))
					sql="select title,publish,time,link from "+tabletitle+" where title like '%"+title+"%' and publish like '%"+publish+"%' and link like'%"+link+"%'";
				//System.out.println(sql);
	        	 pst = (PreparedStatement) conn.prepareStatement(sql);//准备执行语句 
	        	 ret=pst.executeQuery();
	        	 List l=new LinkedList<Map<String,String>>();
	        	 while (ret.next())
	        	 {
	        		/* System.out.println(ret.getString(1));
	        		 System.out.println(ret.getString(2));
	        		 System.out.println(ret.getDate(3).toString());
	        		 System.out.println(ret.getString(4));*/
	        		 Map m=new HashMap<String,String>();
	        		 m.put("title", ret.getString(1));
	        		 m.put("publish",ret.getString(2));
	        		 m.put("time",ret.getDate(3).toString());
	        		 m.put("link",ret.getString(4));
	        		 l.add(m);
	        	 }
	        	 ret.close();
	        	 pst.close();
	        	 conn.close();
	        	/* Iterator a=l.iterator();
	        	 while(a.hasNext())
	        	 {
	        		 Map b=(Map) a.next();
	        		 System.out.println("title:"+b.get("title")+" publish:"+b.get("publish")+" time:"+b.get("time")+" link"+b.get("link"));
	        	 }*/
	        	 return l;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//指定连接类型  
			return null;
		  
		
		
	}
	//save
	public  void insert(List<Map<String,String>> arg,String tabletitle)
	{
		
		try {  
		      Class.forName(name);//指定连接类型  
			         conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接 
			         Iterator  iterator  =  arg.iterator() ;
			         while(iterator.hasNext())
			         {	UUID uuid = UUID.randomUUID(); 
			         	String id=uuid.toString().replace("-", "");
			        	 Map<String,String> val=(Map<String, String>) iterator.next();
			        	 if(val.get("title")==null||val.get("time")==null)
			        		 continue;
			        	 String sql="insert into "+tabletitle+" values ( '"+id+"','"+val.get("title")+"','"+val.get("publish")+"','"+transDate(val.get("time"))+"','"+val.get("link")+"' )";
			        	 //System.out.println(sql);
			        	 pst = (PreparedStatement) conn.prepareStatement(sql);//准备执行语句  
			        	 pst.executeUpdate();
			        	 //执行查询
			        	//res=pst.executeQuery(); 
			        	//res.close();
			        	
			         }
			         pst.close();
		        	 conn.close();
			      } catch (Exception e) {  
			           e.printStackTrace();  
			      }  

	}
	 private String transDate(String cndate)
	{	cndate=cndate.replaceAll(" ", "");
		 int yearPos = cndate.indexOf("年");  
         int monthPos = cndate.indexOf("月");  
         int dayPos=cndate.indexOf("日");
         String date=cndate.substring(0,yearPos);
         date+="-";
	    date+=cndate.substring(yearPos+1,monthPos);
	    date+="-";
	    date+=cndate.substring(monthPos+1,dayPos);
	    date+=" ";
	    date+=cndate.substring(dayPos+1,cndate.length());
	    //System.out.println(date);
		return date;
	}
	 //添加抽取记录并创建表并且返回表名
	public String extandcreate()
	{
		try {  
		      Class.forName(name);//指定连接类型  
	         conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接 
	         conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
			 Date now = new Date(); 
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式 
			 String time= dateFormat.format( now ); 
			 String title="newsinfo_"+time;
			 title=title.replace(" ", "");
			 title=title.replace("-","");
			 title=title.replace(":", "");
         	UUID uuid = UUID.randomUUID(); 
         	String id=uuid.toString().replace("-", "");
        	 String sql="insert into history values ( '"+id+"','"+time+"','"+title+"' )";
        	 //System.out.println(sql);
        	 pst = (PreparedStatement) conn.prepareStatement(sql);//准备执行语句  
        	 pst.executeUpdate();
        	 //创建表
        	 sql="create table "+title+" (id varchar(50) primary key,title varchar(50),publish varchar(50),time datetime,link varchar(100))";
        	 pst = (PreparedStatement) conn.prepareStatement(sql);//准备执行语句  
        	 pst.executeUpdate();
        	 //执行查询
        	//res=pst.executeQuery(); 
        	//res.close();
	         pst.close();
        	 conn.close();
        	 return title;
		      } catch (Exception e) {  
		           e.printStackTrace();  
		      }  
	return null;
	}
}
