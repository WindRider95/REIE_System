package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import  dao.DAO;

public class GetNews extends Thread{
	//get from combobox control
	static String rawurl="http://news.sina.com.cn/";
	/**网站上相对地址与绝对地址的映射*/
	private static Map<String,String> absRelativeUrlMap = new HashMap<String,String>();	
	private static Map<String,List<String>> contentMap=new HashMap<String,List<String>>();
	private static List<Map<String, String>> recList=new LinkedList<Map<String,String>>();
	private final static int timeOut = 30000;	
	private static int depth=1;
	private static String tabletitle="newsinfo";
	public static void main(String[] args)
	{
		tabletitle=args[0];
		
		/*System.out.println("start....");
		//获取所有urls
		getSubUrls(rawurl,rawurl);
		//处理文件
		for(String absUrl : absRelativeUrlMap.keySet()){
			
			String content;
			try {
				content = readContent(absUrl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!absUrl.startsWith(rawurl)){
				continue;
			}

		}
		insertnews(recList,args[0]);
		System.out.println("end....");*/
	}
	public void run()
	{
		view.main.jta.setText("start....");
		view.main.jta.setCaretPosition(view.main.jta.getText().length());
		System.out.println("start....");
		//获取所有urls
		getSubUrls(rawurl,rawurl);
		//处理文件
		for(String absUrl : absRelativeUrlMap.keySet()){
			
			String content;
			try {
				content = readContent(absUrl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!absUrl.startsWith(rawurl)){
				continue;
			}

		}
		insertnews(recList,tabletitle);
		view.main.jta.setText(view.main.jta.getText()+"\nend...");
		view.main.jta.setCaretPosition(view.main.jta.getText().length());
		System.out.println("end....");
		view.main.updateData();
	}
	private static String readContent(String absUrl)  throws IOException{
		// TODO Auto-generated method stub
		Document doc = Jsoup.connect(absUrl).timeout(timeOut).get();	
		Map map=new HashMap();
		map.put("link", absUrl);
		String pattern="\"page-header\">.*>([^aa]*)</h1";
		Pattern r = Pattern.compile(pattern);
	
		String line;//="<div class=\"page-header\"><h1 id=\"artibodyTitle\" cid=\"56044\" docid=\"fxqsxic3609932\">中泰铁路项目泰国政府“变卦” 不再向中方贷款</h1>\";";
		line=doc.html().replaceAll("[\\t\\n\\r\\s]", "");//将内容区域的回车换行去除 
		Matcher m = r.matcher(line);
		
		if (m.find( )) {
	         System.out.println("Found value: " + m.group(1)+"    "+absUrl);
	         view.main.jta.setText(view.main.jta.getText()+"\nFound value: " + m.group(1)+"    "+absUrl);
	         view.main.jta.setCaretPosition(view.main.jta.getText().length());
	         map.put("title", m.group(1));
	      } else {
	         System.out.println("NO MATCH:"+absUrl);
	         view.main.jta.setText(view.main.jta.getText()+"\nNO MATCH:"+absUrl);
	         view.main.jta.setCaretPosition(view.main.jta.getText().length());
	      }
		String pattern1="\"time-source\".*>([^aa]*)<span>.*\"media_name\".*>.*rel=\"nofollow\">([^aa]*)</a></span></span></span>";
		r=Pattern.compile(pattern1);
		m=r.matcher(line);
		if (m.find( )) {
	         System.out.println("Found value: " + m.group(1)+"    "+absUrl);
	         view.main.jta.setText(view.main.jta.getText()+"\nFound value: " + m.group(1)+"    "+absUrl);
	         view.main.jta.setCaretPosition(view.main.jta.getText().length());
	         System.out.println("Found value: " + m.group(2)+"    "+absUrl);
	         view.main.jta.setText(view.main.jta.getText()+"\nFound value: " + m.group(2)+"    "+absUrl);
	         view.main.jta.setCaretPosition(view.main.jta.getText().length());
	         map.put("time", m.group(1));
	         map.put("publish", m.group(2));
	      } else {
	         System.out.println("NO MATCH:"+absUrl);
	         view.main.jta.setText(view.main.jta.getText()+"\nNO MATCH:"+absUrl);
	         view.main.jta.setCaretPosition(view.main.jta.getText().length());
	      }
		
		recList.add(map);
		return null;
	}
	public static void  getSubUrls(String url,String relativeUrl){
				if(absRelativeUrlMap.get(url)!=null || filter(url)){
					return;
				}
				System.out.println(url);
				view.main.jta.setText(view.main.jta.getText()+"\n"+url);
				view.main.jta.setCaretPosition(view.main.jta.getText().length());
				if(!url.endsWith("/"))
				{absRelativeUrlMap.put(url,url); }
				Document doc = null;
				try {
					doc = Jsoup.connect(url).timeout(timeOut).get();
				} catch (IOException e) {
					System.err.println("url="+url+", 页面无效！");
					view.main.jta.setText(view.main.jta.getText()+"\nurl="+url+", 页面无效！");
					view.main.jta.setCaretPosition(view.main.jta.getText().length());
					return;
				}
				
				Elements eles = doc.body().select("a[href]");
				
				for(Element ele : eles){ 
					String absHref = ele.attr("abs:href").replaceAll("\\.\\.\\/", "");
					String href = ele.attr("href"); 
					if(href.startsWith("javascript") ||
							href.startsWith("#") ||
							(href.contains("(") && href.contains(""))){
						continue;
					}
					if(depth<2&&absHref.startsWith(rawurl)){ 
						depth++;
						getSubUrls(absHref,absHref);
						depth--;
					} 
				}  
			}
	
	private static boolean filter(String rawurl2) {
		// TODO Auto-generated method stub
		return false;
	}
	private  static void insertnews(List<Map<String,String>> arg,String tabletitle)
	{ DAO dao=new DAO();
		dao.insert(arg,tabletitle);
	}
}
