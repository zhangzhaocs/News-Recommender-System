package dataInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class ReadInTestData {
    public static void main(String[] args) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader("src/testData.txt"));
    	BufferedWriter writer = new BufferedWriter(
				new FileWriter("E:\\研究生\\大作业\\NewsRecData\\ReadInTestData.txt"));
    	String str = "";
    	HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>(); 
    	while((str = reader.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(str,"\t");
			int count = 0;
			String user = "";
			String news = "";
			while(st.hasMoreElements()) {
				count++;
				if(count == 1) {
					user = st.nextToken();
				} else if(count == 2){
					news = st.nextToken();
					if(hm.containsKey(user)) {
						ArrayList<String> al = hm.get(user);
						al.add(news);
						hm.put(user,al);
					} else {
						ArrayList<String> al = new ArrayList<String>();
						al.add(news);
						hm.put(user,al);
					}
					break;
				}
			}
    	}
    	
    	Iterator<String> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			ArrayList<String> val = hm.get(key);
			writer.write(key + "\t");
			for (String tmp : val) {
				writer.write(tmp + "\t");
			}
			writer.write("\r\n");
		}
		writer.close();
		reader.close();
	}
}

