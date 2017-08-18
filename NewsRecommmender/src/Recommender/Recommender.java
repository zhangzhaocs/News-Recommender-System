package Recommender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Recommender {   
    public static void main(String[] args) throws IOException {
    	recommend();
    }
    
    public static void recommend() throws IOException {
    	HashMap<String, ArrayList<String>> hmRead = new HashMap<String, ArrayList<String>>(); 
		HashMap<String, ArrayList<String>> hmIICF = new HashMap<String, ArrayList<String>>();  
		HashMap<String, ArrayList<String>> hmFunkSVD = new HashMap<String, ArrayList<String>>();  
		BufferedReader readerRead = new BufferedReader(new FileReader("src/ReadInTestData.txt"));
		BufferedReader readerIICF = new BufferedReader(new FileReader("src/IICFRecommendList.txt"));
		BufferedReader readerFunkSVD = new BufferedReader(new FileReader("src/funkSVDRecommendList.txt"));
		
		String str = "";
		while((str = readerRead.readLine()) != null) {
        	StringTokenizer st = new StringTokenizer(str,"\t");
			int count = 0;
			ArrayList<String> list =  new ArrayList<String>();
			String user = new String();
			while(st.hasMoreElements()) {
				count++;
				if(count == 1) {
					user = st.nextToken();
				} else {
					list.add(st.nextToken());
				}
			}
			hmRead.put(user, list);
		}
		
		while((str = readerIICF.readLine()) != null) {
        	StringTokenizer st = new StringTokenizer(str,"\t");
			int count = 0;
			ArrayList<String> list =  new ArrayList<String>();
			String user = new String();
			while(st.hasMoreElements()) {
				count++;
				if(count == 1) {
					user = st.nextToken();
				} else {
					list.add(st.nextToken());
				}
			}
			hmIICF.put(user, list);
		}
		
		while((str = readerFunkSVD.readLine()) != null) {
        	StringTokenizer st = new StringTokenizer(str,"\t");
			int count = 0;
			ArrayList<String> list =  new ArrayList<String>();
			String user = new String();
			while(st.hasMoreElements()) {
				count++;
				if(count == 1) {
					user = st.nextToken();
				} else {
					list.add(st.nextToken());
				}
			}
			hmFunkSVD.put(user, list);
		}
		
		System.out.println("-------------欢迎使用本系统，本系统可查询任意用户的推荐列表-------------");
		while(true) {
			System.out.println("-------------请输入要查询的用户编号，输入exit退出系统----------------");
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
		    String user = s.next();
		    if(user.equals("exit")) {
		    	System.out.println("---------------------感谢使用本系统，再见！----------------------------");
		    	s.close();
		    	break;
		    }
		    if(!hmIICF.containsKey(user)) {
		    	System.out.println("----------------------没有此用户的信息！-----------------------------");
		    	continue;
		    }
		    
		    System.out.println("-------------通过item-item collaborative filtering算法推荐的列表为：");
		    ArrayList<String> list = hmIICF.get(user);
		    for(String tmp:list) {
		    	System.out.println(tmp);
		    }
		    
		    System.out.println("-------------通过funkSVD算法推荐的列表为：--------------------------");
		    list = hmFunkSVD.get(user);
		    for(String tmp:list) {
		    	System.out.println(tmp);
		    }
		    
		    if(!hmRead.containsKey(user)) {
		    	System.out.println("-------------该用户在后十天没有浏览新闻--------------------------");
		    } else {
		    	System.out.println("-------------该用户在后十天浏览的新闻为--------------------------");
		    	list = hmRead.get(user);
		    	for(String tmp:list) {
			    	System.out.println(tmp);
			    }
		    }
		    
		} 
        readerFunkSVD.close();
        readerIICF.close();
        readerRead.close();
    }
}
