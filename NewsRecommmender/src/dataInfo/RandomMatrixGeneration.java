package dataInfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RandomMatrixGeneration {

	public static void main(String[] args) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("E:\\研究生\\大作业\\NewsRecData\\FakeResult.txt"));
		for(int i = 1;i <= 10000;i++) {
			for(int j = 1;j <= 6183;j++) {
				if(j == 6183) {
					writer.write(new Double(Math.random()).toString());
					//writer.write("1.0");
				} else {
					//writer.write("1.0" + "\t");
					writer.write(Math.random() + "\t");
				}
			}
			writer.write("\r\n");
		}
		writer.flush();
		writer.close();
	}

}
