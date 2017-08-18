package dataInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Evaluation {

	public static void main(String[] args) throws IOException {
		HashMap<String, ArrayList<String>> hmRead = new HashMap<String, ArrayList<String>>(); 
		HashMap<String, ArrayList<String>> hmRec = new HashMap<String, ArrayList<String>>(); 
		BufferedReader readerRead = new BufferedReader(
				new FileReader("src\\ReadInTestData.txt"));
		BufferedReader readerRec = new BufferedReader(
				new FileReader("src\\funkSVDRecommendList.txt"));
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
//		System.out.println(hmRead.size());
//		Iterator<String> iter = hmRead.keySet().iterator();
//		while (iter.hasNext()) {
//			String key = iter.next();
//		    System.out.println(hmRead.get(key).size());
//		}
		while((str = readerRec.readLine()) != null) {
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
			hmRec.put(user, list);
		}
//		System.out.println(hmRec.size());
//		Iterator<String> iter = hmRec.keySet().iterator();
//		while (iter.hasNext()) {
//			String key = iter.next();
//		    System.out.println(hmRec.get(key).size());
//		}
		
		double p = 0, r = 0, f = 0;  
		double pcount = 0, rcount = 0;
		Iterator<String> iter = hmRead.keySet().iterator();
		while (iter.hasNext()) {
			pcount = 0; rcount = 0;
			String key = iter.next();
			ArrayList<String> listRead = new ArrayList<String>();
			ArrayList<String> listRec = new ArrayList<String>();
			listRead = hmRead.get(key);
			listRec = hmRec.get(key);
			for(String tmp:listRead) {
				if(listRec.contains(tmp)) {
					pcount += 1;
				}
			}
			pcount /= 10;
			p += pcount;
			
			for(String tmp:listRec) {
				if(listRead.contains(tmp)) {
					rcount += 1;
				}
			}
			rcount /= listRead.size();
			r += rcount;
			if(pcount + rcount != 0) {
				f += (2*pcount*rcount)/(pcount+rcount);
			}
		}
		p = p / hmRead.size();
		r = r / hmRead.size();
		f = f / hmRead.size();
		System.out.println("precison:" + p);
		System.out.println("recall:" + r);
		System.out.println("f1:" + f);
		
		readerRec.close();
		readerRead.close();
	}

}
