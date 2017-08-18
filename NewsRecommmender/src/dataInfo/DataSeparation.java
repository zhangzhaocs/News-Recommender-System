package dataInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class DataSeparation {

	public static void main(String[] args) throws ParseException, IOException {
		 SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Date seperateDate= myFormatter.parse("2014-03-21 00:00"); 
		 Date baseDate= myFormatter.parse("1970-01-01 00:00");
		 long  seperateTime = (seperateDate.getTime()-baseDate.getTime()) / 1000;
		 
		 String InputFileName = "E:\\研究生\\大作业\\NewsRecData\\user_click_data.txt";
		 String OutputFileName1 = "E:\\研究生\\大作业\\NewsRecData\\trainData.txt";
		 String OutputFileName2 = "E:\\研究生\\大作业\\NewsRecData\\testData.txt";
		 FileInputStream fin = new FileInputStream(InputFileName);
		 InputStreamReader inR = new InputStreamReader(fin);
		 BufferedReader reader = new BufferedReader(inR);
			
		 FileOutputStream fout1 = new FileOutputStream(OutputFileName1, false);
		 OutputStreamWriter outW1 = new OutputStreamWriter(fout1);
		 BufferedWriter bfW1 = new BufferedWriter(outW1);
		 FileOutputStream fout2 = new FileOutputStream(OutputFileName2, false);
		 OutputStreamWriter outW2 = new OutputStreamWriter(fout2);
		 BufferedWriter bfW2 = new BufferedWriter(outW2);
		 
		 String str = new String();
		 while((str = reader.readLine()) != null) {
			 int count = 0;
			 String temp_str = new String();
			 StringTokenizer st = new StringTokenizer(str,"\t");
			 while(st.hasMoreElements()) {
			 	 ++count;
				 temp_str = st.nextToken();
				 if(count == 3) {
				     if(Long.parseLong(temp_str) < seperateTime) {
						 bfW1.write(str + "\r\n");
					 } else {
					 	 bfW2.write(str + "\r\n");
					 }
				     break;
				 } 
			 }
		 }
		 bfW1.close();
		 outW1.close();
		 fout1.close();
		 bfW2.close();
		 outW2.close();
		 fout2.close();
		 reader.close();
		 inR.close();
		 fin.close();	 
	}

}
