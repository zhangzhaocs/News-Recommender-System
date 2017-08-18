package dataInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class EffectiveScoreCounting {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src/trainData.txt"));
		double[][] r = new double[10005][6200];
		
		News.loadNewsHashToNum();
        User.loadUserHashToNum();
        String str1 = new String();
        while((str1 = reader.readLine()) != null) {
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
        int count = 0;
        for(int i = 1;i <= 10000;i++) {
        	for(int j = 1;j <= 6183;j++) {
      		    if(r[i][j] == 1) count++;
      	    }
        }
        System.out.println("有效评分为:" + count);
        reader.close();
        
        BufferedReader reader1 = new BufferedReader(new FileReader("src/trainData.txt"));
        Set<String> hsUser = new HashSet<String>();
        Set<String> hsNews = new HashSet<String>();
        String str = "";
        while((str = reader1.readLine()) != null) {
        	StringTokenizer st = new StringTokenizer(str,"\t");
			int count1 = 0;
			String user = new String();
			String news = new String();
			while(st.hasMoreElements()) {
                count1++;
                if(count1 == 1) {
                	user = st.nextToken();
                	if(!hsUser.contains(user))   hsUser.add(user);
                } else if(count1 == 2) {
                	news = st.nextToken();
                	if(!hsNews.contains(news))   hsNews.add(news);
                	break;
                }
			}
        }
        
        System.out.println("训练数据中的用户数:" + hsUser.size());
        System.out.println("训练数据中的新闻数:" + hsNews.size());
        reader1.close();
	}

}
