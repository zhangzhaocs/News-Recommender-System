package dataInfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class User {
	public String userID;
	public static HashMap<String, Integer> userHashToNum = new HashMap<String, Integer>();
	
	public User(String userID) {
		this.userID = userID;
	}
	
	public User() {
	}
    
	public String getUserID() {
        return this.userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}	
	
	public static void loadUserHashToNum() throws IOException {
		String InputFileName = "src/userHashToNum.txt";
		FileInputStream fin = new FileInputStream(InputFileName);
		InputStreamReader inR = new InputStreamReader(fin);
		BufferedReader reader = new BufferedReader(inR);
		String str = new String();
		while((str = reader.readLine()) != null) {
			String value = new String();
			String key = new String();
			StringTokenizer st = new StringTokenizer(str,"\t");
			int count = 0;
			while(st.hasMoreElements()) {
				count++;
				if(count == 1) {
					key = st.nextToken();
				} else if(count == 2) {
					value = st.nextToken();
				}
			}
			userHashToNum.put(key, Integer.valueOf(value));
		}
		reader.close();
		inR.close();
		fin.close();
	}	
	
	public int getHashNum() {
		return userHashToNum.get(this.userID);
	}

}
