package dataInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class NewsHashToNum {
    public static int num = 1;
	
	public static void main(String[] args) throws IOException {
		String InputFileName = "E:\\研究生\\大作业\\NewsRecData\\user_click_data.txt";
		String OutputFileName = "E:\\研究生\\大作业\\NewsRecData\\newsHashToNum.txt";
		FileInputStream fin = new FileInputStream(InputFileName);
		InputStreamReader inR = new InputStreamReader(fin);
		BufferedReader reader = new BufferedReader(inR);
		
		FileOutputStream fout = new FileOutputStream(OutputFileName, false);
		OutputStreamWriter outW = new OutputStreamWriter(fout);
		BufferedWriter bfW = new BufferedWriter(outW);
		
		String str = new String();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		while((str = reader.readLine()) != null) {
			int count = 0;
			String newsID = new String();
			String temp_str = new String();
			StringTokenizer st = new StringTokenizer(str,"\t");
			while(st.hasMoreElements()) {
				count++;
				temp_str = st.nextToken();
				if(count == 2) {
					newsID = temp_str;
				} 
			}
			if(!hm.containsKey(newsID)) {
				bfW.write(newsID + "\t" + num + "\r\n");
				hm.put(newsID, num++);
			}
		}
	    
	    bfW.close();
	    outW.close();
	    fout.close();
	    reader.close();
	    inR.close();
	    fin.close();
	}
}
