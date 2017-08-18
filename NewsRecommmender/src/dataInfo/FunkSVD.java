package dataInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class FunkSVD {

	public static void main(String[] args) throws IOException {
		long startTime=System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader("src/trainData.txt"));
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("E:\\研究生\\大作业\\NewsRecData\\funkSVDResult.txt"));

		double[][] r = new double[10005][6200];
		double[][] p = new double[10005][6200];
        double[][] u = new double[10005][201];
        double[][] v = new double[6200][201];
        
        for(int i = 0;i < 10005;i++) {
        	for(int j = 0;j < 201;j++) {
        		u[i][j] = 0.1;
        	}
        }
        
        for(int i = 0;i < 6200;i++) {
        	for(int j = 0;j < 201;j++) {
        		v[i][j] = 0.1;
        	}
        }
        
        News.loadNewsHashToNum();
        News.loadNewsHashToTime();
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
        
        int count = 0;
        for(int a = 1;a <= 10000;a++) {
    		for(int i = 1;i <= 6183;i++) {
    	        if(Math.abs(r[a][i]-1) < 0.1) count++;
    		}
    	}
        System.out.println("训练数据中的有效评分为:" + count + "个.");
        
        int iteratorCount = 0;
        for(int f = 1;f <= 200;f = (f+1) % 200) {
        	double e = 0; //用来记录误差
        	for(int a = 1;a <= 10000;a++) {
        		for(int i = 1;i <= 6183;i++) {
        			if(r[a][i] == 1) {
        				p[a][i] = 0;
        				for(int j = 1;j <= 200;j++) {
        					p[a][i] += u[a][j] * v[i][j];
        				}
        				e += Math.abs(r[a][i] - p[a][i]);
        				//writer.write(p[a][i] + "\r\n");
        				v[i][f] += 0.01*((r[a][i] - p[a][i])*u[a][f]);
        				u[a][f] += 0.01*((r[a][i] - p[a][i])*v[i][f]);
        			}
        		}
        	}
        	if(e < 400) {
        		for(int i = 1;i <= 10000;i++) {
                	for(int j = 1;j <= 6183;j++) {
                		p[i][j] = 0;
                		for(int k = 1;k <= 200;k++) {
                			p[i][j] += u[i][k] * v[j][k];
                		}
                		writer.write(p[i][j] + "\t");
                	}
                	writer.write("\r\n");
                }
        		break;
        	} 
        	if(Math.abs(f - 1) < 0.1) {
        		iteratorCount++;
        		System.out.println(e);
        	}
        }
       
        System.out.println(iteratorCount);
        long endTime=System.currentTimeMillis();
        System.out.println((startTime - endTime)/1000);
        writer.close();
        reader.close();
	}
}
