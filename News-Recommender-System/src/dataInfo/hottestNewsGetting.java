package dataInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class hottestNewsGetting {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src/trainData.txt"));
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("src/hottestNews.txt"));
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		String str = "";
		while((str = reader.readLine()) != null) {
	        StringTokenizer st = new StringTokenizer(str,"\t");
		    int count = 0;
		    String news = "";
		    while(st.hasMoreElements()) {
		    	count++;
		    	String tmp = st.nextToken();
		    	if(count == 2) {
		    		news = tmp;
		    		if(hm.containsKey(news)) {
		    			int val = hm.get(news) + 1;
		    			hm.put(news, val);
		    		} else {
		    			hm.put(news, 1);
		    		}
		    		break;
		    	}
		    }
		}
		
		int loop = hm.size();
		int T = 0;
		while(T != loop) {
			int max = 0;
			String hotNews = "";
			Iterator<String> iter = hm.keySet().iterator();
			while (iter.hasNext()) {
				String tmp = iter.next();
				int tmpvalue = hm.get(tmp);
			    if(tmpvalue > max) {
			    	hotNews = tmp;
			    	max = tmpvalue;
			    }
			}
			hm.put(hotNews, 0);
			T++;
			System.out.println(T);
			writer.write(hotNews + "\r\n");
		}
        writer.close();
		reader.close();
	}

}
