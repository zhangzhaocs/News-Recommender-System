package dataInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class IICFResult {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader("E:\\研究生\\大作业\\NewsRecData\\itemSimilarity.txt"));
		BufferedReader reader1 = new BufferedReader(new FileReader("src/trainData.txt"));
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("E:\\研究生\\大作业\\NewsRecData\\IICFResult.txt"));
		double[][] ii = new double[6200][6200];
		double[][] r = new double[10005][6200];
		double[][] p = new double[10005][6200];
		
		String str = "";
		int row = 0;
		while((str = reader.readLine()) != null) {
            row++;			
	       	StringTokenizer st = new StringTokenizer(str,"\t");
			int column = 0;
			while(st.hasMoreElements()) {
				column++;
				ii[row][column] = Double.valueOf(st.nextToken());
			}
		}
//		for(int i = 1;i <= 6183;i++) {
//			System.out.println(ii[1][i]);
//		}
//----------------------ii矩阵生成完成---------------------------------
		News.loadNewsHashToNum();
        User.loadUserHashToNum();
        String str1 = new String();
        while((str1 = reader1.readLine()) != null) {
        	StringTokenizer st = new StringTokenizer(str1,"\t");
			int count = 0;
			String user = new String();
			String news = new String();
			while(st.hasMoreElements()) {
				count++;
				if(count == 1) {
					user = st.nextToken();
				} else if(count == 2) {
					news = st.nextToken();
					break;
				}
			}
			r[new User(user).getHashNum()][new News(news).getHashNum()] = 1;
        }
//        int count = 0;
//        for(int i = 1;i <= 10000;i++) {
//        	for(int j = 1;j <= 6183;j++) {
//      		    if(r[i][j] == 1) count++;
//      	    }
//        }
//        System.out.println(count);
//-------------------------------得到评分矩阵r，完成----------------------------------
//-------------------------------下面开始补全评分矩阵中的空缺位置--------------------------
        for(int i = 1;i <= 10000;i++) {
        	for(int j = 1;j <= 6183;j++) {
        		double rate = 0;
        		if(r[i][j] == 0) {
        			for(int k = 1;k <= 6183;k++) {
        				if(r[i][k] == 1) {
        					rate += ii[j][k];
        				}
        			}
        			p[i][j] = rate;
        		} else {
        			p[i][j] = 1;
        		}
        	}
        	System.out.println("--" + i);
        }
//        for(int i = 1;i <= 6183;i++) {
//        	System.out.println(p[1][i]);
//        }
        
        for(int i = 1;i <= 10000;i++) {
        	for(int j = 1;j <= 6183;j++) {
        		writer.write(p[i][j] + "\t");
        	}
        	writer.write("\r\n");
        }
        
        writer.close();
        reader1.close();
        reader.close();

	}

}
