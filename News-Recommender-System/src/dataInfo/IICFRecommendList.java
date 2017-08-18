package dataInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class IICFRecommendList {

	public static void main(String[] args) throws IOException {
		News.loadNewsHashToNum();
		User.loadUserHashToNum();
		
		BufferedReader reader = new BufferedReader(
				new FileReader("E:\\研究生\\大作业\\NewsRecData\\IICFResult.txt"));
		BufferedReader reader1 = new BufferedReader(new FileReader("src/trainData.txt"));
		BufferedReader reader2 = new BufferedReader(
				new FileReader("E:\\研究生\\大作业\\NewsRecData\\hottestNews.txt"));
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("E:\\研究生\\大作业\\NewsRecData\\IICFRecommendList.txt"));
		int[][] r = new int[10005][6200];
		String str = "";
		ArrayList<String> hottest = new ArrayList<String>();
//		hottest.add("100646680");
//		hottest.add("100645310");
//		hottest.add("100645338");
//		hottest.add("100649853");
//		hottest.add("100645335");
//		hottest.add("100645322");
//		hottest.add("100644067");
//		hottest.add("100650720");
//		hottest.add("100645366");
//		hottest.add("100652418");
		while((str = reader2.readLine()) != null) {
			hottest.add(str);
		}
		
		int effectiveScoreCount = 0;
		while((str = reader1.readLine()) != null) {
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
			effectiveScoreCount++;
        }
		effectiveScoreCount = 0;
		for(int i = 1;i <= 10000;i++) {
			for(int j = 1;j <= 6183;j++) {
				if(r[i][j] == 1) {
					effectiveScoreCount++;
				}
			}
		}
		System.out.println("训练数据中的有效评分数:" + effectiveScoreCount);
		//-----------至此，r矩阵的构建完成了，没有问题-------------------------
		
		int userNum = 0;
		while((str = reader.readLine()) != null) {
			userNum++;
			StringTokenizer st = new StringTokenizer(str,"\t");
			ArrayList<String> list = new ArrayList<String>();
			int count = 0;
			double[] d = new double[6200]; 
			while(st.hasMoreElements()) {
				count++;
				d[count] = Double.valueOf(st.nextToken());
			}
			//------d[]矩阵的计算正确
			int T = 0;
			while(T != 10) {
				double max = 0;
				int sub = 0;
				for(int i = 1;i <= 6183;i++) {
					if(d[i] > max) {
						max = d[i];
						sub = i;
					}
				}
				
				if(sub == 0) {
					for(String tmp:hottest) {
						if(r[userNum][News.newsHashToNum.get(tmp)] != 1) {
							list.add(tmp);
						    T++;
						    r[userNum][News.newsHashToNum.get(tmp)] = 1;
						    if(T == 10) break;
					    }
					}
				} else {
					Iterator<String> iter = News.newsHashToNum.keySet()
							.iterator();
					while (iter.hasNext()) {
						String key = iter.next();
						if (News.newsHashToNum.get(key) == sub
								&& r[userNum][sub] != 1) {
							list.add(key);
							r[userNum][sub] = 1;
							d[sub] = 0;
							T++;
							// System.out.println(T);
							break;
						} else if (r[userNum][sub] == 1) {
							d[sub] = 0;
							break;
						}
					}
				}
	        }
//			for (String tmp : list) {
//				System.out.println(tmp);;
//			}
			//--------list构建成功------------------------
			Iterator<String> iter = User.userHashToNum.keySet().iterator();
			while (iter.hasNext()) {
			    String key = iter.next();
			    if(User.userHashToNum.get(key) == userNum) {
			    	writer.write(key + "\t");
			    	for (String tmp : list) {
						writer.write(tmp + "\t");
					}
			    	writer.write("\r\n");
			    	break;
			    }
			}
			System.out.println(userNum);
			
		}
		writer.close();
		reader2.close();
		reader1.close();
		reader.close();
	}
}
