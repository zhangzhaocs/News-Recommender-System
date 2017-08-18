package dataInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class ItemSimilarity {
    public static void main(String[] args) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader("src/trainData.txt"));
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("E:\\研究生\\大作业\\NewsRecData\\ItemSimilarityBetter.txt"));
		BufferedWriter writer1 = new BufferedWriter(
				new FileWriter("E:\\研究生\\大作业\\NewsRecData\\IICFResultBetter.txt"));
        
		// user_item评分矩阵 r[user][item] = 1
		double[][] r = new double[10005][6200];
		// item-item sim
        double[][] ii = new double[6200][6200];
        // predict rate 
        double[][] p = new double[10005][6200];
        
        News.loadNewsHashToNum();
        User.loadUserHashToNum();
        String str = new String();
        while((str = reader.readLine()) != null) {
        	StringTokenizer st = new StringTokenizer(str,"\t");
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
//      	    for(int j = 1;j <= 6183;j++) {
//      		    if(r[i][j] == 1) count++;
//      	    }
//        }
//        System.out.println(count);
//-------------------------------得到评分矩阵r，完成----------------------------------        
        for(int i = 1;i <= 6183;i++) {
        	for(int j = 1;j <= 6183;j++) {
        		double count = 0;
        		for(int k = 1;k <= 10000;k++) {
        			if(r[k][i] == 1 && r[k][j] == 1) {
        				count += 1;
        			}
        		}
        		if(count != 0)
        			ii[i][j] = Math.min(Math.log(count) / Math.log(2), 10) / 10;
        		//System.out.println(count + " " + ii[i][j]);
        	}
        	System.out.println("-" + i);
        }
        for(int i = 1;i <= 6183;i++) {
        	for(int j = 1;j <= 6183;j++) {
                writer.write(ii[i][j] + "\t");
        	}
        	writer.write("\r\n");
        }
        
        
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
        			p[i][j] = 0;
        		}
        	}
        	System.out.println("--" + i);
        }
        for(int i = 1;i <= 10000;i++) {
        	for(int j = 1;j <= 6183;j++) {
        		writer1.write(p[i][j] + "\t");
        	}
        	writer1.write("\r\n");
        }
        
        writer1.close();
        writer.close();
        reader.close();
        
        
    }
}
