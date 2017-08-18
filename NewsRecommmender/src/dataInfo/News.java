package dataInfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class News {
	public String newsID;
	public static HashMap<String, Integer> newsHashToNum = new HashMap<String, Integer>();
	public static HashMap<String, Long> newsHashToTime = new HashMap<String, Long>();	
	
	public News(String newsID) {
		this.newsID = newsID;
	}
	
	public News() {
	}
    
	public String getNewsID() {
        return this.newsID;
	}
	
	public void setNewsID(String newsID) {
		this.newsID = newsID;
	}
	
	public static void loadNewsHashToNum() throws IOException {
		String InputFileName = "src/newsHashToNum.txt";
		FileInputStream fin = new FileInputStream(InputFileName);
		InputStreamReader inR = new InputStreamReader(fin);
		BufferedReader reader = new BufferedReader(inR);
		String str = new String();
		while((str = reader.readLine()) != null) {
			String value = new String();
			String key = new String();
			StringTokenizer st = new StringTokenizer(str,"\t");
			int count = 0;
			while(st.hasMoreElements()) {
				count++;
				if(count == 1) {
					key = st.nextToken();
				} else if(count == 2) {
					value = st.nextToken();
				}
			}
			newsHashToNum.put(key, Integer.valueOf(value));
		}
		reader.close();
		inR.close();
		fin.close();
	}
	
	public static void loadNewsHashToTime() throws IOException {
		String InputFileName = "src/newsHashToTime.txt";
		FileInputStream fin = new FileInputStream(InputFileName);
		InputStreamReader inR = new InputStreamReader(fin);
		BufferedReader reader = new BufferedReader(inR);
		String str = new String();
		while((str = reader.readLine()) != null) {
			String value = new String();
			String key = new String();
			StringTokenizer st = new StringTokenizer(str,"\t");
			int count = 0;
			while(st.hasMoreElements()) {
				count++;
				if(count == 1) {
					key = st.nextToken();
				} else if(count == 2) {
					value = st.nextToken();
				}
			}
			newsHashToTime.put(key, Long.valueOf(value));
		}
		reader.close();
		inR.close();
		fin.close();
	}
	
	public int getHashNum() {
		return newsHashToNum.get(this.newsID);
	}
	
	public long getHashTime() {
		return newsHashToTime.get(this.newsID);
	}
}
